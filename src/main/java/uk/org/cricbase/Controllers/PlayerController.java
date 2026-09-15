/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.org.cricbase.Models.Player;
import uk.org.cricbase.Services.PlayerService;

/**
 *
 */
@RestController
@RequestMapping ("/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
   
	@GetMapping("/missingnicknames")
	public ResponseEntity<List<Player>> getActivePlayersWithoutNickname() {
		return ResponseEntity.ok(playerService.findActivePlayersWithoutNicknames());
	}

	@GetMapping("/search")
	public ResponseEntity<List<Player>> searchPlayers(@RequestParam String search) {
		return ResponseEntity.ok(playerService.searchPlayers(search));
	}

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

	@PostMapping("/addnickname") 
	public void updateNickname(@RequestBody Player player) {
		if(player.getId() == null || player.getNickname() == null) {
		}
		playerService.updateNickname(player);
	}
    
    @PatchMapping("/")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        if(player != null) {
            return playerService.updatePlayer(player)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            
        }
        return ResponseEntity.badRequest().build();
    } 
}
