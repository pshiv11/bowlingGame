package Bowling;

public class Game {
	
	Frame[] frames;
	int frameCounter;
	
	public Game() {
		this.frames = new Frame[10];
		this.frameCounter = 0 ;
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
		if(down.equals("Strike")) {
			this.frames[this.frameCounter].add(this.frames[this.frameCounter].getRollCounter(), 10);
			this.incrementFrameCounter();
		}
		
	
		
		else if(down.equals("Spare")) {
			int rollOne = this.frames[this.frameCounter].getRollValue(0);
			this.frames[this.frameCounter].add(this.frames[this.frameCounter].getRollCounter(), (10 - rollOne));
			this.incrementFrameCounter();
		}
		
		else if(down.equals("Miss")){
			this.frames[this.frameCounter].add(this.frames[this.frameCounter].getRollCounter(), 0);
			
			if(this.frames[this.frameCounter].getRollCounter() == 0) {
				this.frames[this.frameCounter].incrementRollCounter();
			}
			else {
				this.incrementFrameCounter();
			}
		}
		else {
			this.frames[this.frameCounter].add(this.frames[this.frameCounter].getRollCounter(), Integer.parseInt(down));
			
			if(this.frames[this.frameCounter].getRollCounter() == 0) {
				this.frames[this.frameCounter].incrementRollCounter();
			}
			else {
				this.incrementFrameCounter();
			}
			
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
