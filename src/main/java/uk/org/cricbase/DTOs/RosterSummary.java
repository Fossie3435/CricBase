package uk.org.cricbase.DTOs;

import java.util.List;

public class RosterSummary {
	private long id;
	private String name;
	private long tournamentEditionId;

	private List<PlayerRosterSummary> players;

	public RosterSummary() {}

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

	public long getTournamentEditionId() {
		return tournamentEditionId;
	}

	public void setTournamentEditionId(long tournamentEditionId) {
		this.tournamentEditionId = tournamentEditionId;
	}

	public List<PlayerRosterSummary> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerRosterSummary> players) {
		this.players = players;
	}
}
