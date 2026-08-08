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
public class BattingPerformance implements Comparable<BattingPerformance> {
    private long id;

    @JsonIgnore
    private Inning inning;
    private Player batter;
    private int battingPosition;
    private List<Delivery> deliveries = new ArrayList<>();
    private int runs;
    private int ballsFaced;
    private int fours;
    private int sixes;
    private Wicket wicket;
    
    public BattingPerformance() {
        
    }
    
    public BattingPerformance(int battingPosition, Player batter, Inning inning) {
        this.batter = batter;
        this.battingPosition = battingPosition;
        this.inning = inning;
        this.runs = 0;
        this.ballsFaced = 0;
        this.fours = 0;
        this.sixes = 0;
    }
    
    @Override
    public int compareTo(BattingPerformance other) {
        return Integer.compare(this.battingPosition, other.getBattingPosition());
    }

    public Player getBatter() {
        return batter;
    }

    public int getBattingPosition() {
        return battingPosition;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public int getRuns() {
        return runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public Wicket getWicket() {
        return wicket;
    }

    public void setWicket(Wicket wicket) {
        this.wicket = wicket;
    }
    
    public boolean isOut() {
        return (wicket != null);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBatter(Player batter) {
        this.batter = batter;
    }

    public void setBattingPosition(int battingPosition) {
        this.battingPosition = battingPosition;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }
    
    public void addDelivery(Delivery newDelivery) {
        newDelivery.setBattingPerformance(this);
        this.deliveries.add(newDelivery);
        
        
        this.runs += newDelivery.getRuns();
        if(newDelivery.getRuns() == 4) {
            this.fours++;
        } else if(newDelivery.getRuns() == 6) {
            this.sixes++;
        }
        
        if(newDelivery.getWides() == 0) {
            this.ballsFaced++;
        }
        
        if(newDelivery.getWicket() != null && newDelivery.getWicket().getBatter().equals(this.batter)) {
            this.wicket = newDelivery.getWicket();
            this.wicket.setBattingPerformance(this);
        }
    }
    
    public String getWicketString() {
        if(this.wicket == null) {
            return "not out";
        }
        return this.wicket.getWicketString();
    }
    
    public float getStrikeRate() {
        return (100f * runs / ballsFaced);
    }
    
    public String getStringStrikeRate() {
        if(ballsFaced == 0) {
            return " - ";
        } else {
            
            return Float.toString(Math.round(this.getStrikeRate() * 100f) / 100f);
            
        }
    }
    
    public void printBattingPerformance() {
        System.out.printf("%s | %s | Runs: %d | Balls: %d | Fours: %d | Sixes: %d | SR: %s", this.batter, this.getWicketString(), this.runs, this.ballsFaced, this.fours, this.sixes, this.getStringStrikeRate());
        System.out.println("");
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }

    public long getId() {
        return id;
    }
    
    
    
}
