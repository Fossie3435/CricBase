package uk.org.cricbase.DTOs;

public class RosterPointsLeaderboardEntry {
	private long id;
	private String name;
	private long tournamentEditionId;
	private int points;
	private int matches;
	private int wins;
	private int losses;
	private int noResults;

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

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getNoResults() {
		return noResults;
	}

	public void setNoResults(int noResults) {
		this.noResults = noResults;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}
}
