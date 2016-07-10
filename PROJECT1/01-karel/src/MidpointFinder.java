/*
 * TODO: comment this program
 */

import stanford.karel.*;

public class MidpointFinder extends SuperKarel {
	
	public void run() {
		int cnt = 0 ;
		int total = 0;
		while (frontIsClear()){
			move();
			cnt ++;
		}
		total = cnt;
		//facingWest();
		turnRight();
		turnRight();
		while (cnt != total / 2){
			move();
			cnt --;
		}
		putBeeper();
		turnRight();
		turnRight();
		//facingEast();
	}

}
