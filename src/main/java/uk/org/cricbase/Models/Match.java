/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.HashMap;
import uk.org.cricbase.Services.GroundService;
import uk.org.cricbase.Services.PlayerService;


/**
 *
 * @author Benjamin Foster
 */
@JsonIgnoreProperties (ignoreUnknown = true)
public class Match {
    private long id;
    
    private List<Inning> innings; 
    private int overs;
    private String matchFormat;
    private String gender;    
    private int ballsPerOver;
    private int matchNumber;
    private String tournament;
    private String teamType;
    private String season;
    private LocalDate date;
    private String venue;
    private String city;
    
    private Ground ground;
    
    private Team teamOne;
    private Team teamTwo;
    /**
    private Team winner;
    private Team tossWinner;
    private Team tossDecision;
     
    // umpires
    
    **/
    private Map<String, Object> registry;
    
    private List<String>[] nameStrings = new List[2];
    
    public Match() {
        
    }
    
    public void init(PlayerService playerService, GroundService groundService) {
        this.ground = groundService.getGroundByName(venue)
                .orElseGet(() -> (groundService.addNewGround(venue, city)));
        
        HashMap<String, Player> teamOnePlayers = new HashMap<>();
        HashMap<String, Player> teamTwoPlayers = new HashMap<>();
        for(int i = 0; i < nameStrings[0].size(); i++) {
            Player teamOnePlayer = playerService.getPlayerById( (String) registry.getOrDefault(nameStrings[0].get(i), "")).get(); // should be modified to work if no player class is found
            teamOnePlayers.put(nameStrings[0].get(i), teamOnePlayer);
        }
        for(int i = 0; i < nameStrings[1].size(); i++) {
            Player teamTwoPlayer = playerService.getPlayerById((String) registry.getOrDefault(nameStrings[1].get(i), (""))).get();
            teamTwoPlayers.put(nameStrings[1].get(i), teamTwoPlayer);            
        }
        this.teamOne.setPlayers(teamOnePlayers);
        this.teamTwo.setPlayers(teamTwoPlayers);
        
        for(int i = 0; i < innings.size(); i++) {
            innings.get(i).setMatch(this);
            innings.get(i).setTeams(teamOne, teamTwo);
            innings.get(i).generatePerformances();
        }
    }
    
    @JsonSetter("info")
    private void unpackInfo(Map<String, Object> info) {
        this.overs = (int) info.get("overs");
        this.matchFormat = (String) info.get("match_type");
        this.gender = (String) info.get("gender");
        this.ballsPerOver = (int) info.get("balls_per_over");
        //this.dates = (List<Date>) info.get("dates");
        this.teamType = (String) info.get("team_type");
        this.season = (String) info.get("season");
        
        List<String> dates = (List<String>) info.get("dates");
        this.date = LocalDate.parse(dates.get(0));
        
        this.venue = (String) info.get("venue");
        this.city = (String) info.get("city");
        
        this.teamOne = new Team();
        this.teamTwo = new Team();
      
        List<String> teamNames = (List<String>) info.get("teams");
        this.teamOne.setName(teamNames.get(0));
        this.teamTwo.setName(teamNames.get(1));
        this.teamOne.setMatch(this);
        this.teamTwo.setMatch(this);
        
        Map<String, Object> players = (Map<String, Object>) info.get("players");
        this.nameStrings[0] = (List<String>) players.get(teamNames.get(0));
        this.nameStrings[1] = (List<String>) players.get(teamNames.get(1));
        
        // getting team objects for each team
        this.registry = (Map<String, Object>) ((Map<String, Object>) info.get("registry")).get("people"); // gross

        Map<String, Object> event = (Map<String, Object>) info.get("event");
        this.matchNumber = (int) event.get("match_number");
        this.tournament = (String) event.get("tournament");
        
        //Map<String, Object> outcome = (Map<String, Object>) info.get("outcome");
        /**
        if(outcome.get("winner") == null) {
            this.resultType = (String) outcome.get("result");
        } else {
            this.resultType = "result";
            if(this.teamNames[0].equals((String) outcome.get("winner"))) {
                this.winner = teams[0];
            } else {
                this.winner = teams[1];
            }
            // victory margin
            //wickets runs innings 
            // consider result object
        }
        **/
        
        
        // toss handling

        
    }
    public void printScorecard() {
       for(int i = 0; i < innings.size(); i++) {
           innings.get(i).printInningScorecard();
       }  
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Inning> getInnings() {
        return innings;
    }

    public void setInnings(List<Inning> innings) {
        this.innings = innings;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public String getMatchFormat() {
        return matchFormat;
    }

    public void setMatchFormat(String matchFormat) {
        this.matchFormat = matchFormat;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBallsPerOver() {
        return ballsPerOver;
    }

    public void setBallsPerOver(int ballsPerOver) {
        this.ballsPerOver = ballsPerOver;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }
    
    public Map<String, Object> getRegistry() {
        return registry;
    }

    public void setRegistry(Map<String, Object> registry) {
        this.registry = registry;
    }

    public List<String>[] getNameStrings() {
        return nameStrings;
    }

    public void setNameStrings(List<String>[] nameStrings) {
        this.nameStrings = nameStrings;
    }
    
    
}
