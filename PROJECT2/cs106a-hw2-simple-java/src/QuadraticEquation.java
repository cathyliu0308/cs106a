/*
 * File: QuadraticEquation.java
 * Name:
 * Section Leader:
 * -----------------------------
 * TODO: add a program comment
 */

import acm.program.*;

public class QuadraticEquation extends ConsoleProgram {
	//Font myFont = new Font("Courier New",Font.PLAIN, 6);
	public void run() {
		println ("CS 106A Quadratic Solver!");
		int a = readInt ("Enter a: ");
		int b = readInt ("Enter b: ");
		int c = readInt ("Enter c: ");
		double n = Math.pow(b, 2) - 4 * a * c;
		if (n < 0) {
			println ("No real roots");
		}
		else {
			double m = Math.sqrt (n);
			double x0 = (-b + m) / (2 * a);
			double x1 = (-b - m) / (2 * a);
			if (x0 == x1){
				println ("One root: " + x0);
			}
			else {
				println ("Two roots: " + x0 + " and " + x1);
			}
		}		
	}	
}