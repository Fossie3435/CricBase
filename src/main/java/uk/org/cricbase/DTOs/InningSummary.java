/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

import uk.org.cricbase.Models.Inning;

/**
 *
 * @author Benjamin
 */
public class InningSummary {
    private String teamName;
    private int runs;
    private int wickets;

    public InningSummary() {}
    
    public InningSummary(String teamName, int runs, int wickets) {
        this.teamName = teamName;
        this.runs = runs;
        this.wickets = wickets;
    }
    public InningSummary(Inning inning) {
        this.teamName = inning.getTeamName();
        this.runs = inning.getTotalRuns();
        this.wickets = inning.getWicketsTaken();    
    }

    public String getTeamName() {
        return teamName;
    }

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
}
