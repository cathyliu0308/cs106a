/*
 * File: Target.java
 * Name:
 * Section Leader:
 * -----------------
 * TODO: add a program comment 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	private static final double LARGE = 72;
	public void run() {
		double middle = 0.65 * LARGE;
		double small = 0.3 * LARGE;
		double midX = getWidth() / 2;
		double midY = getHeight() / 2;
		GOval circle1 = new GOval (midX - LARGE, midY - LARGE, 2 * LARGE, 2 * LARGE);
		circle1.setColor(Color.RED);
		circle1.setFilled(true);
		add(circle1);
		GOval circle2 = new GOval (midX - middle, midY - middle, 2 * middle, 2 * middle);
		circle2.setColor(Color.WHITE);
		circle2.setFilled(true);
		add(circle2);
		GOval circle3 = new GOval (midX - small, midY - small, 2 * small, 2 * small);
		circle3.setColor(Color.RED);
		circle3.setFilled(true);
		add(circle3);
	}
	/*
	private void drawCircle (double x, double y, double r){
		GOval circle = new GOval (x, y, 2*r, 2*r);
	}
	*/
}