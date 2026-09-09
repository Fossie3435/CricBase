/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import uk.org.cricbase.Models.TournamentEdition;

/**
 *
 *
 */
public record TournamentCreateRequest (
    String name,
    List<TournamentEditionCreateRequest> editions    
) {
    public List<TournamentEdition> getEditions() {
        ArrayList<TournamentEdition> editions = new ArrayList<>();
        for(int i = 0; i < this.editions.size(); i++) {
            editions.add(getEdition(i));
        }
        return editions;
    }
    public TournamentEdition getEdition(int index) {
        TournamentEditionCreateRequest edition = this.editions.get(index);
        TournamentEdition tournamentEdition = new TournamentEdition(edition.start(), edition.end());
       
        
        if(edition.name() == null) {
            tournamentEdition.setName(this.name);
        } else {
            tournamentEdition.setName(edition.name());
        }
        
        if(edition.editionNumber() == null) {
            tournamentEdition.setEdition(index+1);
        } else {
            tournamentEdition.setEdition(edition.editionNumber().intValue());
        }    
        
        return tournamentEdition;
    }

}
