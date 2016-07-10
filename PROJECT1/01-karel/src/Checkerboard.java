/*
 * TODO: comment this program
 */

import stanford.karel.*;

public class Checkerboard extends SuperKarel {
	
	public void run() {
		int x = 1;
		while (true) {
			if (x % 2 == 1){
				turnLeft();
				int y = 1;
				while (true) {
					if (y % 2 == 1){
						putBeeper();
					}
					if (frontIsClear()) {
						move();
						y++;
					} else {
						break;
					}
				}
				turnRight();
				turnRight();
				while (frontIsClear()){
					move();
				}
				turnLeft();
			} else {
				turnLeft();
				int y = 1;
				while (true) {
					if (y % 2 == 0){
						putBeeper();
					}
					if (frontIsClear()) {
						move();
						y++;
					} else {
						break;
					}
				}
				turnRight();
				turnRight();
				while (frontIsClear()){
					move();
				}
				turnLeft();
			}
			if (frontIsClear()) {
				move();
				x++;
			} else{
				break;
			}
			
		}
	}

}
