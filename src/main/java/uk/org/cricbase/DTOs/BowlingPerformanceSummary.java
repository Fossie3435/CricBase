/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

/**
 *
 * @author Benjamin
 */
public class BowlingPerformanceSummary {
    private String bowlerId;
    private int bowlingPosition;
    private int maidens;
    private int ballsBowled;
    private int runsConceded;
    private int wicketsTaken;
    private int dots;
    private int foursConceded;
    private int sixesConceded;
    private int wides;
    private int noballs;
    
    public BowlingPerformanceSummary() {
        
    }

    public String getBowlerId() {
        return bowlerId;
    }

    public void setBowlerId(String bowlerId) {
        this.bowlerId = bowlerId;
    }

    public int getBowlingPosition() {
        return bowlingPosition;
    }

    public void setBowlingPosition(int bowlingPosition) {
        this.bowlingPosition = bowlingPosition;
    }

    public int getMaidens() {
        return maidens;
    }

    public void setMaidens(int maidens) {
        this.maidens = maidens;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public int getFoursConceded() {
        return foursConceded;
    }

    public void setFoursConceded(int foursConceded) {
        this.foursConceded = foursConceded;
    }

    public int getSixesConceded() {
        return sixesConceded;
    }

    public void setSixesConceded(int sixesConceded) {
        this.sixesConceded = sixesConceded;
    }

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
    }

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }
    
    
}
