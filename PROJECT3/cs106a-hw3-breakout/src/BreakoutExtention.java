// TODO: comment this program

import acm.graphics.*;     // GOval, GRect, etc.
import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator

import java.applet.*;      // AudioClip
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent

public class BreakoutExtention extends BreakoutProgram {
	GRect brick;
	//GRect paddle;
	//GOval ball;
	public void run() {
		// Set the window to a nice size for our game
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		drawWall();
		GRect paddle = addPaddle();
		GOval ball = makeBall();
		//pause(500);
		bounceBall(ball);
		GObject collider = checkCollision(ball);
		if (collider == null) {
			//continue;
		}
		else if (collider == paddle) {
			
		}
		else if (collider == brick) {
			remove(brick);
		}
		
	}
	
	private void drawWall() {
		int color;
		for (int i = 0; i < NBRICK_ROWS; i++ ){
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				brick = new GRect(j * (BRICK_WIDTH + BRICK_SEP), BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
				color = i % 10;
				if (color == 0 || color == 1)
					brick.setColor(Color.RED);
				else if (color == 2 || color == 3)
					brick.setColor(Color.ORANGE);
				else if (color == 4 || color == 5)
					brick.setColor(Color.YELLOW);
				else if (color == 6 || color == 7)
					brick.setColor(Color.GREEN);
				else if (color == 8 || color == 9)
					brick.setColor(Color.CYAN);
				brick.setFilled(true);
				add(brick);
			}
		}		
	}
	
	private GRect addPaddle() {
		GRect paddle = new GRect(0, getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		return paddle;
	}
	
	private GOval makeBall() {
		GOval ball = new GOval (getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setColor(Color.GRAY);
		ball.setFilled(true);
		add(ball);
		return ball;
	}
	
	private void bounceBall(GOval ball) {
		RandomGenerator rgen = RandomGenerator.getInstance();
		double vx = rgen.nextDouble(1.0, 3.0);
//		boolean direction = rgen.nextBoolean();
		double vy = 3.0;
		ball.move(vx,vy);
		double dy = 5.0;
		while (true) {
			ball.move(vx,vy);
			pause(50);
		}
	}
	private GObject checkCollision(GOval ball) {
		GObject collider = getElementAt(ball.getX(), ball.getY(), ball.getX(), ball.getY()+ 2 * BALL_RADIUS, ball.getX()+ 2 * BALL_RADIUS, ball.getY(), ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		return collider;
						
	}
	
}
