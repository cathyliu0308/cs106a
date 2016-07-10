/*
 * TODO: comment this program
 */

import stanford.karel.*;

public class StoneMason extends SuperKarel {	
	public void run() {	
		int cnt=0;
		while (true){
			int direction=0;
			
			if(cnt%8==0)
				direction=0;
			else if(cnt%8==4)
				direction=2;
			else
				direction=1;
			if(direction==0 || direction==2)
			{
				if(direction==0)
					turnLeft();
				else
					turnRight();
				
				while (true)
				{
					if (noBeepersPresent())
						putBeeper();
					
					if (frontIsClear())
						move();
					else
						break;
				}
				
				if(direction==0)
					turnRight();
				else
					turnLeft();
			}
			if (frontIsClear()){
				move();
				cnt++;
			}
			else 
				break;
			
		}
	}		
		// TODO: write the code to implement this program
}
