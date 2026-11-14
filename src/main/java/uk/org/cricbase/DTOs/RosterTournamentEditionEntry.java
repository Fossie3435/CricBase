package uk.org.cricbase.DTOs;

import java.util.List;

public class RosterTournamentEditionEntry {
	private TournamentEditionSummary tournamentEditionSummary;
	private List<RosterSummary> rosterSummaries;

	public RosterTournamentEditionEntry() {

	}

	public TournamentEditionSummary getTournamentEditionSummary() {
		return tournamentEditionSummary;
	}

	public void setTournamentEditionSummary(TournamentEditionSummary tournamentEditionSummary) {
		this.tournamentEditionSummary = tournamentEditionSummary;
	}

	public List<RosterSummary> getRosterSummaries() {
		return rosterSummaries;
	}

	public void setRosterSummaries(List<RosterSummary> rosterSummaries) {
		this.rosterSummaries = rosterSummaries;
	}
}
