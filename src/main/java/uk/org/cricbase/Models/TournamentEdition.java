/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import java.time.LocalDate;

import uk.org.cricbase.DTOs.TournamentEditionCreateRequest;

/**
 *
 *
 */
public class TournamentEdition {
    private long id;
    private String name;
    private int edition;
    private LocalDate start;
    private LocalDate end;
    private Long tournamentId;
	private String season;
    // winning roster
    // season?
   

    public TournamentEdition(String name, int edition) {
        this.name = name;
        this.edition = edition;
    }
    
    public TournamentEdition() {
	}

	public TournamentEdition(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    public TournamentEdition(String name, int edition, LocalDate start, LocalDate end) {
        this.name = name;
        this.edition = edition;
        this.start = start;
        this.end = end;
    }
    
    public TournamentEdition(TournamentEditionCreateRequest request) {
        this.name = request.name();
        this.edition = request.editionNumber();
        this.start = request.start();
        this.end = request.end();
        this.tournamentId = request.tournamentId();
    }

    public TournamentEdition(long id) {
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

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
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
	public Long getTournamentId() {
		return this.tournamentId;
	}
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
}

