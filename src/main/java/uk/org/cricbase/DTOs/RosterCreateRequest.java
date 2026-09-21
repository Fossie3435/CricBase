package uk.org.cricbase.DTOs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.Models.PlayerRosterContainer;
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
			System.out.println("no tournamentEditionId");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		roster.setTournament(new TournamentEdition(tournamentEditionId().longValue()));
		roster.setName(name);
		if(players != null) {
			for(PlayerRosterCreateRequest p : players()) {
				PlayerRosterContainer player = p.getPlayerRosterContainer(roster);
				roster.addPlayer(player);
			}
		}
		return roster;
	}
}
