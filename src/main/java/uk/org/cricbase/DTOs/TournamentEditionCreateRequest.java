package uk.org.cricbase.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uk.org.cricbase.Models.Roster;

/**
 *
 */
public record TournamentEditionCreateRequest(
    Long tournamentId,
    Integer editionNumber,
    LocalDate start,
    LocalDate end,
    String name,
	String season,
	List<RosterCreateRequest> rosters
) {
	public List<Roster> getRosters() {
		ArrayList<Roster> rosters = new ArrayList<>();
		for(RosterCreateRequest rcr : rosters()) {
			rosters.add(rcr.createRoster());
		}
		return rosters;
	}
}

