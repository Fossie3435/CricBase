package uk.org.cricbase.Models;

/**
 */
public class WicketFielder {
	private long id;
	private Player fielder;
	private int ordinal;
	private boolean isSubstitute;
	private boolean isWicketkeeper;

	public WicketFielder() {}

	public WicketFielder(Player fielder, int ordinal, boolean isSubstitute, boolean isWicketkeeper) {
		this.fielder = fielder;
		this.ordinal = ordinal;
		this.isSubstitute = isSubstitute;
		this.isWicketkeeper = isWicketkeeper;
	}

	public boolean isSubstitute() {
		return isSubstitute;
	}

	public void setSubstitute(boolean isSubstitute) {
		this.isSubstitute = isSubstitute;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getFielder() {
		return fielder;
	}

	public void setFielder(Player fielder) {
		this.fielder = fielder;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public boolean isWicketkeeper() {
		return isWicketkeeper;
	}

	public void setWicketkeeper(boolean isWicketkeeper) {
		this.isWicketkeeper = isWicketkeeper;
	}
}

