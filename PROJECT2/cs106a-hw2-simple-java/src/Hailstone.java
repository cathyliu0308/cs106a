/*
 * File: Hailstone.java
 * Name:
 * Section Leader:
 * --------------------
 * TODO: add a program comment 
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	public void run() {
		//int n;
		int cur = readInt ("Enter a number: ");
		int post;
		int cnt = 0;
		while (cur != 1) {
			if (cur % 2 == 1){
				post = 3 * cur + 1;
				println (cur + " is odd, so I make 3n + 1: " + post);
			}
			else{
				post = cur / 2;
				println (cur + " is even, so I take half: " + post);
			}
			cur = post;
			cnt ++;
		}
		println ("The process took " + cnt + " steps to reach 1");
	}
}