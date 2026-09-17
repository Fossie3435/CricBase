package uk.org.cricbase.DTOs;

import uk.org.cricbase.Models.Player;

public class StatLeaderboardEntry {
	private int entry;
	private Player player;
	private float stat;
	
	public StatLeaderboardEntry() {}

	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public float getStat() {
		return stat;
	}

	public void setStat(float stat) {
		this.stat = stat;
	}
}

