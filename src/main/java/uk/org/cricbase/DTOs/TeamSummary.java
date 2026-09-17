package uk.org.cricbase.DTOs;

import java.util.List;
import uk.org.cricbase.Models.Player;

/**
 *
 */
public class TeamSummary {
	private long id;
    private String name;
	private String tricode;
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

    public String getTricode() {
		return tricode;
	}

	public void setTricode(String tricode) {
		this.tricode = tricode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
