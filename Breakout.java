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
	private static final int PADDLE_WIDTH = 400;
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
	
	private static final int DELAY = 10;

	
	private RandomGenerator rg = new RandomGenerator();
	
	/*
	 * Since the same ball and paddle is connected with several other methods,
	 * they can be defined as instance variables.
	 */
	GRect brick = null;
	GOval ball = new GOval (2*BALL_RADIUS, 2*BALL_RADIUS);
	GRect paddle = null;
	
	int count = NBRICKS_PER_ROW * NBRICK_ROWS;

	
/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		
		// Make and place the paddle.
		paddle = makePaddle();
		placePaddle();
		
		// Make and place the ball.
		ball = makeBall();
		placeBall();
		
		// Draw the bricks.
		placeBricks();
		

		
		addMouseListeners();
		
		play();
				
	}

private void placeBall() {
	double x = getWidth() * 0.5 - BALL_RADIUS;
	double y = getHeight() -PADDLE_Y_OFFSET - 2 * BALL_RADIUS;
	add (ball, x,y);
}

private GOval makeBall() {
	ball.setFilled(true);
	ball.setColor(Color.DARK_GRAY);
	return ball;
}

private void play() {
	
	double vx = rg.nextDouble(1.0,3.0);
	if (rg.nextBoolean(0.5)){
		vx= -vx;
	}
	double vy = rg.nextDouble (-3.0, -1.0);
	
	waitForClick();
	

	
	while (!gameOver(vy) && count >0){
		
		if (hitWalls() || hitPaddleSide() && vy >0){
			vx = -vx;
		}
		if (hitCeiling() || (hitPaddle() && vy > 0)){
			vy = -vy;
		}
		if (hitBricks()){
			/*removeBrick(ball.getX(),ball.getY(),vx, vy)*/;
			double x = ball.getX();
			double y = ball.getY();
			double cx = x + BALL_RADIUS;
			double cy = y + BALL_RADIUS;
			
			GObject obj01 = getElementAt(x + 2*BALL_RADIUS, y);
			GObject obj02 = getElementAt(x + 2*BALL_RADIUS, y + 2*BALL_RADIUS);
			GObject obj03 = getElementAt(x, y);
			GObject obj04 = getElementAt(x, y + 2*BALL_RADIUS);
			
			// Show how many bricks are left.
			GLabel countLabel = new GLabel (count + "bricks left");
			add (countLabel,0, countLabel.getAscent());
			
			
/*			 * The upper right corner of the ball-square hits a brick.
			 * The ball can either be hitting a brick above it, 
			 * or a brick to its right.
			 * or, in rare cases, hitting the bottom left corner of a brick.*/
			 
			if (obj01 != null){
				
				if (obj03 == null){
					if (cy > (obj01.getY() + BRICK_HEIGHT)){
						vy = -vy;
					} 
					if (obj01.getX() > cx){
						vx = -vx;
					}
					
				} else {
					if (obj03 != null && obj03 !=obj01){
						remove(obj03);
						count --;
						vy= -vy;
					}
					if (obj03 == obj01){
						vy = -vy;
					}
				}
				remove (obj01);	
				count --;
			} 
			
			
/*			 * The bottom right of the ball hits a brick.
			 * The ball can either be hitting a brick beneath it,
			 * or a brick to its right.*/
			 
			if (obj02 != null){
				
				if (obj04 == null){
					
					if (obj02.getY() > cy){
						vy= - vy;
					}
					if (obj02.getX() > cx){
						vx= -vx;
					}		
					
				} else {
					if (obj04 != null && obj04 != obj02){
						remove(obj04);
						count --;
						vy = -vy;						
					}
					if (obj04 == obj02){
						vy=-vy;
					}
				}
				remove (obj02);
				count --;
			}
			
			
/*			 * The upper left corner of the ball hits a brick.
			 * The ball can either be hitting a brick above it,
			 * or a brick to its left.*/
			 
			if (obj03 != null && obj01 == null){
				if (cy > (obj03.getY() + BRICK_HEIGHT)){
					vy = -vy;
				} 
				if (cx > obj03.getX() + BRICK_WIDTH){
					vx = -vx;
				}
				remove (obj03);
				count --;
			} 
			
			
				/*
				 * * The bottom left corner of the ball hits a brick. The ball
				 * can either be hitting a brick beneath it, or a brick to its
				 * left.
				 */
			 
			if (obj04 != null && obj02 == null){
				if (obj04.getY() > cy){
					vy = -vy;
				} 
				if (cx > obj04.getX() + BRICK_WIDTH){
					vx = -vx;
				}
				remove (obj04);
				count --;
			}
			
			
		}
		ball.move(vx, vy);
		pause(DELAY);
		
	}
	
	if (gameOver(vy)){
		gameOverMessage();
	}
	if (count ==0){
		stageClear();
	}
	
	
	
	
}





