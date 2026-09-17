package uk.org.cricbase.DTOs;

import java.util.List;

public class StatLeaderboard {
	private String name;
	private String statName;
	private List<StatLeaderboardEntry> entries;
	private int decimalPlaces;

	public StatLeaderboard(String name, String statName) {
		this.name = name;
		this.statName = statName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatName() {
		return statName;
	}

	public void setStatName(String statName) {
		this.statName = statName;
	}

	public List<StatLeaderboardEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<StatLeaderboardEntry> entries) {
		this.entries = entries;
	}

    public void generateEntryNumbers() {
		float lastEntry = -1;
		int nSinceLastAssigned = 0;
		for(int i = 0; i < this.entries.size(); i++) {
			if(lastEntry == this.entries.get(i).getStat()) {
				nSinceLastAssigned++;
			} else {
				nSinceLastAssigned = 0;
			}
			lastEntry = this.entries.get(i).getStat();
			this.entries.get(i).setEntry(i+1 - nSinceLastAssigned);
		}
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}
}
