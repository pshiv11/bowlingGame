package Bowling;


public class Frame {
	//List <Roll> rolls;
	
	Roll[] rolls;
	int rollCounter;
	public final int MAX_ROLL = 3;
	Score frameScore;
	boolean frameStatus;
	boolean isStrike;
	boolean isSpare;
	
	public Frame() {
		this.rolls = new Roll[3];
		this.rollCounter = 0;
		this.frameScore = new Score();
		this.frameStatus = false;
		for(int i = 0; i < 3; i++) {
			this.rolls[i] = new Roll();
		}
	}
	
	public void setStrike() {
		this.isStrike = true;
	}
	
	public void setSpare() {
		this.isSpare = true;
	}
	
	public int getScore() {
		return this.frameScore.getScore();
	}
	public void setScore(int i) {
		this.frameScore.setScore(i);
	}
	
	public void setFrameStatus(String str) {
		if(str.equals("closed")) {
			this.frameStatus= true;
		}
		else if (str.equals("open")){
			this.frameStatus = false;
		}
	}
	
	public boolean isFrameClosed() {
		return this.frameStatus;
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
	
	public int getRollScore(int index) {
		return this.rolls[index].getScore();
		
	}

	public Roll[] getRollArray() {
		return this.rolls;
	}
	
	
	
}
