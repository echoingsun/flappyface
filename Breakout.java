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

/** Width and height of application window in pixels.  IMPORTANT NOTE:
  * ON SOME PLATFORMS THESE CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS
  * OF THE GRAPHICS CANVAS.  Use getWidth() and getHeight() to get the 
  * dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  IMPORTANT NOTE: ON SOME PLATFORMS THESE 
  * CONSTANTS MAY **NOT** ACTUALLY BE THE DIMENSIONS OF THE GRAPHICS
  * CANVAS.  Use getWidth() and getHeight() to get the dimensions of
  * the graphics canvas. */
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
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

	
	RandomGenerator rg = new RandomGenerator();

	
	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		
		placeBricks();
		addMouseListeners();
		
	}


private void placeBricks() {
	/*
	 * Draw a matrix (NBRICKS_PER_ROW * NBRICK_ROWS) of bricks with different colors. 
	 * Each color goes by two rows. Therefore:
	 * One for loop should repeat (NBRICK_ROWS / 2) times.
	 * One for loop should make the program draw two rows of the same color of bricks.
	 */
	

	for (int k = 0; k <NBRICK_ROWS; k ++){
		for (int i = 0; i < NBRICKS_PER_ROW; i++){
			
			GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			
			// Set color.
			Color c = null;
			c = rainbowColor(k,c);
			
			
			double leftX = (getWidth() - BRICK_WIDTH * NBRICKS_PER_ROW - BRICK_SEP * (NBRICKS_PER_ROW-1))*0.5;
			double intervalX = BRICK_WIDTH+BRICK_SEP;	
			double intervalY = BRICK_HEIGHT+BRICK_SEP;
			
			add (brick, leftX + i * intervalX, BRICK_Y_OFFSET + k * intervalY);
		}
		
	}
	

	
}


private Color rainbowColor(int k, Color c) {
	if (k % 14 == 1 || k % 14 ==2){
		return Color.RED;
	}
	if (k % 14 == 3 || k % 14 ==4){
		return Color.ORANGE;
	}
	if (k % 14 == 5 || k % 14 == 6){
		return Color.YELLOW;
	}
	if (k % 14 == 7 || k % 14 == 8){
		return Color.GREEN;
	}
	if (k % 14 == 9 || k % 14 == 10){
		return Color.CYAN;
	}
	if (k % 14 == 11 || k % 14 == 12){
		return Color.BLUE;
	}
	if (k % 14 == 13 || k % 14 == 0){
		return Color.BLACK;
	}
	return null;
}



}
