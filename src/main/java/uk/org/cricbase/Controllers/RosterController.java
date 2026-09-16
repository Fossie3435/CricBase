package uk.org.cricbase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import uk.org.cricbase.DTOs.PlayerRosterCreateRequest;
import uk.org.cricbase.DTOs.RosterCreateRequest;
import uk.org.cricbase.DTOs.RosterSummary;
import uk.org.cricbase.Services.RosterService;

@RestController
@RequestMapping("/rosters")
public class RosterController {
	@Autowired
	private RosterService rosterService;
	
	@GetMapping("")
	public void test() {
	System.out.println("test");
	}

	@PostMapping("")
	public ResponseEntity<Void> addRoster(@RequestBody RosterCreateRequest request) {
		this.rosterService.addNewRoster(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{rosterId}/players")
	public ResponseEntity<Void> addPlayers(@PathParam("rosterId") long rosterId, @RequestBody List<PlayerRosterCreateRequest> request) {
		if(request.size() == 0) {
			return ResponseEntity.badRequest().build();
		}
		this.rosterService.addPlayersToRoster(rosterId, request);	
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{rosterId}")
	public ResponseEntity<RosterSummary> getRosterById(@PathVariable("rosterId") long rosterId) {
		return ResponseEntity.ok(this.rosterService.getRosterById(rosterId));
	}

	@GetMapping("/getall")
	public ResponseEntity<List<RosterSummary>> getAllRosters() {
		return ResponseEntity.ok(this.rosterService.getAllRosters());
	}
}
