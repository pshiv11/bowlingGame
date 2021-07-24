package Bowling;

public class Roll  {


	private int pinsDown ;

	public Roll() {
		
		this.pinsDown = -1;
	}
	
	
	public void add(int pins) {
		this.pinsDown = pins;
		
	}


	public int getValue() {
		// TODO Auto-generated method stub
		return this.pinsDown;
	}
	
}
	
	



