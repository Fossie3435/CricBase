package uk.org.cricbase.DTOs;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import uk.org.cricbase.Models.Player;
import uk.org.cricbase.Models.PlayerRosterContainer;
import uk.org.cricbase.Models.Roster;

public record PlayerRosterCreateRequest(
	String playerId,
	LocalDate start,
	LocalDate end
) {

    public PlayerRosterContainer getPlayerRosterContainer(Roster roster) {
		if(this.playerId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST) ; 
		}
		return new PlayerRosterContainer(roster, new Player(playerId()), start, end);
	}
}
