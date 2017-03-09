
/*
 * File: Contest_Run.java
 * ------------------------------
 * This is the main class file for the game.
 * This is a parody ;) to the runaway game "Flappy bird".
 * Player uses the mouse to control the bird from falling or hitting the blocks (chimneys).
 * 
 */

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import acm.program.*;

public class Contest_Run extends Program implements Constants {

	private Background sky = new Background();
	private Bird bird = new Bird();
	private Pairs blockPairs1 = new Pairs();
	private Pairs blockPairs2 = new Pairs();
	
	private boolean mouseClicked = false;
	
	private int count = 0;
	private int pts = 0;

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		
		
		loadCanvas();
		enableMouse();
		
		loadTitle();
		
		loadBird();
		enableBirdMove();
		
		loadBlocks();
		
		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			if (blockPairs1.getX() + blockPairs1.getWidth() <= -BLOCK_INTERVAL) {
				replaceBlockPairs();				
			} else {
				blockPairs1.moveLeft();
				pause(DELAY);
				count ++;
				if (count == 300){
					count = 0;
					pts = pts + PTS_DELTA;
					//bird.flip();

				}
			}

			blockPairs2.moveLeft();
			pause(DELAY);

		}
		
		gameOver();


	}

	private void gameOver() {
		bird.changeImg();
		pause(GAMEOVER_DELAY);
		sky.remove(blockPairs1);
		sky.remove(blockPairs2);
		
		centerBird();
	
		Displays gameOver = sky.addDisplay("GameOver");
		println(count);
		println(pts);
		
	}

	private void centerBird() {

		double cy = sky.getHeight() * 0.5;
		while (bird.getImg().getY() + bird.getImg().getHeight() * 0.5 <= cy){
			bird.getImg().move(0, MOVE_SPEED);
			pause(DELAY);
		}
		while (bird.getImg().getY() >= cy){
			bird.getImg().move(0, -MOVE_SPEED);
			pause(DELAY);
		} 
	}

	private void floatDisplay (Displays ds, int upperBorder, int lowerBorder, double moveAmt, int delayTime){
		while (ds.getImg().getY() >= upperBorder){
			ds.getImg().move(0, -moveAmt);
			pause(delayTime);
		}
		while (ds.getImg().getY() <= lowerBorder){
			ds.getImg().move(0, moveAmt);
			pause(delayTime);
		}
	}
	
	private void loadTitle() {

		Displays title = sky.addDisplay("Title");
		Displays clickToStart = sky.addDisplay("ClickToStart");
		
		while (mouseClicked == false){
			floatDisplay(title, TITLE_UPPER_BORDER, TITLE_LOWER_BORDER,TITLE_MOVE_AMT,TITLE_FLOAT);
		}
		
		sky.remove(clickToStart.getImg());
		while (title.getImg().getY() + title.getImg().getHeight() >=0){
			title.getImg().move(0, - VY_DELTA);
			pause(TITLE_FLOAT);
		}
		
		sky.remove(title.getImg());
		
	}

	private void replaceBlockPairs() {
		sky.remove(blockPairs1);
		blockPairs1 = blockPairs2;
		blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + +BLOCK_INTERVAL);		
	}

	private void loadBlocks() {
		blockPairs1 = sky.addBlockPairs(BLOCK_START_POINT);
		blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + BLOCK_INTERVAL);
	}

	private void enableMouse() {
		sky.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClicked = true;
				
			}
		});
	}

	private void enableBirdMove() {
		sky.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				bird.moveUp();
			}
		});
	}
	
	private void loadBird() {
		sky.addBird(bird);
		
	}

	private void loadCanvas() {
		add(sky);
		sky.init();
	}

}
