package uk.org.cricbase.DTOs;

import java.util.List;

/**
 *
 */
public class WicketSummary {
    private String batterOut;
    private String bowler;
    private String dismissalType;
	
	private List<FielderDto> fielders;

    public WicketSummary() {
        
    }

    public String getBatterOut() {
        return batterOut;
    }

    public void setBatterOut(String batterOut) {
        this.batterOut = batterOut;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getDismissalType() {
        return dismissalType;
    }

    public void setDismissalType(String dismissalType) {
        this.dismissalType = dismissalType;
    }

	public List<FielderDto> getFielders() {
		return fielders;
	}

	public void setFielders(List<FielderDto> fielders) {
		this.fielders = fielders;
	}
}
