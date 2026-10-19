package uk.org.cricbase.DTOs;

public class RosterPointsLeaderboardEntry {
	private long id;
	private String name;
	private long tournamentEditionId;
	private int points;

	public RosterPointsLeaderboardEntry() {
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

	public long getTournamentEditionId() {
		return tournamentEditionId;
	}

	public void setTournamentEditionId(long tournamentEditionId) {
		this.tournamentEditionId = tournamentEditionId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
