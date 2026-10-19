package uk.org.cricbase.domain.points;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class PointCriteria {
	private ArrayList<Integer> args = new ArrayList<>();
	private int value;


	public PointCriteria() {
	}

	public PointCriteria(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@JsonIgnore
	public int getArgsSize() {
		return this.args.size();
	}

	public int getArg(int index) {
		return args.get(index);
	}
	public void addArg(int newArg) {
		this.args.add(newArg);
	}
}

