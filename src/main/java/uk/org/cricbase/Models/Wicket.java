/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Transient;

/**
 *
 * @author Benjamin Foster <fosterbp@lancaster.ac.uk>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wicket {
    private long id;
    
    @JsonIgnore
    private Delivery delivery;
    @JsonIgnore
    private BattingPerformance battingPerformance;
    @JsonIgnore
    private BowlingPerformance bowlingPerformance;
    @JsonProperty("player_out")
    private String batterString;
    @JsonProperty("kind")
    private String dismissalType;
    
    //private ArrayList<String> fielders = new ArrayList<>();
    @JsonIgnore
    private Player bowler;

    @JsonIgnore
    private Player batter;
    
    public Wicket() {
        
    }
    
    /**
    @JsonProperty("fielders")
    private void unpackFielders(List<Map<String, String>> fielders) {
        this.fielders = new ArrayList<>();
        for(int i = 0; i < fielders.size(); i++) {
            this.fielders.add(fielders.get(i).get("name"));
        } 
    } **/

    public Player getBatter() {
        return batter;
    }

    public void setBatter(Player batter) {
        this.batter = batter;
    }

    public String getDismissalType() {
        return dismissalType;
    }

    public void setDismissalType(String dismissalType) {
        this.dismissalType = dismissalType;
    }
    @Transient
    public String getWicketString() {
        switch(dismissalType) {
            case "bowled":
                return("b ");
            
            case "lbw":
                return("lbw b ");
                
            case "caught":
                /**
                if(this.getPrimaryFielder().equals(this.bowler)) {
                    return("c&b " + this.bowler);
                }
                return("c " + this.getPrimaryFielder() + " b " + this.bowler);
                * */
                return "caught";
            
            case "run out":
                //return ("run out (" + getFieldersString() + ")");
                return "run out";
            
        }
        return "not out";
    }
    /**
    public void setFielders(ArrayList<String> fielders) {
        this.fielders = fielders;
    }
    
    public List<String> getFielders() {
        return this.fielders;
    }
    
    public String getPrimaryFielder() {
        return this.fielders.get(0);
    }
    
    public String getFieldersString() {
        String fielders = getPrimaryFielder();
        for(int i = 1; i < this.fielders.size(); i++) {
            fielders +="/" + this.fielders.get(i);
        }
        return fielders;  
    } **/

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatterString() {
        return batterString;
    }

    public void setBatterString(String batterString) {
        this.batterString = batterString;
    }
    
    
    
    
}
