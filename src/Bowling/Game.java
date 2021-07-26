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
			computeScore();
			this.incrementFrameCounter();
		}
		
		else if(isSpare(down)) {
			int rollOne = this.frames[this.frameCounter].getRollScore(0);
			frame.add(rollCount, (10 - rollOne));
			frame.setSpare();
			computeScore();
			this.incrementFrameCounter();
		}
		
		else if(down.equals("Miss")){
			frame.add(rollCount, 0);
			computeScore();
			if(this.frames[this.frameCounter].getRollCounter() == 0) {
				 
				frame.incrementRollCounter();
			}
			else {
				
				this.incrementFrameCounter();
			}
			
		}
		else {
			frame.add(rollCount, Integer.parseInt(down));
			computeScore();
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
	

	
	public void computeScore() {
		
		// if only one frame exist
		if(this.frameCounter == 0) {
			int finalScore = this.finalScore.getScore();
			Frame currentFrame = this.frames[0];
			if(currentFrame.isStrike || currentFrame.isSpare) {
				currentFrame.setScore(finalScore + 10 );
				this.finalScore.setScore( currentFrame.getScore());
				currentFrame.setFrameStatus("open");
			}
			else {
				if(currentFrame.getRollCounter() >= 1) {
					currentFrame.setScore(currentFrame.getRollScore(0) + currentFrame.getRollScore(1));
					this.finalScore.setScore(currentFrame.getScore());
					currentFrame.setFrameStatus("closed");
				}	
			}
		}
		//more than one frame exist
		else {
			
			for(int i = 0; i < this.frameCounter; i++) {	
				int finalScore = this.finalScore.getScore();
				Frame currentFrame = this.frames[i];
				Frame nextFrame = this.frames[i+1];
				if(i+1 != 9) {
						if(!currentFrame.isFrameClosed()) {
							if(currentFrame.isStrike && !nextFrame.isStrike && !nextFrame.isSpare && nextFrame.getRollCounter() >= 1) {
								currentFrame.setScore(finalScore + 10 + nextFrame.getRollScore(0) + nextFrame.getRollScore(1));
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("closed");
							}
							else if(currentFrame.isStrike && nextFrame.isSpare && nextFrame.getRollCounter() >= 1) {
								currentFrame.setScore(this.finalScore.getScore() + 10);
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("closed");
							}
							else if(currentFrame.isStrike && nextFrame.isStrike) {
								//check to see if next to next frame exist?
								if(this.frames[i+2].getRollScore(0) != -1) {
									//next to next frame exist
									currentFrame.setScore(finalScore + 10 + this.frames[i+2].getRollScore(0));
									this.finalScore.setScore(currentFrame.getScore());
									currentFrame.setFrameStatus("closed");
								}
								else {
									//next to next frame does not exist
									currentFrame.setScore(finalScore + 10);
									this.finalScore.setScore(currentFrame.getScore());
									currentFrame.setFrameStatus("open");
								}	
							}				
							else if(currentFrame.isSpare) {
								currentFrame.setScore(finalScore + 10 + nextFrame.getRollScore(0));
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("closed");
							}
							else {
								currentFrame.setFrameStatus("open");
								if(currentFrame.getRollCounter() >= 1) {
									currentFrame.setScore(finalScore + currentFrame.getRollScore(0) + currentFrame.getRollScore(1));
									this.finalScore.setScore(currentFrame.getScore());
									currentFrame.setFrameStatus("closed");
								}
							}
						}	
						else {
							//frame is closed. move to next frame
						}
				}
				//next frame is the last frame
				else {
					if(!currentFrame.isFrameClosed()) {
						if(currentFrame.isStrike && nextFrame.isStrike) {
							if(nextFrame.getRollScore(1) == -1) {
								currentFrame.setScore(finalScore + 10);
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("open");
							}
							else if(nextFrame.getRollScore(1) != -1) {
								currentFrame.setScore(finalScore + 10 + nextFrame.getRollScore(1));
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("closed");
							}	
						}
						else if(currentFrame.isStrike && nextFrame.isSpare && nextFrame.getRollCounter() >= 1) {
							currentFrame.setScore(finalScore + 10 + 10);
							this.finalScore.setScore( currentFrame.getScore());
							currentFrame.setFrameStatus("closed");
						}
						else if(currentFrame.isSpare) {
							currentFrame.setScore(finalScore + nextFrame.getRollScore(0) );
							this.finalScore.setScore( currentFrame.getScore());
							currentFrame.setFrameStatus("closed");
						}
						else {
							currentFrame.setFrameStatus("open");
							if(currentFrame.getRollCounter() >= 1) {
								currentFrame.setScore(finalScore + currentFrame.getRollScore(0) + currentFrame.getRollScore(1));
								this.finalScore.setScore(currentFrame.getScore());
								currentFrame.setFrameStatus("closed");
							}
						}
					}
					//next frame is the last frame
					else {
						nextFrame.setFrameStatus("open");
						if(nextFrame.isStrike && (nextFrame.getRollCounter() >= 2)) {
							nextFrame.setScore(finalScore + 10 +  nextFrame.getRollScore(1) + nextFrame.getRollScore(2));
							this.finalScore.setScore( nextFrame.getScore());
							nextFrame.setFrameStatus("closed");
							
						}
						else if(nextFrame.isSpare && (nextFrame.getRollCounter() >= 2)) {
							nextFrame.setScore(finalScore + 10 +  nextFrame.getRollScore(2));
							this.finalScore.setScore( nextFrame.getScore());
							nextFrame.setFrameStatus("closed");
						}
						else {
							if(nextFrame.getRollCounter() >= 1) {
								nextFrame.setScore(finalScore + nextFrame.getRollScore(0) + nextFrame.getRollScore(1));
								this.finalScore.setScore( nextFrame.getScore());
								nextFrame.setFrameStatus("closed");
							}
						}
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
			System.out.println("Frames " + i + " Score = " + this.frames[i].getScore());
			
		}
	}
	
}
