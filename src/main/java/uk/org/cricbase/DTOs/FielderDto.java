package uk.org.cricbase.DTOs;

/**
 */
public class FielderDto {
	private String fielder;
	private boolean isWicketkeeper;
	private boolean isSubstitute;
	
	public FielderDto() {}

	public boolean isWicketkeeper() {
		return isWicketkeeper;
	}

	public void setWicketkeeper(boolean isWicketkeeper) {
		this.isWicketkeeper = isWicketkeeper;
	}

	public boolean isSubstitute() {
		return isSubstitute;
	}

	public void setSubstitute(boolean isSubstitute) {
		this.isSubstitute = isSubstitute;
	}

	public String getFielder() {
		return fielder;
	}

	public void setFielder(String fielder) {
		this.fielder = fielder;
	}


}

