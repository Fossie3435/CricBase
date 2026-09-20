package uk.org.cricbase.DTOs;

import uk.org.cricbase.Models.Player;

public class BattingLeaderboardEntry {
	private Player player;
	private BattingStatsSummary statLine;

	public BattingLeaderboardEntry() {}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BattingStatsSummary getStatLine() {
		return statLine;
	}

	public void setStatLine(BattingStatsSummary statLine) {
		this.statLine = statLine;
	}
}
