package uk.org.cricbase.DTOs;

import java.util.List;

public class RosterTournamentEditionEntry {
	private TournamentEditionSummary tournamentEditionSummary;
	private List<RosterSummary> rosterSummary;

	public RosterTournamentEditionEntry() {

	}

	public TournamentEditionSummary getTournamentEditionSummary() {
		return tournamentEditionSummary;
	}

	public void setTournamentEditionSummary(TournamentEditionSummary tournamentEditionSummary) {
		this.tournamentEditionSummary = tournamentEditionSummary;
	}

	public List<RosterSummary> getRosterSummary() {
		return rosterSummary;
	}

	public void setRosterSummary(List<RosterSummary> rosterSummary) {
		this.rosterSummary = rosterSummary;
	}
}
