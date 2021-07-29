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
			if(i == 9) {
				this.frames[i] = new Frame(3);
			}
			else {
				this.frames[i] = new Frame();
			}
			
		}
	}
	
	public void incrementFrameCounter() {
		if(this.frameCounter < 9) {
			this.frames[this.frameCounter].incrementRollCounter();
			this.frameCounter++;
		}
		else {
			this.frames[this.frameCounter].incrementRollCounter();
		}
	}
		

	public void roll(String down) {
		
		Frame currentFrame = this.frames[this.frameCounter];
		int rollCount = currentFrame.getRollCounter();
		
		if(isStrike(down)) {
			
			currentFrame.add(rollCount, 10);
			currentFrame.setStrike();
			computeScore();
			this.incrementFrameCounter();
		}
		
		else if(isSpare(down)) {
			int rollOne = currentFrame.getRollScore(0);
			currentFrame.add(rollCount, (10 - rollOne));
			currentFrame.setSpare();
			computeScore();
			this.incrementFrameCounter();
		}
		
		else if(down.equals("Miss")){
			currentFrame.add(rollCount, 0);
			computeScore();
			if(currentFrame.getRollCounter() == 0) {
				 
				currentFrame.incrementRollCounter();
			}
			else {
				
				this.incrementFrameCounter();
			}
			
		}
		else {
			currentFrame.add(rollCount, Integer.parseInt(down));
			computeScore();
			if(currentFrame.getRollCounter() == 0) {
				
				currentFrame.incrementRollCounter();
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
	
	
	public void checkPreviousFramesScore() {
		Frame previousFrame = this.frames[this.frameCounter - 1];
		Frame currentFrame = this.frames[this.frameCounter];
		
		//previous of previous frame exists
		if(this.frameCounter > 1) {
			Frame previousOfPrevious = this.frames[this.frameCounter - 2];
			if(!previousOfPrevious.isFrameClosed()) {
				//if previous of previous frame is not closed than previous frame has to be strike
				if(previousFrame.isStrike) { //no need to check this condition
					previousOfPrevious.setScore(previousOfPrevious.getScore() +  currentFrame.getRollScore(0));
					this.finalScore.setScore(this.finalScore.getScore() + previousOfPrevious.getScore());
					previousOfPrevious.setFrameStatus("closed");
					System.out.println("Final Score = " + this.finalScore.getScore());
				}
			}
		}
		//if previous frame is not closed
		if(!previousFrame.isFrameClosed()) {
			if(previousFrame.isStrike && currentFrame.getRollCounter() == 0) {
				previousFrame.setScore(previousFrame.getScore() + currentFrame.getRollScore(0));
			}
			else if(previousFrame.isStrike && currentFrame.getRollCounter() == 1) {
				previousFrame.setScore(previousFrame.getScore() + currentFrame.getRollScore(1));
				previousFrame.setFrameStatus("closed");
				this.finalScore.setScore(this.finalScore.getScore() + previousFrame.getScore());
				System.out.println("Final Score = " + this.finalScore.getScore());
			}
			else if(previousFrame.isSpare && currentFrame.getRollCounter() == 0) {
				previousFrame.setScore(previousFrame.getScore() + currentFrame.getRollScore(0));
				previousFrame.setFrameStatus("closed");
				this.finalScore.setScore(this.finalScore.getScore() + previousFrame.getScore());
				System.out.println("Final Score = " + this.finalScore.getScore());
			}
			
		}
	
	}
	
	public void lastFrameScore() {
		Frame currentFrame = this.frames[this.frameCounter];
		//this.checkPreviousFramesScore();
	
		//******************Calculate last frame score*************//
		if(currentFrame.isStrike || currentFrame.isSpare) {
			if(currentFrame.getRollCounter() == 0) {
				currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(0));
					
			}
			else if(currentFrame.getRollCounter() == 1) {
				currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
				
			}
			else if(currentFrame.getRollCounter() == 2) {
				currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(2));
				this.finalScore.setScore(this.finalScore.getScore() + currentFrame.getScore());
				System.out.println("Final Score = " + this.finalScore.getScore());
				currentFrame.setFrameStatus("closed");
			}
			
		}
		else {
			if(currentFrame.getRollCounter() == 0) {
				currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(0));
				
			}
			else if(currentFrame.getRollCounter() == 1 && !currentFrame.isSpare) {
				currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
				this.finalScore.setScore(this.finalScore.getScore() + currentFrame.getScore());
				System.out.println("Final Score = " + this.finalScore.getScore());
				currentFrame.setFrameStatus("closed");
			}	
			
		}
	}

	public void computeScore() {
		int numOfFrames = this.frameCounter;

		//only one frame exists
		if(numOfFrames == 0) {
			Frame currentFrame = this.frames[this.frameCounter];
			
			
			if(currentFrame.isStrike) {
				currentFrame.setScore(currentFrame.getRollScore(0));
			}
			else {
				if(currentFrame.getRollCounter() == 0) {
					currentFrame.setScore(currentFrame.getRollScore(0));
				}
				else if(currentFrame.getRollCounter() == 1 && currentFrame.isSpare) {
					currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
				}
				else if(currentFrame.getRollCounter() == 1 && !currentFrame.isSpare){
					currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
					currentFrame.setFrameStatus("closed");
					this.finalScore.setScore(currentFrame.getScore());
					System.out.println("Final Score = " + this.finalScore.getScore());
				}
			}	
			
		}
		//more than one frame exists
		else {
			//******************Calculate special previous frame score (another method)*************//
			
			//Frame previousFrame = this.frames[this.frameCounter - 1];
			Frame currentFrame = this.frames[this.frameCounter];
			
			
			
			//******************Calculate current frame score*************//
			if(this.frameCounter == 9) {
				this.checkPreviousFramesScore();
				this.lastFrameScore();
			}
			else {
				this.checkPreviousFramesScore();
				if(currentFrame.isStrike) {
					currentFrame.setScore(currentFrame.getRollScore(0));
				}
				else {
					if(currentFrame.getRollCounter() == 0) {
						currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(0));
					}
					else if(currentFrame.getRollCounter() == 1 && currentFrame.isSpare) {
						currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
					}
					else if(currentFrame.getRollCounter() == 1 && !currentFrame.isSpare) {	
						currentFrame.setScore(currentFrame.getScore() + currentFrame.getRollScore(1));
						this.finalScore.setScore(this.finalScore.getScore() + currentFrame.getScore());
						System.out.println("Final Score = " + this.finalScore.getScore());
						currentFrame.setFrameStatus("closed");
					}	
					
				}
				
			}
				
		}
	}
	
	public void print() {
		for(int i = 0; i < 10; i++) {
			
			if(this.frames[i].getRollScore(0) >= 0) {
				System.out.print(" " + "Frame " + i + " Roll 1 = ");
				System.out.print(this.frames[i].getRollScore(0));
				System.out.println();
				
			}
			if(this.frames[i].getRollScore(1) >= 0) {
				System.out.print(" " + "Frame " + i + " Roll 2 = ");
				System.out.print(this.frames[i].getRollScore(1));
				System.out.println();
			}
			
			if(i == 9) {
				System.out.print(" " + "Frame " + i + " Roll 3 = ");
				System.out.print(this.frames[i].getRollScore(2));
				System.out.println();
			}
		//	System.out.println("Frames " + i + " Score = " + this.frames[i].getScore());
			
		}
		//System.out.println("FINAL SCORE = " + this.finalScore.getScore());
	}
	
}
