package uk.org.cricbase.DTOs;

import java.util.List;

public class TournamentSummary {
	private long id;
	private String name;
	private List<TournamentEditionSummary> editions;

	public TournamentSummary() {
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

	public List<TournamentEditionSummary> getEditions() {
		return editions;
	}

	public void setEditions(List<TournamentEditionSummary> editions) {
		this.editions = editions;
	}


}

