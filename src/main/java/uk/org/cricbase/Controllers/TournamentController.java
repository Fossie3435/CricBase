/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.org.cricbase.DTOs.DetailedTournamentEditionSummary;
import uk.org.cricbase.DTOs.TournamentCreateRequest;
import uk.org.cricbase.DTOs.TournamentEditionCreateRequest;
import uk.org.cricbase.DTOs.TournamentSummary;
import uk.org.cricbase.Services.TournamentService;

/**
 *
 *
 */
@RestController
@RequestMapping ("/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;
    
    @PostMapping
    public ResponseEntity<Void> addNewTournament(@RequestBody TournamentCreateRequest request) {
        this.tournamentService.createTournament(request);
        return ResponseEntity.ok().build();
        
    }
    
    @PostMapping("/edition")
    public ResponseEntity<Void> addNewEdition(@RequestBody TournamentEditionCreateRequest request) {
        this.tournamentService.createTournamentEdition(request);
        return ResponseEntity.ok().build();
    }

	@PostMapping("/{tournamentId}/matches")
	public ResponseEntity<Void> addNewGroup(@PathVariable Long tournamentId) {
		System.out.println("src/main/resources/" + "hnd_json");
		String folderName = "hnd_json";
		this.tournamentService.addMatches(tournamentId, "src/main/resources/" + folderName);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{tournamentId}")
	public ResponseEntity<TournamentSummary> getTournamentById(@PathVariable long tournamentId) {
		return tournamentService.getTournamentSummaryById(tournamentId)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/edition/{tournamentEditionId}")
	public ResponseEntity<DetailedTournamentEditionSummary> getDetailedTournamentEditionSummary(@PathVariable long tournamentEditionId) {
		return tournamentService.getTournamentEditionSummary(tournamentEditionId)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/updatedates")
	public ResponseEntity<Void> updateDates() {
		System.out.println("test");
		this.tournamentService.updateTournamentDates(1);
		this.tournamentService.updateTournamentDates(4);
		return ResponseEntity.ok().build();
	}
	
}
