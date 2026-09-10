package uk.org.cricbase.DTOs;

import java.util.List;

/**
 *
 */
public class DetailedMatchSummary {
    MatchSummary matchSummary;
    List<DetailedInningSummary> innings;
    List<TeamSummary> teams;
    
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
