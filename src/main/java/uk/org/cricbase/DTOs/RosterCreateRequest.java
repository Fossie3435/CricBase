package uk.org.cricbase.DTOs;

import java.util.List;


import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

public record RosterCreateRequest(
	String name,
	Long tournamentEditionId,
	List<PlayerRosterCreateRequest> players
) {
	public Roster createRoster() {
		Roster roster = new Roster();
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
