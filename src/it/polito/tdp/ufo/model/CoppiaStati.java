package it.polito.tdp.ufo.model;

public class CoppiaStati {

	private String state1;
	private String state2;

	public CoppiaStati(String state1, String state2) {
		super();
		this.state1 = state1;
		this.state2 = state2;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	@Override
	public String toString() {
		return String.format("CoppiaStati [state1=%s, state2=%s]", state1, state2);
	}

}
