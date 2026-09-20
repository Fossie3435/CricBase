package uk.org.cricbase.Models;

import uk.org.cricbase.DTOs.BowlingPerformanceSummary;

public class BowlingStatLine {
	private String bowlerId;
	private long tournamentEditionId;

	private int matches;
	private int innings;
	private int wickets;
	private int ballsBowled;
	private int runsConceded;
	private int maidens;
	private int noBalls;
	private int wides;
	private int foursConceded;
	private int sixesConceded;
	private BowlingPerformance best;

	public BowlingStatLine(String playerId, long tournamentEditionId) {
		this.bowlerId = playerId;
		this.tournamentEditionId = tournamentEditionId;
		this.matches = 0;
		this.innings = 0;
		this.wickets = 0;
		this.ballsBowled = 0;
		this.runsConceded = 0;
		this.maidens = 0;
		this.noBalls = 0;
		this.wides = 0;
		this.foursConceded = 0;
		this.sixesConceded = 0;
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

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getBallsBowled() {
		return ballsBowled;
	}

	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}

	public int getRunsConceded() {
		return runsConceded;
	}

	public void setRunsConceded(int runsConceded) {
		this.runsConceded = runsConceded;
	}

	public int getMaidens() {
		return maidens;
	}

	public void setMaidens(int maidens) {
		this.maidens = maidens;
	}

	public int getNoBalls() {
		return noBalls;
	}

	public void setNoBalls(int noBalls) {
		this.noBalls = noBalls;
	}

	public int getWides() {
		return wides;
	}

	public void setWides(int wides) {
		this.wides = wides;
	}

	public int getFoursConceded() {
		return foursConceded;
	}

	public void setFoursConceded(int foursConceded) {
		this.foursConceded = foursConceded;
	}

	public int getSixesConceded() {
		return sixesConceded;
	}

	public void setSixesConceded(int sixesConceded) {
		this.sixesConceded = sixesConceded;
	}

	public long getTournamentEditionId() {
		return tournamentEditionId;
	}

	public void setTournamentEditionId(long tournamentEditionId) {
		this.tournamentEditionId = tournamentEditionId;
	}

    public void add(BowlingPerformanceSummary bp) {
		this.innings++;
		this.ballsBowled += bp.getBallsBowled();
		this.runsConceded += bp.getRunsConceded();
		this.wickets += bp.getWicketsTaken();
		this.maidens += bp.getMaidens();
		this.foursConceded += bp.getFoursConceded();
		this.sixesConceded += bp.getSixesConceded();
		this.noBalls += bp.getNoballs();
		this.wides += bp.getWides();
    }

	public String getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(String bowlerId) {
		this.bowlerId = bowlerId;
	}

	public BowlingPerformance getBest() {
		return best;
	}

	public void setBest(BowlingPerformance best) {
		this.best = best;
	}
}
