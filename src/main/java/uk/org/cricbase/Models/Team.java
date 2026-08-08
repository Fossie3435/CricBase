 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import java.util.HashMap;

/**
 *
 * @author Benjamin Foster <fosterbp@lancaster.ac.uk>
 */
public class Team {
    private long id;
    
    private String name;
    private HashMap<String, Player> players;
    
    private Match match;
    
    public Team() {
        
    }
    
    public Team(String name, Match match) {
        this.name = name;
        this.match = match;
    }
    public Player getPlayer(String player) {
        return players.getOrDefault(player, null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
    
    
    
}
