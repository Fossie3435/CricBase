package uk.org.cricbase.DTOs;

import java.time.LocalDate;

public class TournamentEditionSummary {
	private long id;
	private String name;
	private LocalDate start;
	private LocalDate end;
	private Integer edition;

	public TournamentEditionSummary() {
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

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}
}
