package uk.org.cricbase.DTOs;

public class ResultSummary {
	private String winner;
	private String type;
	private Integer runsMargin;
	private Integer wicketsMargin;
	private Integer inningsMargin;	

	public ResultSummary() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRunsMargin() {
		return runsMargin;
	}

	public void setRunsMargin(Integer runsMargin) {
		this.runsMargin = runsMargin;
	}

	public Integer getWicketsMargin() {
		return wicketsMargin;
	}

	public void setWicketsMargin(Integer wicketsMargin) {
		this.wicketsMargin = wicketsMargin;
	}

	public Integer getInningsMargin() {
		return inningsMargin;
	}

	public void setInningsMargin(Integer inningsMargin) {
		this.inningsMargin = inningsMargin;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
}
