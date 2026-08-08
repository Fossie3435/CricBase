/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.DTOs;

/**
 *
 * @author Benjamin
 */
public class BattingPerformanceSummary {
    private String batterId;
    private int battingPosition;
    private int runs;
    private int ballsFaced;
    private int fours;
    private int sixes;
    private WicketSummary wicket;
    
    public BattingPerformanceSummary() {}

    public String getBatterId() {
        return batterId;
    }

    public void setBatterId(String batterId) {
        this.batterId = batterId;
    }

    public int getBattingPosition() {
        return battingPosition;
    }

    public void setBattingPosition(int battingPosition) {
        this.battingPosition = battingPosition;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public WicketSummary getWicket() {
        return wicket;
    }

    public void setWicket(WicketSummary wicket) {
        this.wicket = wicket;
    }
    
    
}
