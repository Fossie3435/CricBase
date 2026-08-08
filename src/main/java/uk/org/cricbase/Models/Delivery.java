/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Benjamin Foster
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Delivery {
    private long id;
    
    @JsonIgnore
    private Over over;
    private int totalDeliveryCount;
    private int deliveryCount;
    
    @JsonIgnore
    private BattingPerformance battingPerformance;   
    @JsonIgnore
    private BowlingPerformance bowlingPerformance;
    
    @JsonProperty("batter")
    private String batterString;
    
    @JsonProperty("bowler")
    private String bowlerString;
    
    @JsonProperty("non_striker")
    private String nonStrikerString;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Player batter;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Player bowler;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Player nonStriker;
    
    private int runs;
    private int wides;
    private int byes;
    private int legbyes;
    private int noballs;
    private int penaltyRuns;
    private Wicket wicket;
    
    @JsonProperty("runs")
    private void unpackRuns(Map<String, Object> runs) {
        this.runs = (Integer) runs.get("batter");
    }
    
    @JsonProperty("extras")
    private void unpackExtras(Map<String, Object> extras) {
        this.wides = (Integer) extras.getOrDefault("wides", 0);
        this.byes = (Integer) extras.getOrDefault("byes", 0);
        this.legbyes = (Integer) extras.getOrDefault("legbyes", 0);
        this.noballs = (Integer) extras.getOrDefault("noballs", 0);
        this.penaltyRuns = (Integer) extras.getOrDefault("penalties", 0);
    }

    @JsonProperty("wickets")
    private void unpackWicket(List<Wicket> wickets) {
        if(wickets == null || wickets.isEmpty()) {
            this.wicket = null;
        } else {
            this.wicket = wickets.get(0);
        }
    }
    
    
    public Delivery() {
        
    }
    @JsonIgnore
    public String getBatterString() {
        return batterString;
    }
    @JsonIgnore
    public void setBatterString(String batter) {
        this.batterString = batter;
    }
    @JsonIgnore
    public String getBowlerString() {
        return bowlerString;
    }

    @JsonIgnore
    public void setBowlerString(String bowler) {
        this.bowlerString = bowler;
    }
    @JsonIgnore
    public String getNonStrikerString() {
        return nonStrikerString;
    }
    @JsonIgnore
    public void setNonStrikerString(String nonStriker) {
        this.nonStrikerString = nonStriker;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
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

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }

    public int getPenaltyRuns() {
        return penaltyRuns;
    }

    public void setPenalties(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }

    public Wicket getWicket() {
        return wicket;
    }

    public void setWicket(Wicket wicket) {
        this.wicket = wicket;
    }
    
    public int getTotalRuns() {
        return (runs + wides + byes + legbyes + noballs);
    }
    
    public boolean isLegalDelivery() {
        if((noballs + wides) == 0) {
            return true;
        } 
        return false;
    }
    
    public boolean isFour() {
        return (runs == 4);
    }
    
    public boolean isSix() {
        return (runs == 6);
    } 

    public Over getOver() {
        return over;
    }

    public void setOver(Over over) {
        this.over = over;
    }

    public BattingPerformance getBattingPerformance() {
        return battingPerformance;
    }

    public void setBattingPerformance(BattingPerformance battingPerformance) {
        this.battingPerformance = battingPerformance;
    }

    public BowlingPerformance getBowlingPerformance() {
        return bowlingPerformance;
    }

    public void setBowlingPerformance(BowlingPerformance bowlingPerformance) {
        this.bowlingPerformance = bowlingPerformance;
    }

    public int getTotalDeliveryCount() {
        return totalDeliveryCount;
    }

    public void setTotalDeliveryCount(int totalDeliveryCount) {
        this.totalDeliveryCount = totalDeliveryCount;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(int deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public Player getBatter() {
        return batter;
    }

    public void setBatter(Player batter) {
        this.batter = batter;
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }
    
    
}
    
    