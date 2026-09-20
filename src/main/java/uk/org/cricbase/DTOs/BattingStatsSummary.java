package uk.org.cricbase.DTOs;

public class BattingStatsSummary {
	private TournamentEditionSummary tournament;
	private int matches;
	private int innings;
	private int runs;
	private int ballsFaced;
	private int fours;
	private int sixes;
	private int dismissals;
	private float strikeRate;
	private float average;
	private BattingPerformanceSummary best;
	public BattingStatsSummary() {}

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

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getBallsFaced() {
		return ballsFaced;
	}

	public void setBallsFaced(int ballsFaced) {
		this.ballsFaced = ballsFaced;
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

	public int getDismissals() {
		return dismissals;
	}

	public void setDismissals(int dismissals) {
		this.dismissals = dismissals;
	}

	public float getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(float strikeRate) {
		this.strikeRate = strikeRate;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public BattingPerformanceSummary getBest() {
		return best;
	}

	public void setBest(BattingPerformanceSummary best) {
		this.best = best;
	}
}
