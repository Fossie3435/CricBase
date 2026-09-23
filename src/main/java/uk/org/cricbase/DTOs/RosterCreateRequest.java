package uk.org.cricbase.DTOs;

import java.util.List;

import uk.org.cricbase.Models.Organisation;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

public record RosterCreateRequest(
	String name,
	String tricode,
	Long tournamentEditionId,
	Long organisationId,
	List<PlayerRosterCreateRequest> players
) {
	public Roster createRoster() {
		Roster roster = new Roster();
		roster.setName(name);
		roster.setTricode(tricode);
		if(organisationId() != null) {
			roster.setOrganisation(new Organisation(organisationId.longValue()));
		}
		if(players != null) {
			for(PlayerRosterCreateRequest p : players()) {
				PlayerRosterContainer player = p.getPlayerRosterContainer(roster);
				roster.addPlayer(player);
			}
		}
		return roster;
	}
}
