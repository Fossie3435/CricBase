/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.org.cricbase.DTOs.DetailedMatchSummary;
import uk.org.cricbase.DTOs.MatchSummary;
import uk.org.cricbase.Mappers.MatchMapper;
import uk.org.cricbase.Models.BattingPerformance;
import uk.org.cricbase.Models.BowlingPerformance;
import uk.org.cricbase.Models.Delivery;
import uk.org.cricbase.Models.FallOfWicket;
import uk.org.cricbase.Models.Inning;
import uk.org.cricbase.Models.Match;
import uk.org.cricbase.Models.Over;
import uk.org.cricbase.Models.Team;
import uk.org.cricbase.Models.TournamentEdition;

/**
 *
 * @author Benjamin
 */
@Service
public class MatchService {
    
    private final MatchMapper matchMapper;
    private final PlayerService playerService;
    private final GroundService groundService;
    
    
    
    public MatchService(MatchMapper matchMapper, PlayerService playerService, GroundService groundService) {
        this.matchMapper = matchMapper;
        this.playerService = playerService;
        this.groundService = groundService;
    }
    
    public Match openNewMatchFromJson(File newMatch) {
        Match match = new Match();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            match = objectMapper.readValue(newMatch, Match.class);
            match.init(playerService, groundService);
        } catch (IOException e) {
			System.out.println(newMatch.toString());
            e.printStackTrace();
        }
        System.out.println("Match Retrieved from JSON");
        return match;
    }
    
    public Stream<Match> openMatchFolderFromJson(String directory) {
        return Stream.of(new File(directory)
                .listFiles())
                .map(this::openNewMatchFromJson);
    }
    
    
    
    public void addNewMatch(File newMatch, List<TournamentEdition> editions) {
        Match match = openNewMatchFromJson(newMatch);

		for(TournamentEdition edition : editions) {
			if(match.getDate().isAfter(edition.getStart()) && match.getDate().isBefore(edition.getEnd())) {
				match.setTournament(edition);
				break;
			}
		}
        matchMapper.insertMatch(match);

        Team teamOne = match.getTeamOne();
        matchMapper.insertTeam(teamOne);
        teamOne.getPlayers().values()
                .forEach(playerTeam -> matchMapper.insertPlayerTeam(playerTeam, teamOne));
        
        Team teamTwo = match.getTeamTwo();
		matchMapper.insertTeam(match.getTeamTwo());
        teamTwo.getPlayers().values()
                .forEach(playerTeam -> matchMapper.insertPlayerTeam(playerTeam, teamTwo));
		
		matchMapper.updateToss(match);
		matchMapper.updateWin(match);

        for(Inning inning : match.getInnings()) {
            matchMapper.insertInning(inning);
            for(BattingPerformance battingPerformance : inning.getBattingPerformances()) {
                matchMapper.insertBattingPerformance(battingPerformance);
                //System.out.println("Saved Batting Performance @ " + battingPerformances.get(j).getId());
            }
            for(BowlingPerformance bowlingPerformance : inning.getBowlingPerformances()) {
                matchMapper.insertBowlingPerformance(bowlingPerformance);
                //System.out.println("Saved Bowling Performance @ " + bowlingPerformances.get(j).getId());
            }
            for(FallOfWicket fallOfWicket : inning.getFallOfWickets()) {
                //System.out.println(fallOfWickets.get(j).getBatterOut().getId());
                matchMapper.insertFallOfWicket(fallOfWicket);
                //System.out.println("Saved Fall Of Wicket @ " + fallOfWickets.get(j).getId());
            }
            for(Over over : inning.getOvers()) {
                matchMapper.insertOver(over);
                for(Delivery delivery : over.getDeliveries()) {
                    if(delivery.getWicket() != null) {
                        matchMapper.insertWicket(delivery.getWicket());
                    }
                    matchMapper.insertDelivery(delivery);
                }
            }
        }
    }
    public void addNewMatchFolder(String directory, List<TournamentEdition> editions) {
        Stream.of(new File(directory).listFiles())
			.filter(path -> path.getPath().toString().endsWith(".json"))
			.forEach(file -> addNewMatch(file, editions));
    }
    
    public Optional<DetailedMatchSummary> getMatchById(Long id) {
        DetailedMatchSummary match = matchMapper.findDetailedMatchSummaryById(id);
        if(match != null) {
            return Optional.of(match);
        } else {
            return Optional.empty();
        }
    }

	public List<MatchSummary> getMatchSummariesByTournamentEditionId(long id) {
		return this.matchMapper.findMatchSummariesByTournamentEditionId(id);
	}
    
    public Optional<Match> getMatchByInfo(String tournament, String season, int matchNumber) {
  /**      Match match = matchMapper.findMatchByInfo(tournament, season, matchNumber);
        if(match != null) {
            return Optional.of(match);
        } else {
            return Optional.empty();
        } **/
		return Optional.empty();
    }
    
    public void updateMatchGround(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setGround(match.getGround());
            this.matchMapper.updateGround(matchDb.get());
        }
        
    }
    
    public void updateMatchGrounds(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateMatchGround);
    }
    
    public void updatePlayerOfTheMatch(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setPlayerOfTheMatch(match.getPlayerOfTheMatch());
            this.matchMapper.updatePlayerOfTheMatch(matchDb.get());
        }
    }
    
    public void updatePlayersOfTheMatch(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updatePlayerOfTheMatch);
    }
    
    public void updateToss(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setTossDecision(match.getTossDecision());
            matchDb.get().setTossWinner(match.getTossWinner()); 
            matchDb.get().getTossWinner().setId(this.matchMapper.findTeamIdByInfo(matchDb.get().getId(), match.getTossWinner().getName()));
            
            this.matchMapper.updateToss(matchDb.get());
        }
    }
    
    public void updateTosses(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateToss);
    }
    
    public void updateWin(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            if(match.getWinner() != null) {
                matchDb.get().setWinner(match.getWinner());
                matchDb.get().setRunsMargin(match.getRunsMargin());
                matchDb.get().setInningsMargin(match.getInningsMargin());
                matchDb.get().setWicketsMargin(match.getWicketsMargin());

                matchDb.get().getWinner().setId(this.matchMapper.findTeamIdByInfo(matchDb.get().getId(), match.getWinner().getName()));

                this.matchMapper.updateWin(matchDb.get());
            }
        }
    }
    
    public void updateWins(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateWin);
    }
    
    public void updateMatchResult(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setResultType(match.getResultType());
            
            this.matchMapper.updateResultType(matchDb.get());
        }
    }
    
    public void updateMatchResults(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateMatchResult);
    }
    
    public void updateMatchType(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setMatchType(match.getMatchType());
            
            this.matchMapper.updateMatchType(matchDb.get());
        } else {
            System.out.println("Missing Match in DB!");
        }
    }
    
    public void updateMatchTypes(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateMatchType);
    }
    
    public void updateDate(Match match) {
        Optional<Match> matchDb = this.getMatchByInfo(match.getGender(), match.getSeason(), match.getMatchNumber());
        if(matchDb.isPresent()) {
            matchDb.get().setDate(match.getDate());
             
            this.matchMapper.updateDate(matchDb.get());
        } else {
            System.out.println("Missing Match in DB!");
        }
    }
    
    public void updateDates(String directory) {
        openMatchFolderFromJson(directory).forEach(this::updateDate);
    }
    
    
    
}
