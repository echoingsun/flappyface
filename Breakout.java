
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/**
	 * Width and height of application window in pixels. IMPORTANT NOTE: ON SOME
	 * PLATFORMS THESE CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS OF THE
	 * GRAPHICS CANVAS. Use getWidth() and getHeight() to get the dimensions of
	 * the graphics canvas.
	 */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/**
	 * Dimensions of game board. IMPORTANT NOTE: ON SOME PLATFORMS THESE
	 * CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS OF THE GRAPHICS CANVAS.
	 * Use getWidth() and getHeight() to get the dimensions of the graphics
	 * canvas.
	 */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private static final int DELAY = 10;

	// Define a random generator for generating random speed.
	private RandomGenerator rg = new RandomGenerator();

	// Define bricks, the ball, the paddle and some labels as instance
	// variables.
	GRect brick = null;
	GOval ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
	GRect paddle = null;
	GLabel turnsLeft = null;

	// Also define two counts: how many bricks are on the screen; how many turns
	// left.
	int count = NBRICKS_PER_ROW * NBRICK_ROWS;
	int gameOverCount = NTURNS;
	

	double vx = rg.nextDouble(1.0, 3.0);
	double vy = rg.nextDouble(-3.0, -1.0);



	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {

		// Add mouse listeners to the program.
		addMouseListeners();

		// Play the game for maximum NTURNS times if user fails to clear stage
		// at some point.
		for (gameOverCount = NTURNS; gameOverCount > 0;) {
			play();
		}

		// Once user has used up all 3 chances, clear the canvas.
		removeAll();

	}

	private void play() {
		
		//Before each turn starts, clear all objects and re-place them on the canvas.
		removeAll();
		placeItems();
		

		// Set a 50% possibility for vx to be reversed.
		if (rg.nextBoolean(0.5)) {
			vx = -vx;
		}

		waitForClick();

		while (!gameOver() && count > 0) {

			bounceAround();
			hitAndRemove();

			ball.move(vx, vy);
			pause(DELAY);
		}

		// If the player lost one turn, gameOverCount -1 and show game over message.
		if (gameOver()) {
			gameOverCount--;
			gameOverMessage();

		}
		
		// If the bricks are all cleared (count=0), show stage clear message.
		if (count == 0) {
			stageClear();
		}

	}

	private void hitAndRemove() {
		if (hitBricks()) {			
			removeBricks();			
		}

	}

	private void removeBricks() {
		double x1 = ball.getX();
		double y1 = ball.getY();
		double x2 = ball.getX() + 2 * BALL_RADIUS;
		double y2 = ball.getY() + 2 * BALL_RADIUS;
		double cx = x1 + BALL_RADIUS;
		double cy = y1 + BALL_RADIUS;

		GObject obj01 = getElementAt(x1, y1);
		GObject obj02 = getElementAt(x1, y2);
		GObject obj03 = getElementAt(x2, y1);
		GObject obj04 = getElementAt(x2, y2);
		
		/*
		 * obj01 is the object that collides with the top left corner (imaginary) of the ball.
		 * When there is an obj 01, it can be:
		 * (1) the brick which the ball hits from the bottom upwards;
		 * (2) the brick which the ball hits from the right leftwards;
		 * For every brick that's removed, let the brick count "count" -1.
		 */
		if (obj01 != null) {

			/*
			 * Check if the ball is hitting two bricks from the bottom upwards at the same time,
			 * or the ball hits somewhere in between the brick's width (then obj01 == obj03).
			 */			
			if (obj03 == null) {				
				if (cy > (obj01.getY() + BRICK_HEIGHT)) {
					vy = -vy; // Case (1)
				} else if (obj01.getX() > cx) {
					vx = -vx; // Case (2)
				}
			} else {
				if (obj03 != null && obj03 != obj01) {
					remove(obj03); // If obj03 is another brick, remove it too.
					count--;
					vy = -vy;
				}
				if (obj03 == obj01) {
					vy = -vy; // If obj03 is the same brick, just reverse vy.
				}
			}
			
			// In any case, obj01 will be removed.
			remove(obj01);
			count--;
		}

		// The similar check process applies to obj02 and 04.

		if (obj02 != null) {

			if (obj04 == null) {
				if (obj02.getY() > cy) {
					vy = -vy;
				} else if (obj02.getX() > cx) {
					vx = -vx;
				}
			} else {
				if (obj04 != null && obj04 != obj02) {
					remove(obj04);
					count--;
					vy = -vy;
				}
				if (obj04 == obj02) {
					vy = -vy;
				}
			}
			remove(obj02);
			count--;
		}

		/*
		 * Since some cases are already discussed in the obj01 section,
		 * for obj03 only one situation is discussed here:
		 * when the ball is hitting obj03 ONLY.
		 */
		if (obj03 != null && obj01 == null) {
			if (cy > (obj03.getY() + BRICK_HEIGHT)) {
				vy = -vy;
			} else if (cx > obj03.getX() + BRICK_WIDTH) {
				vx = -vx;
			}
			remove(obj03);
			count--;
		}

		// obj04 is similar to obj03.
		if (obj04 != null && obj02 == null) {
			if (obj04.getY() > cy) {
				vy = -vy;
			} else if (cx > obj04.getX() + BRICK_WIDTH) {
				vx = -vx;
			}
			remove(obj04);
			count--;
		}

		
	}

	private void bounceAround() {
		if (hitWalls() || hitPaddleSide() && vy > 0) {
			vx = -vx;
		}
		if (hitCeiling() || (hitPaddle())) {
			vy = -vy;
		}
	}

	private void placeItems() {

		// Make and place the paddle.
		paddle = makePaddle();
		placePaddle();

		// Make and place the ball.
		ball = makeBall();
		placeBall();

		// Draw the bricks.
		placeBricks();

	}

	private void placeBall() {
		double x = getWidth() * 0.5 - BALL_RADIUS;
		double y = getHeight() - PADDLE_Y_OFFSET - 2 * BALL_RADIUS;
		add(ball, x, y);
	}

	private GOval makeBall() {
		ball.setFilled(true);
		ball.setColor(Color.DARK_GRAY);
		return ball;
	}

	private void stageClear() {
		GLabel stageClear = new GLabel("STAGE CLEAR!");
		stageClear.setFont("*-56");
		add(stageClear, (getWidth() - stageClear.getWidth()) * 0.5, (getHeight() - stageClear.getAscent()) * 0.5);

	}

	private boolean gameOver() {
		// Game is over when the ball falls and it falls below the bottom.
		double y1 = ball.getY();
		return vy > 0 && y1 >= getHeight();
	}

	private void gameOverMessage() {
		GLabel gameOverMessage = new GLabel("GAME OVER");
		gameOverMessage.setFont("*-56");

		if (gameOverCount > 0) {
			turnsLeft = new GLabel(gameOverCount + " turn(s) left");
		} else {
			turnsLeft = new GLabel("You lost all your lives T_T");
		}

		add(gameOverMessage, (getWidth() - gameOverMessage.getWidth()) * 0.5,
				(getHeight() - gameOverMessage.getAscent()) * 0.5);
		add(turnsLeft, (getWidth() - turnsLeft.getWidth()) * 0.5, gameOverMessage.getY() + 20);
		pause(1500);
		remove(gameOverMessage);
		remove(turnsLeft);
	}

	private boolean hitBricks() {
		double x1 = ball.getX();
		double y1 = ball.getY();
		double x2 = ball.getX() + 2 * BALL_RADIUS;
		double y2 = ball.getY() + 2 * BALL_RADIUS;

		GObject obj01 = getElementAt(x1, y1);
		GObject obj02 = getElementAt(x1, y2);
		GObject obj03 = getElementAt(x2, y1);
		GObject obj04 = getElementAt(x2, y2);
		
		return (obj01 != null || obj02 != null || obj03 != null || obj04 != null)
				&& (obj01 != paddle && obj02 != paddle && obj03 != paddle && obj04 != paddle);
	}

	private boolean hitPaddle() {
		/*
		 * This method defines the behavior of the ball when it hits the top of the paddle.
		 * The lower coordinate(s) of the ball is greater than / equals the coordinate of the paddle.
		 */
		double x1 = ball.getX();
		double x2 = ball.getX() + 2 * BALL_RADIUS;
		double y2 = ball.getY() + 2 * BALL_RADIUS;

		GObject obj02 = getElementAt(x1, y2);
		GObject obj04 = getElementAt(x2, y2);
		
		return y2 >= paddle.getY() && (obj02 == paddle || obj04 == paddle) && vy > 0;
	}

	private boolean hitPaddleSide() {
		/*
		 * Sometimes the ball can hit the paddle's left or right side.
		 * This can only happen when either (x1,y2) or (x2,y2) hits the paddle's sides.
		 */
		double x1 = ball.getX();
		double x2 = ball.getX() + 2 * BALL_RADIUS;
		double y2 = ball.getY() + 2 * BALL_RADIUS;
		double cx = x1 + BALL_RADIUS;
		GObject obj02 = getElementAt(x1, y2);
		GObject obj04 = getElementAt(x2, y2);
		return (obj04 == paddle && cx < paddle.getX() && y2 > paddle.getY()) || (obj02 == paddle && cx > paddle.getX() + PADDLE_WIDTH && y2 > paddle.getY());
	}

	private boolean hitCeiling() {
		double y1 = ball.getY();
		return y1 <= 0;
	}

	private boolean hitWalls() {
		double x1 = ball.getX();
		double x2 = ball.getX() + 2 * BALL_RADIUS;
		return x1 <= 0 || x2 >= getWidth();
	}

	private void placePaddle() {
		double x = (getWidth() - PADDLE_WIDTH) * 0.5;
		double y = getHeight() - PADDLE_Y_OFFSET;
		add(paddle, x, y);
	}

	private GRect makePaddle() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.GRAY);
		return paddle;
	}

	public void mouseMoved(MouseEvent e) {
		double x = e.getX() - PADDLE_WIDTH * 0.5;
		double y = getHeight() - PADDLE_Y_OFFSET;
		double xx = e.getX() + PADDLE_WIDTH * 0.5;

		if (x > 0 && xx < getWidth()) {
			paddle.setLocation(x, y);
		} else {
			if (x < 0) {
				paddle.setLocation(0, y);
			} else if (xx > getWidth()) {
				paddle.setLocation(getWidth() - PADDLE_WIDTH, y);
			}
		}
	}

	private void placeBricks() {
		/*
		 * Draw a matrix (NBRICKS_PER_ROW * NBRICK_ROWS) of bricks with
		 * different colors. Each color goes by two rows. Therefore: One for
		 * loop should repeat (NBRICK_ROWS / 2) times. One for loop should make
		 * the program draw two rows of the same color of bricks.
		 */

		for (int k = 1; k <= NBRICK_ROWS; k++) {
			for (int i = 0; i < NBRICKS_PER_ROW; i++) {

				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);

				// Check which line it is and set accordingly the color.
				Color c = null;
				c = rainbowColor(k, c);
				brick.setColor(c);

				double leftX = (getWidth() - BRICK_WIDTH * NBRICKS_PER_ROW - BRICK_SEP * (NBRICKS_PER_ROW - 1)) * 0.5;
				double intervalX = BRICK_WIDTH + BRICK_SEP;
				double intervalY = BRICK_HEIGHT + BRICK_SEP;

				// lay the bricks.
				add(brick, leftX + i * intervalX, BRICK_Y_OFFSET + (k - 1) * intervalY);
			}

		}

	}

	private Color rainbowColor(int k, Color c) {
		if (k % 14 == 1 || k % 14 == 2) {
			return Color.RED;
		} else if (k % 14 == 3 || k % 14 == 4) {
			return Color.ORANGE;
		} else if (k % 14 == 5 || k % 14 == 6) {
			return Color.YELLOW;
		} else if (k % 14 == 7 || k % 14 == 8) {
			return Color.GREEN;
		} else if (k % 14 == 9 || k % 14 == 10) {
			return Color.CYAN;
		} else if (k % 14 == 11 || k % 14 == 12) {
			return Color.BLUE;
		} else if (k % 14 == 13 || k % 14 == 0) {
			return Color.BLACK;
		} else {
			return null;
		}

	}

}
