package uk.org.cricbase.DTOs;

import java.util.ArrayList;
import java.util.List;

public class MissingMatch {
	private TournamentEditionSummary tournamentEditionSummary;
	private List<Integer> missingMatches = new ArrayList<>();

	public MissingMatch(TournamentEditionSummary tournamentEditionSummary) {
		this.tournamentEditionSummary = tournamentEditionSummary;
	}

	public TournamentEditionSummary getTournamentEditionSummary() {
		return tournamentEditionSummary;
	}

	public void setTournamentEditionSummary(TournamentEditionSummary tournamentEditionSummary) {
		this.tournamentEditionSummary = tournamentEditionSummary;
	}

	public List<Integer> getMissingMatches() {
		return missingMatches;
	}

	public void setMissingMatches(List<Integer> missingMatches) {
		this.missingMatches = missingMatches;
	}
}
