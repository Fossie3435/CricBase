package uk.org.cricbase.DTOs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.Models.Roster;
import uk.org.cricbase.Models.TournamentEdition;

public record RosterCreateRequest(
	String name,
	Long tournamentEditionId,
	List<PlayerRosterCreateRequest> players
) {
	public Roster createRoster() {
		Roster roster = new Roster();
		if(tournamentEditionId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		roster.setTournament(new TournamentEdition(tournamentEditionId().longValue()));
		roster.setName(name);
		for(PlayerRosterCreateRequest p : players()) {
			roster.addPlayer(p.getPlayerRosterContainer(roster));
		}

		return roster;
	}
}
