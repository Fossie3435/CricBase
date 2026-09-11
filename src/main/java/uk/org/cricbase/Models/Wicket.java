/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.org.cricbase.Models;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Transient;

/**
 *
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
    
    private List<String> fieldersStrings = new ArrayList<>();
	private List<Boolean> isFielderSub = new ArrayList<>();
	private List<WicketFielder> fielders = new ArrayList<>();

	@JsonIgnore
    private Player bowler;

    @JsonIgnore
    private Player batter;
    
    public Wicket() {
        
    }
    
    @JsonProperty("fielders")
    private void unpackFielders(List<Map<String, Object>> fielders) {
		for(int i = 0; i < fielders.size(); i++) {
            this.fieldersStrings.add((String) (fielders.get(i).get("name")));
			
			if(fielders.get(i).containsKey("substitute")) {	
				this.isFielderSub.add((Boolean) fielders.get(i).get("substitute"));	
			} else {
				this.isFielderSub.add(Boolean.FALSE);
			}
        } 
    }

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

    public void addFielders(Team bowlingTeam) {
		switch(this.dismissalType) {
			case "caught and bowled":
				this.dismissalType = "caught";
				this.fielders.add(new WicketFielder(this.bowler, 1, false, false));
				break;
			case "run out":
				for(int i = 0; i < fieldersStrings.size(); i++) {
					this.fielders.add(new WicketFielder(bowlingTeam.getPlayer(fieldersStrings.get(i)), i+1, bowlingTeam.isPlayerSub(fieldersStrings.get(i)), bowlingTeam.isPlayerWicketkeeper(fieldersStrings.get(i))));
				}
				break;
			case "stumped":
				this.fielders.add(new WicketFielder(bowlingTeam.getPlayer(fieldersStrings.get(0)), 1, bowlingTeam.isPlayerSub(fieldersStrings.get(0)), true));
				break;
			case "caught":
				this.fielders.add(new WicketFielder(bowlingTeam.getPlayer(fieldersStrings.get(0)), 1, bowlingTeam.isPlayerSub(fieldersStrings.get(0)), bowlingTeam.isPlayerWicketkeeper(fieldersStrings.get(0))));
				break;
		}
	}

    public List<WicketFielder> getFielders() {
		return this.fielders;
    } 
}
