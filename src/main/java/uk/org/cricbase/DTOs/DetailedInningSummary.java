/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

import java.util.List;


import uk.org.cricbase.Models.BattingPerformance;
import uk.org.cricbase.Models.BowlingPerformance;

/**
 *
 * @author Benjamin
 */
public class DetailedInningSummary {
    private long id;
    private int total;
    private int wickets;
    private int runs;
    private int byes;
    private int legbyes;
    private int wides;
    private int noballs;
    private int penaltyRuns;
    private List<BattingPerformanceSummary> battingScorecard;
    private List<BowlingPerformanceSummary> bowlingScorecard;
    private List<FallOfWicketSummary> fallOfWickets;
    
    public DetailedInningSummary() {
    
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    
    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
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

    public int getPenaltyRuns() {
        return penaltyRuns;
    }

    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }

    public List<BattingPerformanceSummary> getBattingScorecard() {
        return battingScorecard;
    }

    public void setBattingScorecard(List<BattingPerformanceSummary> battingScorecard) {
        this.battingScorecard = battingScorecard;
    }

    public List<BowlingPerformanceSummary> getBowlingScorecard() {
        return bowlingScorecard;
    }

    public void setBowlingScorecard(List<BowlingPerformanceSummary> bowlingScorecard) {
        this.bowlingScorecard = bowlingScorecard;
    }

    public List<FallOfWicketSummary> getFallOfWickets() {
        return fallOfWickets;
    }

    public void setFallOfWickets(List<FallOfWicketSummary> fallOfWickets) {
        this.fallOfWickets = fallOfWickets;
    }
    
    
    
    
    
    
}
