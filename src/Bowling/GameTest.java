package Bowling;

public class GameTest {
	
	
	public static void main(String[] args) {
		Game bowling = new Game();
		bowling.init();
		
		bowling.roll("Strike");
		bowling.roll("7");
		bowling.roll("Spare");
		bowling.roll("9");
		bowling.roll("Miss");
		bowling.roll("Strike");
		bowling.roll("Miss");
		bowling.roll("8");
		bowling.roll("8");
		bowling.roll("Spare");
		bowling.roll("Miss");
		bowling.roll("6");
		bowling.roll("Strike");
		bowling.roll("Strike");
		bowling.roll("Strike");
		bowling.roll("8");
		bowling.roll("1");
		
		
		bowling.print();
		// adding to git
	}
	

}
