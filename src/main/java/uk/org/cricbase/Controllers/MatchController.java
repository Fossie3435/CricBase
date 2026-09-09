/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Controllers;

import java.io.File;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uk.org.cricbase.DTOs.DetailedMatchSummary;
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
          
    @GetMapping ("/{id}")
    public  ResponseEntity<DetailedMatchSummary> getMatchById(@PathVariable("id") Long id) {
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/test")
    public ResponseEntity<Void> testMatch() {
        long matchId = 1252696;
        //long matchId = 1355588;
        matchService.updateDate(matchService.openNewMatchFromJson(new File("src/main/resources/hnd_json/" + matchId + ".json")));
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/testfolder")
    public ResponseEntity<Void> testMatchFolder() {
        String folderName = "hnd_json";
        matchService.updateDates("src/main/resources/" + folderName);
        return ResponseEntity.accepted().build();
    }
    
}
