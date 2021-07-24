package Bowling;


public class Frame {
	//List <Roll> rolls;
	
	Roll[] rolls;
	int rollCounter;
	public final int MAX_ROLL = 3;
	
	public Frame() {
		this.rolls = new Roll[3];
		this.rollCounter = 0;
		for(int i = 0; i < 3; i++) {
			this.rolls[i] = new Roll();
		}
	}
	
	public void add(int index, int pins) {
		this.rolls[index].add(pins);
		
	}
	
	public void incrementRollCounter() {
		this.rollCounter++;
	}
	
	public int getRollCounter() {
		return this.rollCounter;
	}
	
	public int getRollValue(int index) {
		return this.rolls[index].getValue();
		
	}

	public Roll[] getRollArray() {
		return this.rolls;
	}
	
	
	
}
