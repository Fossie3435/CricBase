package uk.org.cricbase.DTOs;

import java.time.LocalDate;

import uk.org.cricbase.Models.Player;

public class PlayerRosterSummary {
	private Player player;

	private LocalDate start;
	private LocalDate end;
	public PlayerRosterSummary() {
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
}
