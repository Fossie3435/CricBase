package uk.org.cricbase.Models;

import java.util.List;

public class Organisation {
	private long id;
	private String name;
	private List<Roster> rosters;

	public Organisation() {
	}
	
	public Organisation(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Roster> getRosters() {
		return rosters;
	}

	public void setRosters(List<Roster> rosters) {
		this.rosters = rosters;
	}
}
