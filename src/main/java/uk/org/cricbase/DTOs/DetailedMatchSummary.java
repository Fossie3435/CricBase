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
public class DetailedMatchSummary {
    MatchSummary matchSummary;
    List<DetailedInningSummary> innings;
    List<TeamSummary> teams;
    // TOSS
    public DetailedMatchSummary() {
    }

    public MatchSummary getMatchSummary() {
        return matchSummary;
    }

    public void setMatchSummary(MatchSummary matchSummary) {
        this.matchSummary = matchSummary;
    }

    public List<DetailedInningSummary> getInnings() {
        return innings;
    }

    public void setInnings(List<DetailedInningSummary> innings) {
        this.innings = innings;
    }

    public List<TeamSummary> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamSummary> teams) {
        this.teams = teams;
    }
    
}
