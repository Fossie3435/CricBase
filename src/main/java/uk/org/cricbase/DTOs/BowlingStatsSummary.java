package uk.org.cricbase.DTOs;

public class BowlingStatsSummary {
	private TournamentEditionSummary tournament;	
	private int matches;
	private int innings;
	private int ballsBowled;
	private int runsConceded;
	private int wickets;
	private int maidens;
	private int foursConceded;
	private int sixesConceded;
	private int wides;
	private int noBalls;

	public BowlingStatsSummary() {
	}

	public TournamentEditionSummary getTournament() {
		return tournament;
	}

	public void setTournament(TournamentEditionSummary tournament) {
		this.tournament = tournament;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getInnings() {
		return innings;
	}

	public void setInnings(int innings) {
		this.innings = innings;
	}

	public int getRunsConceded() {
		return runsConceded;
	}

	public void setRunsConceded(int runsConceded) {
		this.runsConceded = runsConceded;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getMaidens() {
		return maidens;
	}

	public void setMaidens(int maidens) {
		this.maidens = maidens;
	}

	public int getFoursConceded() {
		return foursConceded;
	}

	public void setFoursConceded(int foursConceded) {
		this.foursConceded = foursConceded;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getNoBalls() {
		return noBalls;
	}

	public void setNoBalls(int noBalls) {
		this.noBalls = noBalls;
	}

	public int getBallsBowled() {
		return ballsBowled;
	}

	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}

	public int getSixesConceded() {
		return sixesConceded;
	}

	public void setSixesConceded(int sixesConceded) {
		this.sixesConceded = sixesConceded;
	}
}