private void stageClear() {
	GLabel stageClear = new GLabel("GAME OVER");
	stageClear.setFont("*-56");
	add (stageClear, (getWidth()-stageClear.getWidth()) * 0.5, (getHeight() - stageClear.getAscent()) * 0.5);
	
}

private boolean gameOver(double vy) {
	return vy>0 && ball.getY() >= getHeight();
}



private void gameOverMessage() {
	GLabel gameOverMessage = new GLabel("GAME OVER");
	gameOverMessage.setFont("*-56");
	add (gameOverMessage, (getWidth()-gameOverMessage.getWidth()) * 0.5, (getHeight() - gameOverMessage.getAscent()) * 0.5);
	
}

/*private void removeBrick(double x, double y, double vx, double vy) {
	GObject obj01 = getElementAt(x + 2*BALL_RADIUS, y);
	GObject obj02 = getElementAt(x + 2*BALL_RADIUS, y + 2*BALL_RADIUS);
	GObject obj03 = getElementAt(x, y);
	GObject obj04 = getElementAt(x, y + 2*BALL_RADIUS);
	
	double cx = x + BALL_RADIUS;
	double cy = y + BALL_RADIUS;
	
	
	 * The upper right corner of the ball-square hits a brick.
	 * The ball can either be hitting a brick above it, 
	 * or a brick to its right.
	 * or, in rare cases, hitting the bottom left corner of a brick.
	 
	if (obj01 != null){
		if (cy > (obj01.getY() + BRICK_HEIGHT)){
			vy = -vy;
		} 
		if (obj01.getX() > cx){
			vx = -vx;
		}
		remove (obj01);

	} 
	
	
	 * The bottom right of the ball hits a brick.
	 * The ball can either be hitting a brick beneath it,
	 * or a brick to its right.
	 
	if (obj02 != null){
		if (obj02.getY() > cy){
			vy= - vy;
		}
		if (obj02.getX() > cx){
			vx= -vx;
		}		
		remove (obj02);
	}
	
	
	 * The upper left corner of the ball hits a brick.
	 * The ball can either be hitting a brick above it,
	 * or a brick to its left.
	 
	if (obj03 != null){
		if (cy > (obj03.getY() + BRICK_HEIGHT)){
			vy = -vy;
		} 
		if (cx > obj03.getX() + BRICK_WIDTH){
			vx = -vx;
		}
		remove (obj03);
	} 
	
	
	 * The bottom left corner of the ball hits a brick.
	 * The ball can either be hitting a brick beneath it,
	 * or a brick to its left.
	 
	if (obj04 != null){
		if (obj04.getY() > cy){
			vy = -vy;
		} 
		if (cx > obj04.getX() + BRICK_WIDTH){
			vx = -vx;
		}
		remove (obj04);
	}
	
}*/


