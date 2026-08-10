/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import uk.org.cricbase.Mappers.PlayerMapper;
import uk.org.cricbase.Models.Player;

/**
 *
 * @author Benjamin
 */
@Service
public class PlayerService {
    private final PlayerMapper playerMapper;
    
    public PlayerService(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }
    
    public Optional<Player> getPlayerById(String id) {
        Player player = playerMapper.findById(id);
        if(player != null) {
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }
    
    public void addNewPlayer(Player player) {
        playerMapper.insert(player);
    }
    
    public Optional<Player> updatePlayer(Player player) {
        if(player.getId() != null) {            
            playerMapper.update(player);
            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }
    
    
        
    
}
