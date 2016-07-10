// TODO: comment this program

import acm.graphics.*;     // GOval, GRect, etc.
import acm.program.*;      // GraphicsProgram
import acm.util.*;         // RandomGenerator

import java.applet.*;      // AudioClip
import java.awt.*;         // Color
import java.awt.event.*;   // MouseEvent
import java.util.*;        // Hashset

//public class Breakout extends GraphicsProgram
public class Breakout extends BreakoutProgram {
	//GRect brick;
	private GRect paddle;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double initX;
	private double initY;
	private int score = 0;
	private int fullscore = NBRICK_ROWS * NBRICKS_PER_ROW;
	private int turn = NTURNS;
	private GLabel scoreLabel;
	//private GObject collider;
	HashSet<GRect> bricks = new HashSet<GRect>();
	
	public void run() {
		// Set the window to a nice size for our game
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		addScore();
		drawWall();
		addPaddle();
		makeBall();
		moveBall();
		bounceBall();
	}
	
	private void drawWall() {
		int color;
		for (int i = 0; i < NBRICK_ROWS; i++ ){
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				GRect brick = new GRect(j * (BRICK_WIDTH + BRICK_SEP), BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
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
				bricks.add(brick);
				brick.setFilled(true);
				add(brick);
			}
		}		
	}
	
	private void addPaddle() {
		paddle = new GRect(0, getHeight()-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
		//return paddle;
	}
	
	private void makeBall() {
		ball = new GOval (getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setColor(Color.GRAY);
		ball.setFilled(true);
		add(ball);
		//return ball;
	}
	
	private void moveBall() {
		initY = 3.0;
		initX = rgen.nextDouble(1.0, 3.0);
		boolean direction = rgen.nextBoolean();		
		if (direction == false) {
			initX = -initX;
		}
		//System.out.println (initX);
	}
	private void bounceBall() {
		//double dX = initX;
		//double dY = initY;				
		//System.out.println (initX);
		AudioClip bounceClip = MediaTools.loadAudioClip("res/bounce.au");
		while (true) {
			ball.move(initX,initY);
			pause(DELAY);
			if (ball.getX() <= 0 || (ball.getX() + 2 * BALL_RADIUS) >= getWidth()) {
				bounceClip.play();
				initX = -initX;
			}
			//else if (ball.getY() <= 0 || ((ball.getY() + 2 * BALL_RADIUS) >= getHeight()) ) {
			else if (ball.getY() <= 0) {
				bounceClip.play();
				initY = -initY;
				//System.out.println ("###");
				//System.out.println (getWidth());
				
			}
			else if ((ball.getY() + 2 * BALL_RADIUS) >= getHeight()) {
				turn --;
				scoreLabel.setLabel("Score: " + score + ", " + "Turns: " + turn);
				if (turn > 0) {
					ball.setLocation(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
					pause(50);
					moveBall();
					//break;
				}
				else {
					remove(ball);
					remove(paddle);
					theResult();
					break;
				}
			}
			
			else {
				GObject collider = checkCollision();
				if (collider == null) {
					//ball.move(dX,dY * flag);
					//pause(50);
					continue;
				}
				else if (collider == paddle) {
					//ball.setLocation(ball.getX(), ball.getY() - (ball.getHeight() - paddle.getY() + ball.getY()) - 1);
					bounceClip.play();
					initY = -initY;
				}
				else {
					if (collider != scoreLabel) {
						bounceClip.play();
						bricks.remove(collider);
						remove(collider);						
						score ++;
						scoreLabel.setLabel("Score: " + score + ", " + "Turns: " + turn);
						//ball.setLocation(ball.getX(), ball.getY());
						initY = -initY;
						if (score == fullscore) {
							theResult();
							break;
						}	
					}
				}
			}
		}
	}
	
	private GObject checkCollision() {
		return getElementAt(ball.getX(), ball.getY(), ball.getX() + 2 * BALL_RADIUS, ball.getY(), ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS, ball.getX(), ball.getY()  + 2 * BALL_RADIUS);
						
	}
	
//	public void mouseDragged(MouseEvent e) {
		//paddle.move(e.getX(), e.getY());
//		System.out.println ("*" + e.getX());
		//lastX = e.getX();
		//lastY = e.getY();
//	}
	public void mouseMoved(MouseEvent e) {
		//System.out.println (e.getX());
		if ((e.getX() <= getWidth() - paddle.getWidth() + paddle.getWidth()/2) && (e.getX() >= paddle.getWidth()/2)) {
			//System.out.println ("moved");
			double dx = e.getX() - paddle.getX() - paddle.getWidth()/2;
			paddle.move(dx, 0);
		}		
	}
	
	private void addScore() {
		scoreLabel = new GLabel("Score: " + score + ", " + "Turns: " + turn);
		scoreLabel.setFont(SCREEN_FONT);
		double y = scoreLabel.getAscent();
		add(scoreLabel, 0, y);
	}
	
	private void theResult() {
		GLabel result;
		if (score == fullscore)
			result = new GLabel("YOU WIN!");
		else {
			result = new GLabel("GAME OVER");
			for (GRect i:bricks) {
				i.setFilled(false);
			}
		}
		result.setFont(SCREEN_FONT);
		double y = result.getAscent();
		double x = result.getWidth();
		add(result, getWidth()/2 - x/2, getHeight()/2 - y);
	}
}