private boolean hitBricks(){
	GObject obj01 = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
	GObject obj02 = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
	GObject obj03 = getElementAt(ball.getX(), ball.getY());
	GObject obj04 = getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
	return (obj01 != null || obj02 != null || obj03 != null || obj04 !=null) && (obj01 != paddle && obj02 != paddle && obj03 != paddle && obj04 != paddle);
}



private boolean hitPaddle() {
	GObject obj = getElementAt(ball.getX() + 2* BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);	
	return ball.getY() + 2*BALL_RADIUS >= paddle.getY() && obj == paddle;
}

private boolean hitPaddleSide(){
	double c = ball.getX() + BALL_RADIUS;
	
	GObject obj01 = getElementAt(ball.getX(), ball.getY() + 2* BALL_RADIUS);
	GObject obj02 = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2* BALL_RADIUS);
	return (obj01 == paddle || obj02 == paddle) && (ball.getY() + 2* BALL_RADIUS) > paddle.getY() && (c<paddle.getX() || c>paddle.getX()+PADDLE_WIDTH) ;
}

private boolean hitCeiling() {
	return ball.getY() <= 0 ;
}

private boolean hitWalls() {
	return ball.getX() <= 0 || ball.getX() + 2*BALL_RADIUS >= getWidth();
}

private void placePaddle() {
	double x = (getWidth()-PADDLE_WIDTH) * 0.5;
	double y = getHeight() -PADDLE_Y_OFFSET;
	add(paddle,x,y);
}

private GRect makePaddle() {
	paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
	paddle.setFilled(true);
	paddle.setColor(Color.GRAY);
	return paddle;
}

public void mouseMoved(MouseEvent e){
	double x = e.getX()- PADDLE_WIDTH * 0.5;
	double y = getHeight()-PADDLE_Y_OFFSET;
	double xx =e.getX() + PADDLE_WIDTH*0.5;
	
	if (x > 0 && xx < getWidth()){
		paddle.setLocation(x,y);
	} else 	{
		if (x<0){
			paddle.setLocation(0,y);
		} else if (xx > getWidth()){
			paddle.setLocation(getWidth()-PADDLE_WIDTH,y);
		}
	}
}

private void placeBricks() {
	/*
	 * Draw a matrix (NBRICKS_PER_ROW * NBRICK_ROWS) of bricks with different colors. 
	 * Each color goes by two rows. Therefore:
	 * One for loop should repeat (NBRICK_ROWS / 2) times.
	 * One for loop should make the program draw two rows of the same color of bricks.
	 */
	

	for (int k = 1; k <= NBRICK_ROWS; k ++){
		for (int i = 0; i < NBRICKS_PER_ROW; i++){
			
			brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			
			// Check which line it is and set accordingly the color.
			Color c = null;
			c = rainbowColor(k,c);
			brick.setColor(c);
			
			double leftX = (getWidth() - BRICK_WIDTH * NBRICKS_PER_ROW - BRICK_SEP * (NBRICKS_PER_ROW-1))*0.5;
			double intervalX = BRICK_WIDTH+BRICK_SEP;	
			double intervalY = BRICK_HEIGHT+BRICK_SEP;
			
			// lay the bricks.
			add (brick, leftX + i * intervalX, BRICK_Y_OFFSET + (k-1) * intervalY);
		}
		
	}
	

	
}

private Color rainbowColor(int k, Color c) {
	if (k % 14 == 1 || k % 14 ==2){
		return Color.RED;
	} else	if (k % 14 == 3 || k % 14 ==4){
		return Color.ORANGE;
	} else 	if (k % 14 == 5 || k % 14 == 6){
		return Color.YELLOW;
	} else	if (k % 14 == 7 || k % 14 == 8){
		return Color.GREEN;
	} else 	if (k % 14 == 9 || k % 14 == 10){
		return Color.CYAN;
	} else	if (k % 14 == 11 || k % 14 == 12){
		return Color.BLUE;
	} else	if (k % 14 == 13 || k % 14 == 0){
		return Color.BLACK;
	} else {
		return null;
	}
	
}



}
