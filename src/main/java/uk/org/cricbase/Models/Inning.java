package uk.org.cricbase.Models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Benjamin Foster
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inning {
    private long id;
    @JsonIgnore
    private Match match;
    
    @JsonProperty("team_name")
    private String teamName;
    
    @JsonProperty("batting_team")
    private Team battingTeam;
    @JsonProperty("bowling_team")
    private Team bowlingTeam;
    
    private List<Over> overs;
    
    /**
    @OneToMany (mappedBy = "inning", cascade = CascadeType.ALL)
    private List<Powerplay> powerplays;
    **/
    
    private List<FallOfWicket> fallOfWickets;
    
    private List<BattingPerformance> battingPerformances;
    
    private List<BowlingPerformance> bowlingPerformances;
    
    private int runs;
    
    private int wicketsTaken;
    private int wides;
    private int noballs;
    private int byes;
    private int legbyes;
    private int penaltyRuns;
    
    @JsonCreator
    public Inning(
            @JsonProperty("team") String teamName,
            @JsonProperty("overs") List<Over> overs
            //,
            //@JsonProperty("powerplays") List<Powerplay> powerplays
    ) {
        this.teamName = teamName;
        this.overs = overs;
        //this.powerplays = powerplays;
        
    }
    
    public Inning() {
        
    }
    
    public void generatePerformances() {
        HashMap<String, BattingPerformance> battingPerformances = new HashMap<>();
        HashMap<String, BowlingPerformance> bowlingPerformances = new HashMap<>();
        ArrayList<FallOfWicket> fallOfWickets = new ArrayList<>();
        int currentBattingPosition = 0;
        int currentBowlingPosition = 0;
        int wicketsTaken = 0;
        int runs = 0;
        int wides = 0;
        int noballs = 0;
        int byes = 0;
        int legbyes = 0;
        int penaltyRuns = 0;
        
        Over currentOver;
        Delivery currentDelivery;
        int legalDeliveriesInOver;
        int totalDeliveryCount = 0;
        for(int overIndex = 0; overIndex < overs.size(); overIndex++) {
            legalDeliveriesInOver = 1;
            currentOver = overs.get(overIndex);
            currentOver.setInning(this);
            for(int deliveryIndex = 0; deliveryIndex < currentOver.getDeliveriesCount(); deliveryIndex++) {
                totalDeliveryCount++;
                currentDelivery = currentOver.getDelivery(deliveryIndex);
                currentDelivery.setOver(currentOver);
                currentDelivery.setTotalDeliveryCount(totalDeliveryCount);
                currentDelivery.setDeliveryCount(legalDeliveriesInOver);
                
                currentDelivery.setBatter(battingTeam.getPlayer(currentDelivery.getBatterString()));
                currentDelivery.setBowler(bowlingTeam.getPlayer(currentDelivery.getBowlerString()));
                currentDelivery.setNonStriker(battingTeam.getPlayer(currentDelivery.getNonStrikerString()));
                
                String currentDeliveryStamp = overIndex + "." + (legalDeliveriesInOver + 1);

                if(!battingPerformances.containsKey(currentDelivery.getBatterString())) { // if batter has no performance, adds it 
                    currentBattingPosition++;
                    battingPerformances.put(currentDelivery.getBatterString(), new BattingPerformance(currentBattingPosition, currentDelivery.getBatter(), this));
                }
                
                if(!battingPerformances.containsKey(currentDelivery.getNonStrikerString ())) {
                    currentBattingPosition++;
                    battingPerformances.put(currentDelivery.getNonStrikerString(), new BattingPerformance(currentBattingPosition, currentDelivery.getNonStriker(), this));
                }
                
                if(!bowlingPerformances.containsKey(currentDelivery.getBowlerString())) {
                    currentBowlingPosition++;
                    bowlingPerformances.put(currentDelivery.getBowlerString(), new BowlingPerformance(currentBowlingPosition, currentDelivery.getBowler(), this));
                }
                runs += currentDelivery.getRuns();
                wides += currentDelivery.getWides();
                noballs += currentDelivery.getNoballs();
                byes += currentDelivery.getByes();
                legbyes += currentDelivery.getLegbyes();
                penaltyRuns += currentDelivery.getPenaltyRuns();
                
               
                
                if(currentDelivery.getWicket() != null) {
                    currentDelivery.getWicket().setBowler(currentDelivery.getBowler());
                    currentDelivery.getWicket().setBatter(this.battingTeam.getPlayer(currentDelivery.getWicket().getBatterString()));
                    Player batterOut = currentDelivery.getWicket().getBatter();
                    int totalRunsScored = runs + wides + noballs + byes + legbyes + penaltyRuns;
                    
                    wicketsTaken++;
                    
                    
                    if(batterOut.getName().equals(currentDelivery.getNonStrikerString())) {
                        currentDelivery.getWicket().setBattingPerformance(battingPerformances.get(currentDelivery.getNonStrikerString()));
                        battingPerformances.get(batterOut).setWicket(currentDelivery.getWicket());
                    }   
                    
                    fallOfWickets.add(new FallOfWicket(wicketsTaken, totalRunsScored, batterOut, currentDeliveryStamp));
                    fallOfWickets.get(fallOfWickets.size() - 1).setInning(this);
                }
                                
                if(currentDelivery.isLegalDelivery()) {
                    legalDeliveriesInOver++;
                }
                bowlingPerformances.get(currentDelivery.getBowlerString()).addDelivery(currentDelivery);
                battingPerformances.get(currentDelivery.getBatterString()).addDelivery(currentDelivery);
                currentOver.addDelivery(currentDelivery);
                
                
                
            }
        }
        this.wicketsTaken = wicketsTaken;
        this.runs = runs;
        this.wides = wides;
        this.noballs = noballs;
        this.byes = byes;
        this.legbyes = legbyes;
        this.penaltyRuns = penaltyRuns;
        
        
        this.bowlingPerformances = new ArrayList<>(bowlingPerformances.values());
        this.battingPerformances = new ArrayList<>(battingPerformances.values());
        this.fallOfWickets = fallOfWickets;
        
        Collections.sort(this.battingPerformances);
        Collections.sort(this.bowlingPerformances);
    }
    
    public void printInningScorecard() {
        System.out.println("Total: " + (runs + wides + noballs + byes + legbyes +penaltyRuns) + "/" + wicketsTaken);
        System.out.print("Runs: " + runs + " | Extras: " + (wides + noballs + byes + legbyes));
        if(penaltyRuns != 0) {
            System.out.println("Runs from penalties: " + penaltyRuns);
        }
        System.out.println("");
        
        for(int i = 0; i < battingPerformances.size(); i++) {
            battingPerformances.get(i).printBattingPerformance();
        }
        System.out.println("");
        
        for(int i = 0; i < fallOfWickets.size(); i++) {
            fallOfWickets.get(i).print();
            System.out.println("");
        } 
        System.out.println("");
        
        for(int i = 0; i < bowlingPerformances.size(); i++) {
            bowlingPerformances.get(i).printBowlingPerformance();
        }
    }
    
    public void setTeams(Team teamOne, Team teamTwo) {
        if(teamOne.getName().equals(teamName)) {
            this.battingTeam = teamOne;
            this.bowlingTeam = teamTwo;
        } else {
            this.battingTeam = teamTwo;
            this.bowlingTeam = teamOne;
        }
    }
    

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    

    public List<Over> getOvers() {
        return overs;
    }

    public void setOvers(List<Over> overs) {
        this.overs = overs;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<FallOfWicket> getFallOfWickets() {
        return fallOfWickets;
    }

    public void setFallOfWickets(List<FallOfWicket> fallOfWickets) {
        this.fallOfWickets = fallOfWickets;
    }

    public List<BattingPerformance> getBattingPerformances() {
        return battingPerformances;
    }

    public void setBattingPerformances(List<BattingPerformance> battingPerformances) {
        this.battingPerformances = battingPerformances;
    }

    public List<BowlingPerformance> getBowlingPerformances() {
        return bowlingPerformances;
    }

    public void setBowlingPerformances(List<BowlingPerformance> bowlingPerformances) {
        this.bowlingPerformances = bowlingPerformances;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
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

    public int getPenaltyRuns() {
        return penaltyRuns;
    }

    public void setPenaltyRuns(int penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }

    public long getId() {
        return id;
    }
    
    public int getTotalRuns() {
        return this.runs + this.byes + this.legbyes + this.wides + this.noballs + this.penaltyRuns;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }
    
    
    /**
    public List<Powerplay> getPowerplays() {
        return powerplays;
    }

    public void setPowerplays(List<Powerplay> powerplays) {
        this.powerplays = powerplays;
    }
    * */
    
    
}


