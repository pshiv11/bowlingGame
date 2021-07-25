package Bowling;

public class Game {
	
	Frame[] frames;
	int frameCounter;
	Score finalScore;
	
	public Game() {
		this.frames = new Frame[10];
		this.frameCounter = 0 ;
		this.finalScore = new Score();
	}
	

	public void init() {
		for(int i = 0; i < 10; i++) {
			this.frames[i] = new Frame();
		}
		
	}
	
	public void incrementFrameCounter() {
		if(this.frameCounter < 9) {
			this.frameCounter++;
		}
		else {
			this.frames[this.frameCounter].incrementRollCounter();
		}
	}
		
	
	public void roll(String down) {
		int rollCount = this.frames[this.frameCounter].getRollCounter();
		Frame frame = this.frames[this.frameCounter];
		
		if(isStrike(down)) {
			
			frame.add(rollCount, 10);
			frame.setStrike();
			this.incrementFrameCounter();
		}
		
		else if(isSpare(down)) {
			int rollOne = this.frames[this.frameCounter].getRollValue(0);
			frame.add(rollCount, (10 - rollOne));
			frame.setSpare();
			this.incrementFrameCounter();
		}
		
		else if(down.equals("Miss")){
			frame.add(rollCount, 0);
			
			if(this.frames[this.frameCounter].getRollCounter() == 0) {
				frame.incrementRollCounter();
			}
			else {
				this.incrementFrameCounter();
			}
		}
		else {
			frame.add(rollCount, Integer.parseInt(down));
			
			if(frame.getRollCounter() == 0) {
				frame.incrementRollCounter();
			}
			else {
				this.incrementFrameCounter();
			}
			
		}
			
	}
	
	public boolean isStrike(String str) {
		return str.equals("Strike");
	}
	public boolean isSpare(String str) {
		return str.equals("Spare");
	}
	
	
	public int getPreviousFrameScore(int pinsDown) {
		if(this.frameCounter > 0) {
		 Frame currentFrame = this.frames[this.frameCounter];
		 Frame previousFrame = this.frames[this.frameCounter - 1];
		 if(previousFrame.isStrike && currentFrame.getRollCounter()==0) {
			 int prevScore = previousFrame.getScoreObj().getScore();
			previousFrame.getScoreObj().setScore(prevScore + pinsDown);
		 }
			
			
			
			
		}
		return 0;
	}
	
	public void computeScore() {
		for(int i = 0; i < this.frameCounter; i++) {
			
		}
	}
	
	
	public void print() {
		for(int i = 0; i < 10; i++) {
			
			if(this.frames[i].getRollValue(0) >= 0) {
				System.out.print(" " + "Frame " + i + " Roll 1 = ");
				System.out.print(this.frames[i].getRollArray()[0].getValue());
				System.out.println();
				
			}
			if(this.frames[i].getRollValue(1) >= 0) {
				System.out.print(" " + "Frame " + i + " Roll 2 = ");
				System.out.print(this.frames[i].getRollArray()[1].getValue());
				System.out.println();
			}
			
			if(i == 9) {
				System.out.print(" " + "Frame " + i + " Roll 3 = ");
				System.out.print(this.frames[i].getRollArray()[2].getValue());
				System.out.println();
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	

}
