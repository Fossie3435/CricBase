package uk.org.cricbase.Models;

import uk.org.cricbase.DTOs.BattingPerformanceSummary;

public class BattingStatLine {
	private String batterId;
	private long tournamentEditionId;
	
	private int matches;
	private int innings;
	private int ballsFaced;
	private int runsScored;
	private int dismissals;
	private int fours;
	private int sixes;
	
	public BattingStatLine(String playerId, long tournamentId) {
		this.batterId = playerId;
		this.tournamentEditionId = tournamentId;
		this.matches = 0;
		this.innings = 0;
		this.runsScored = 0;
		this.ballsFaced = 0;
		this.fours = 0;
		this.sixes = 0;
		this.dismissals = 0;
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

	public int getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(int ballsFaced) {
		this.ballsFaced = ballsFaced;
	}

	public int getRunsScored() {
		return runsScored;
	}

	public void setRunsScored(int runsScored) {
		this.runsScored = runsScored;
	}

	public int getDismissals() {
		return dismissals;
	}

	public void setDismissals(int dismissals) {
		this.dismissals = dismissals;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
	

    public void add(BattingPerformanceSummary bp) {
    	this.runsScored += bp.getRuns();
		this.ballsFaced += bp.getBallsFaced();
		this.fours += bp.getFours();
		this.sixes += bp.getSixes();
		this.innings++;
		if(bp.isDismissed()) {
			this.dismissals++;
		}
	}

	public String getBatterId() {
		return batterId;
	}

	public void setBatterId(String batterId) {
		this.batterId = batterId;
	}

	public long getTournamentEditionId() {
		return tournamentEditionId;
	}

	public void setTournamentEditionId(long tournamentEditionId) {
		this.tournamentEditionId = tournamentEditionId;
	}
}
