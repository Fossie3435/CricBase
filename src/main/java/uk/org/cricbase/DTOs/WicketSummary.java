/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

/**
 *
 * @author Benjamin
 */
public class WicketSummary {
    private String batterOut;
    private String bowler;
    private String dismissalType;
    
    public WicketSummary() {
        
    }

    public String getBatterOut() {
        return batterOut;
    }

    public void setBatterOut(String batterOut) {
        this.batterOut = batterOut;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getDismissalType() {
        return dismissalType;
    }

    public void setDismissalType(String dismissalType) {
        this.dismissalType = dismissalType;
    }
    
}
