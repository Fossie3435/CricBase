 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import java.util.HashMap;

/**
 *
 */
public class Team {
    private long id;
    
    private String name;
	private String tricode;
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

	public boolean isPlayerSub(String player) {
		return !this.players.containsKey(player);
	}

	public boolean isPlayerWicketkeeper(String player) {
		return false;
	}

	public String getTricode() {
		return tricode;
	}

	public void setTricode(String tricode) {
		this.tricode = tricode;
	}
}
