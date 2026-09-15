package uk.org.cricbase.Models;

import java.time.LocalDate;
import java.util.List;

//
public class Roster {
	private long id;

	private List<PlayerRosterContainer> players;
	private String name;
	private TournamentEdition tournament;
	// organisation
	
	public Roster() {}
	
	public Roster(String name, TournamentEdition tournament) {
		this.name = name;
		this.tournament = tournament;
	}

	public void addPlayer(Player player, LocalDate start, LocalDate end) {
		this.players.add(new PlayerRosterContainer(this, player, start, end));		
	}

	public void addPlayer(PlayerRosterContainer player) {
		this.players.add(player);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PlayerRosterContainer> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerRosterContainer> players) {
		this.players = players;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TournamentEdition getTournament() {
		return tournament;
	}

	public void setTournament(TournamentEdition tournament) {
		this.tournament = tournament;
	}
}

