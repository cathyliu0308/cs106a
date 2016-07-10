/*
 * TODO: comment this program
 */

import stanford.karel.*;

public class CollectNewspaper extends SuperKarel {
	
	public void run() {
		// TODO: write the code to implement this program
		moveToBeeper();				
		pickBeeper();
		returnToStart();	
	}
	
	private void moveToBeeper() {
		moving();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	private void returnToStart(){
		turnRight();
		turnRight();
		move();
		turnRight();
		moving();
		turnLeft();
		moving();
		turnRight();
		turnRight();
	}
	
	private void moving(){
		while(frontIsClear()) {
			move();
		}
	}
}
