package uk.org.cricbase.DTOs;

import uk.org.cricbase.Models.Player;

public class BowlingLeaderboardEntry {
	private Player player;
	private BowlingStatsSummary statLine;

	public BowlingLeaderboardEntry() {}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BowlingStatsSummary getStatLine() {
		return statLine;
	}

	public void setStatLine(BowlingStatsSummary statLine) {
		this.statLine = statLine;
	}
}
