/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * TODO: add a program comment
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 20;

	
	public void run() {	
		for (int i = BRICKS_IN_BASE; i > 0; i --){
			for (int j = 0; j < i; j++){
				double x = getWidth()/2 - BRICK_WIDTH * i / 2 + BRICK_WIDTH * j;
				double y = getHeight()- BRICK_HEIGHT * (BRICKS_IN_BASE + 1 - i);
				GRect brick = new GRect (x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
			}
		}
	}
	
}