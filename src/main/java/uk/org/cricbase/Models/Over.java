/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import jakarta.persistence.*;

/**
 *
 * @author Benjamin Foster
 */
public class Over {
    private long id;
    
    @JsonIgnore
    private Inning inning;
    private List<Delivery> deliveries;
    private int over;

    private int runs;
    private int wides;
    private int byes;
    private int legbyes;
    private int noballs;
    private int penaltyRuns;
    
    public Over() {
        
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
    
    public Delivery getDelivery(int index) {
        return this.deliveries.get(index);
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }
    public int getDeliveriesCount() {
        return this.deliveries.size();
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
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

    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }
    public void addDelivery(Delivery currentDelivery) {
        runs += currentDelivery.getRuns();
        wides += currentDelivery.getWides();
        noballs += currentDelivery.getNoballs();
        byes += currentDelivery.getByes();
        legbyes += currentDelivery.getLegbyes();
        penaltyRuns += currentDelivery.getPenaltyRuns();
                
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
