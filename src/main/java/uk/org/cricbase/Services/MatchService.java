/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import uk.org.cricbase.DTOs.DetailedMatchSummary;
import uk.org.cricbase.Mappers.MatchMapper;
import uk.org.cricbase.Models.BattingPerformance;
import uk.org.cricbase.Models.BowlingPerformance;
import uk.org.cricbase.Models.Delivery;
import uk.org.cricbase.Models.FallOfWicket;
import uk.org.cricbase.Models.Inning;
import uk.org.cricbase.Models.Match;
import uk.org.cricbase.Models.Over;
import uk.org.cricbase.Models.Team;

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
    
    
    
    public void addNewMatch(File newMatch) {
        Match match = openNewMatchFromJson(newMatch);
        matchMapper.insertMatch(match);
        long matchId = match.getId();
        Team teamOne = match.getTeamOne();
        matchMapper.insertTeam(teamOne);
        teamOne.getPlayers().values()
                .forEach(playerTeam -> matchMapper.insertPlayerTeam(playerTeam, teamOne));
        matchMapper.insertTeam(match.getTeamTwo());
        Team teamTwo = match.getTeamTwo();
        teamTwo.getPlayers().values()
                .forEach(playerTeam -> matchMapper.insertPlayerTeam(playerTeam, teamTwo));

        List<Inning> innings = match.getInnings();
        for(int i = 0; i < innings.size(); i++) {
            matchMapper.insertInning(innings.get(i));
            long inningId = innings.get(i).getId();
            List<BattingPerformance> battingPerformances = innings.get(i).getBattingPerformances();
            for(int j = 0; j < battingPerformances.size(); j++) {
                matchMapper.insertBattingPerformance(battingPerformances.get(j));
                //System.out.println("Saved Batting Performance @ " + battingPerformances.get(j).getId());
            }
            List<BowlingPerformance> bowlingPerformances = innings.get(i).getBowlingPerformances();
            for(int j = 0; j < bowlingPerformances.size(); j++) {
                matchMapper.insertBowlingPerformance(bowlingPerformances.get(j));
                //System.out.println("Saved Bowling Performance @ " + bowlingPerformances.get(j).getId());
            }
            List<FallOfWicket> fallOfWickets = innings.get(i).getFallOfWickets();
            for(int j = 0; j < fallOfWickets.size(); j++) {
                //System.out.println(fallOfWickets.get(j).getBatterOut().getId());
                matchMapper.insertFallOfWicket(fallOfWickets.get(j));
                //System.out.println("Saved Fall Of Wicket @ " + fallOfWickets.get(j).getId());
            }
            List<Over> overs = innings.get(i).getOvers();
            for(int j = 0; j < overs.size(); j++) {
                matchMapper.insertOver(overs.get(j));
                List<Delivery> deliveries = overs.get(j).getDeliveries();
                for(int k = 0; k < deliveries.size(); k++) {
                    if(deliveries.get(k).getWicket() != null) {
                        matchMapper.insertWicket(deliveries.get(k).getWicket());
                    }
                    matchMapper.insertDelivery(deliveries.get(k));
                }
            }
        }
    }
    public void addNewMatchFolder(String directory) {
        Stream.of(new File(directory).listFiles())
                .forEach(this::addNewMatch);
    }
    
    public Optional<DetailedMatchSummary> getMatchById(Long id) {
        DetailedMatchSummary match = matchMapper.findDetailedMatchSummaryById(id);
        if(match != null) {
            return Optional.of(match);
        } else {
            return Optional.empty();
        }
    }
    
    public Optional<Match> getMatchByInfo(String tournament, String season, int matchNumber) {
        Match match = matchMapper.findMatchByInfo(tournament, season, matchNumber);
        if(match != null) {
            return Optional.of(match);
        } else {
            return Optional.empty();
        }
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
    
}
