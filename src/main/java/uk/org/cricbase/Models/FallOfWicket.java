/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
/**
 *
 * @author Benjamin
 */
public class FallOfWicket {
    private long id;
    
    @JsonIgnore
    private Inning inning;
    
    private int wicket;
    private int currentTotal;
    private Player batterOut;
    private String delivery;
    
    public FallOfWicket() {
        
    }
    
    public FallOfWicket(int wicket, int currentTotal, Player batterOut, String delivery) {
        this.wicket = wicket;
        this.currentTotal = currentTotal;
        this.batterOut = batterOut;
        this.delivery = delivery;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public void setCurrentTotal(int currentTotal) {
        this.currentTotal = currentTotal;
    }

    public  Player getBatterOut() {
        return batterOut;
    }

    public void setBatterOut(Player batterOut) {
        this.batterOut = batterOut;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public Inning getInning() {
        return inning;
    }

    public void setInning(Inning inning) {
        this.inning = inning;
    }
    
    public void print() {
        System.out.printf("%d-%d (%s, %s)", wicket, currentTotal, batterOut, delivery);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    
    
}
