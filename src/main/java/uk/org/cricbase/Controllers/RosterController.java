package uk.org.cricbase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import uk.org.cricbase.DTOs.PlayerRosterCreateRequest;
import uk.org.cricbase.Services.RosterService;

@RestController
@RequestMapping("/rosters")
public class RosterController {
	@Autowired
	private RosterService rosterService;

	@PostMapping("/{rosterId}/players")
	public ResponseEntity<Void> addPlayers(@PathParam("rosterId") long rosterId, List<PlayerRosterCreateRequest> request) {
		if(request.size() == 0) {
			return ResponseEntity.badRequest().build();
		}
		this.rosterService.addPlayersToRoster(rosterId, request);	
		return ResponseEntity.ok().build();
	}
}
