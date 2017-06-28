package edu.gvsu.cis350;


public class CounterModel implements IModel {
	
	private int data;

	public CounterModel() {
		data = 0;
	}

	public int getValue() {
		return data;
	}

	public void add(int val) {
		data += val;
	}

	public void sub(int val) {
		data -= val;
	}

	public void reset() {
		data = 0;
	}
	
}
