package uk.org.cricbase.DTOs;

/**
 *
 */
public class FallOfWicketSummary {
    private String delivery;
    private int total;
    private int wicket;
    private String batterId;
    
    public FallOfWicketSummary() {}

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getBatterId() {
        return batterId;
    }

    public void setBatterId(String batterId) {
        this.batterId = batterId;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }
    
    
    
}
