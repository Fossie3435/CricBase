/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Controllers;

import java.io.File;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uk.org.cricbase.DTOs.DetailedMatchSummary;
import uk.org.cricbase.DTOs.MatchSummary;
import uk.org.cricbase.Services.MatchService;

/**
 *
 * @author Benjamin
 */
@RestController
@RequestMapping ("/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;
    
    @GetMapping
    public ResponseEntity<Void> getAllMatches() {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{matchId}")
    public ResponseEntity<Void> addNewMatch(@PathVariable String matchId) {
        File newMatch = new File("src/main/resources/" + matchId + ".json");
        matchService.addNewMatch(newMatch);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/addgroup/{folderName}")
    public ResponseEntity<Void> addNewMatchFolder(@PathVariable String folderName) {
        matchService.addNewMatchFolder("src/main/resources/" + folderName);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping ("/{id}")
    public  ResponseEntity<DetailedMatchSummary> getMatchById(@PathVariable("id") Long id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/test")
    public ResponseEntity<Void> testMatch() {
        long matchId = 1252696;
        matchService.updateToss(matchService.openNewMatchFromJson(new File("src/main/resources/hnd_json/" + matchId + ".json")));
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/testfolder")
    public ResponseEntity<Void> testMatchFolder() {
        String folderName = "hnd_json";
        matchService.updateTosses("src/main/resources/" + folderName);
        return ResponseEntity.accepted().build();
    }
    
}
