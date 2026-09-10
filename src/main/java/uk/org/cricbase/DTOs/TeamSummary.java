package uk.org.cricbase.DTOs;

import java.util.List;
import uk.org.cricbase.Models.Player;

/**
 *
 */
public class TeamSummary {
    private String name;
    private List<Player> players;
    
    public TeamSummary() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
