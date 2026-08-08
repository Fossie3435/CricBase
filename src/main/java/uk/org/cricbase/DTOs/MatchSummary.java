/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

import java.util.List;
import uk.org.cricbase.Models.Match;

/**
 *
 * @author Benjamin
 */
public class MatchSummary {
    private long id;
    private String tournament;
    private String season;
    private String matchType;
    private String gender;
    private String teamType;
    private int matchNumber;
    private int overs;
    private List<InningSummary> innings;
    
    public MatchSummary() {    
    }
    
    public MatchSummary(Match match) {
        this.id = match.getId();
        this.tournament = match.getTournament();
        this.season = match.getSeason();
        this.matchType = match.getMatchType();
        this.gender = match.getGender();
        this.teamType = match.getTeamType();
        this.matchNumber = match.getMatchNumber();
        this.innings = match.getInnings().stream()
                .map(InningSummary::new)
                .toList();
    }

    public long getId() {
        return id;
    }

    public String getTournament() {
        return tournament;
    }

    public String getSeason() {
        return season;
    }

    public String getMatchType() {
        return matchType;
    }

    public String getGender() {
        return gender;
    }

    public String getTeamType() {
        return teamType;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public List<InningSummary> getInnings() {
        return innings;
    }

    public void setInnings(List<InningSummary> innings) {
        this.innings = innings;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    } 
}
