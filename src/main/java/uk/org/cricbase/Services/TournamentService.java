/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.DTOs.DetailedTournamentEditionSummary;
import uk.org.cricbase.DTOs.TournamentCreateRequest;
import uk.org.cricbase.DTOs.TournamentEditionCreateRequest;
import uk.org.cricbase.DTOs.TournamentSummary;
import uk.org.cricbase.Mappers.RosterMapper;
import uk.org.cricbase.Mappers.TournamentMapper;
import uk.org.cricbase.Models.Roster;
import uk.org.cricbase.Models.Tournament;
import uk.org.cricbase.Models.TournamentEdition;

/**
 *
 * 
 */
@Service
public class TournamentService {
    private final TournamentMapper tournamentMapper;
    private final MatchService matchService;
	private final RosterMapper rosterMapper;
	private final StatLineService statLineService;

	private ArrayList<Tournament> activeTournaments;

	public TournamentService(TournamentMapper tournamentMapper, MatchService matchService, RosterMapper rosterMapper, StatLineService statLineService) {
		this.tournamentMapper = tournamentMapper;
		this.matchService = matchService;
		this.rosterMapper = rosterMapper;
		this.statLineService = statLineService;

		this.activeTournaments = new ArrayList<>();
	}

	public Optional<TournamentEdition> findTournamentEditionForNewMatch(String name, String season, String gender) {
		if(activeTournaments.size() == 0) {
			System.out.println("no active tournaments");
			return Optional.empty();
		} else if(activeTournaments.size() == 1 && activeTournaments.getFirst().getGender().equals(gender)) {
			return findTournamentEditionInTournament(activeTournaments.getFirst(), season);
		} else {
			for(Tournament t : activeTournaments) {
				if(t.getName().equals(name)) {
					return findTournamentEditionInTournament(t, season);
				}
			}
			System.out.println("Could not find tournament, searching with name: " + name + " season: " + season + " gender: " + gender); 
			return Optional.empty();
		}
	}

	private Optional<TournamentEdition> findTournamentEditionInTournament(Tournament t, String season) {
		for(TournamentEdition te : t.getEditions()) {
			System.out.println("TE: " + te.getSeason());
			System.out.println("INPUT: " + season);
			if(te.getSeason().equals(season)) {
				System.out.println("found season");
				return Optional.of(te);
			}
		}
		System.out.println("Could not find tournament edition with that season");
		return Optional.empty();	
	}

    public Tournament createTournament(TournamentCreateRequest request) {
        Tournament tournament = new Tournament(request);
		System.out.println(tournament.getGender());
        
        try {
            this.tournamentMapper.insertTournament(tournament);
            for(TournamentEdition edition : tournament.getEditions()) {
                this.tournamentMapper.insertEdition(edition, tournament.getId());
				for(Roster r : edition.getRosters()) {
					r.setTournament(edition);
					this.rosterMapper.insertRoster(r);
				}
            }
        } catch(DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "tournament already exists");
        }
		this.activeTournaments.add(tournament);	
	
		this.matchService.addNewMatchFolder("src/main/resources/" + request.folderName(), this);
		
		this.activeTournaments.remove(tournament);

		for(TournamentEdition te : tournament.getEditions()) {
			this.updateTournamentEditionDate(te.getId());
			this.statLineService.calculateStatlines(te.getId());
		}
        return tournament;
    }

	public String findTournamentNameById(Long tournamentId) {
		return this.tournamentMapper.findTournamentNameById(tournamentId.longValue());
	}
    
    public TournamentEdition createTournamentEdition(TournamentEditionCreateRequest request) {
        TournamentEdition tournament = new TournamentEdition(request);
		if(tournament.getTournamentId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		}
		if(tournament.getName() == null) {
			tournament.setName(this.findTournamentNameById(tournament.getTournamentId()));
		}
		
		try {
			this.tournamentMapper.insertEdition(tournament, tournament.getTournamentId());
		} catch (DuplicateKeyException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "edition already exists");
		}
		return tournament;
    }
	public List<TournamentEdition> findTournamentEditionsById(long tournamentId) {
		return this.tournamentMapper.findTournamentEditionsByTournamentId(tournamentId);
	}

	public void addMatches(Long tournamentId, String folderName) {
		List<TournamentEdition> editions = this.findTournamentEditionsById(tournamentId);
		if(editions.size() == 0){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot find tournament editions");
		}
		this.matchService.addNewMatchFolder(folderName, null);
	}

	public Optional<TournamentEdition> findTournamentEditionById(long id) {
		return Optional.of(this.tournamentMapper.findTournamentEditionById(id));
	}

	public Optional<TournamentSummary> getTournamentSummaryById(long tournamentId) {
		return Optional.of(this.tournamentMapper.findTournamentSummaryById(tournamentId));
	}

	public Optional<DetailedTournamentEditionSummary> getTournamentEditionSummary(long tournamentId) {
		return Optional.of(this.tournamentMapper.findDetailedTournamentEditionSummary(tournamentId));
	}
	
	public void updateTournamentEditionDate(long tournamentEditionId) {
		LocalDate start = this.matchService.getDateOfFirstMatchByTournamentEditionId(tournamentEditionId);
		LocalDate end = this.matchService.getDateOfLastMatchByTournamentEditionId(tournamentEditionId);
		
		if(start != null && end != null) {
			System.out.println("inserting dates");
			this.tournamentMapper.updateTournamentEditionDates(tournamentEditionId, start, end);
		}
	}

	public void updateTournamentDates(long tournamentId) {
		List<TournamentEdition> tournamentEditions = this.tournamentMapper.findTournamentEditionsByTournamentId(tournamentId);	
		for(TournamentEdition te : tournamentEditions) {
			System.out.println("updating tournament");
			this.updateTournamentEditionDate(te.getId());
		}
	}

    public List<TournamentSummary> getAllTournamentSummaries() {
    	return this.tournamentMapper.findAllTournamentSummaries();
	}

	

}
