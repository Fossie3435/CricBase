/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin Foster <fosterbp@lancaster.ac.uk>
 */
public class BowlingPerformance implements Comparable<BowlingPerformance> {
    private long id;
    
    @JsonIgnore
    private Inning inning;
    
    private Player bowler;
    private int bowlingPosition;
    
    private List<Delivery> deliveries = new ArrayList<>();
    private List<Wicket> wickets = new ArrayList<>();
    
    private int ballsBowled;
    private int maidens;
    private int runsConceded;
    private int wicketCount;
    private int dots;
    private int foursConceded;
    private int sixesConceded;
    private int wides;
    private int noballs;
    private int byes;
    private int legbyes;
    
    public BowlingPerformance() {
        
    }
    
    public BowlingPerformance(int bowlingPosition, Player bowler, Inning inning) {
        this.bowler = bowler;
        this.bowlingPosition = bowlingPosition;
        this.inning = inning;
        this.ballsBowled = 0;
        this.maidens = 0;
        this.runsConceded = 0;
        this.wicketCount = 0;
        this.dots = 0;
        this.foursConceded = 0;
        this.sixesConceded = 0;
        this.wides = 0;
        this.noballs = 0;
        this.byes = 0;
        this.legbyes = 0;
    }
    
    @Override
    public int compareTo(BowlingPerformance other) {
        return Integer.compare(this.bowlingPosition, other.getBowlingPosition());
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Wicket> getWickets() {
        return wickets;
    }

    public void setWickets(List<Wicket> wickets) {
        this.wickets = wickets;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public int getMaidens() {
        return maidens;
    }

    public void setMaidens(int maidens) {
        this.maidens = maidens;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getWicketCount() {
        return wicketCount;
    }

    public void setWicketCount(int wicketCount) {
        this.wicketCount = wicketCount;
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

    public int getByes() {
        return byes;
    }

    public void setByes(int byes) {
        this.byes = byes;
    }

    public int getLegbyes() {
        return legbyes;
    }

    public void setLegbyes(int legbyes) {
        this.legbyes = legbyes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    public void addDelivery(Delivery newDelivery) {
        newDelivery.setBowlingPerformance(this);
        if(newDelivery.isLegalDelivery()) {
            this.ballsBowled++;
        }
        if(newDelivery.getTotalRuns() == 0) {
            // dot ball
            dots++;
        } else {
            runsConceded += newDelivery.getRuns();
            
            foursConceded += newDelivery.isFour() ? 1 : 0;
            sixesConceded += newDelivery.isSix() ? 1 : 0;
            
            wides += newDelivery.getWides();
            runsConceded += newDelivery.getWides();
            
            noballs += newDelivery.getNoballs();
            runsConceded += newDelivery.getNoballs();
            
            byes += newDelivery.getByes();
            legbyes += newDelivery.getLegbyes();
        }
        Wicket wicket = newDelivery.getWicket();
        if(wicket != null) {
            if(wicket.getDismissalType().equals("bowled") || wicket.getDismissalType().equals("lbw") || wicket.getDismissalType().equals("caught") || wicket.getDismissalType().equals("stumped") || wicket.getDismissalType().equals("hit wicket")) { // could also be retired hurt , run out
                wicketCount++;
                wicket.setBowlingPerformance(this);
                wickets.add(wicket);
            }
        }
    }
    public void addMaiden() {
        this.maidens++;
    }
    
    public float getEcon() {
        return  runsConceded / ((float) ballsBowled / 6);
    }
    
    public String getOvers() {
        if(this.ballsBowled % 6 == 0) {
            return  Integer.toString(ballsBowled / 6);
        } else {
            return ((int) ballsBowled / 6 + "." + ballsBowled % 6);
        }
    }

    public int getBowlingPosition() {
        return bowlingPosition;
    }
    
    
    public void printBowlingPerformance() {
        System.out.printf("%s | Overs: %s | Maidens: %d | Runs: %d | Wickets: %d | Econ: %.2f | Dots: %d | Wides: %d | No-balls: %d", this.bowler, getOvers(), getMaidens(), getRunsConceded(), getWicketCount(), getEcon(), getDots() ,getWides(), getNoballs() );
        System.out.println("");
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }
    
    
 
}
