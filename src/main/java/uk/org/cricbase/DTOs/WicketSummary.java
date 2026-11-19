package uk.org.cricbase.DTOs;

import java.util.List;

/**
 *
 */
public class WicketSummary {
    private String batterId;
    private String bowlerId;
    private String dismissalType;
	
	private List<FielderDto> fielders;

    public WicketSummary() {
        
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

	public String getBatterId() {
		return batterId;
	}

	public void setBatterId(String batterId) {
		this.batterId = batterId;
	}

	public String getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(String bowlerId) {
		this.bowlerId = bowlerId;
	}
}
