package uk.org.cricbase.Models;

import java.time.LocalDate;

/**
 * PlayerRosterContainer
 */
public class PlayerRosterContainer {
	private Roster roster;
	private Player player;
	private LocalDate start;
	private LocalDate end;
	// isInternational
	// Salary Information (how signed)

	public PlayerRosterContainer() {
	}

	public PlayerRosterContainer(Roster roster, Player player, LocalDate start, LocalDate end) {
		this.roster = roster;
		this.player = player;
		this.start = start;
		this.end = end;
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

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}
}

