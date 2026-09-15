package uk.org.cricbase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.org.cricbase.DTOs.CareerSummary;
import uk.org.cricbase.Services.StatLineService;

@RestController
@RequestMapping("/players/stats")
public class StatsController {
	@Autowired
	private StatLineService statlineService;
	
	public StatsController() {}

	@GetMapping("/tournament")
	public void updateStatlinesForTournamentEdition(@RequestParam long id) {
		this.statlineService.calculateStatlines(id);
	}
	
	@GetMapping("/{playerId}")
	public ResponseEntity<CareerSummary> getCareerStats(@PathVariable String playerId) {
		return ResponseEntity.ok(this.statlineService.getCareerStats(playerId));
	}	

	@GetMapping("/test")
	public void test() {
		this.statlineService.calculateStatlinesForPlayer(5, "1f1b4c89");
	}
}
