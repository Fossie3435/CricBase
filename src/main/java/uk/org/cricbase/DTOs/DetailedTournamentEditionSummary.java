package uk.org.cricbase.DTOs;

import java.time.LocalDate;
import java.util.List;

public class DetailedTournamentEditionSummary {
	private long id;
	private String name;
	private LocalDate start;
	private LocalDate end;
	private Integer edition; 
	private Long tournamentId;
	private String season;
	private List<MatchSummary> matches;
	

	public DetailedTournamentEditionSummary() {}

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

	public Long getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public List<MatchSummary> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchSummary> matches) {
		this.matches = matches;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
}
