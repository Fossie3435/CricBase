/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import java.time.LocalDate;
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
import uk.org.cricbase.Mappers.TournamentMapper;
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

    public TournamentService(TournamentMapper tournamentMapper, MatchService matchService) {
        this.tournamentMapper = tournamentMapper;
        this.matchService = matchService;
    }
    
    public Tournament createTournament(TournamentCreateRequest request) {
        Tournament tournament = new Tournament(request);
        
        try {
            this.tournamentMapper.insertTournament(tournament);
            for(TournamentEdition edition : tournament.getEditions()) {
                this.tournamentMapper.insertEdition(edition, tournament.getId());
            }
        } catch(DuplicateKeyException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "tournament already exists");
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
		this.matchService.addNewMatchFolder(folderName, editions);
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


}
