/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import java.util.List;
import uk.org.cricbase.DTOs.TournamentCreateRequest;

/**
 *
 * 
 */
public class Tournament {
    private long id;
    private String name;
	private String gender;
    private List<TournamentEdition> editions;
    
    public Tournament(TournamentCreateRequest request) {
        this.name = request.name();
		this.gender = request.gender();
        this.editions = request.getEditions();  
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

    public List<TournamentEdition> getEditions() {
        return editions;
    }

    public void setEditions(List<TournamentEdition> editions) {
        this.editions = editions;
    }
    
    public void addEdition(TournamentEdition edition) {
        this.editions.add(edition);
    }

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
