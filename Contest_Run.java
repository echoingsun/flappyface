
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
	
	private double count = 0;

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
				//count += 0.00000000000000000000000000000001;
			}

			blockPairs2.moveLeft();
			pause(DELAY);

		}
		//println(count);

	}

	private void loadTitle() {
		Displays title = new Displays ("Title", "", 0);
		sky.addDisplay("Title", title);
		
		while (mouseClicked == false){
			while (title.getImg().getY() >= TITLE_UPPER_BORDER){
				title.getImg().move(0, -VY_DELTA);
				pause(TITLE_SHAKE);
			}
			while (title.getImg().getY() <= TITLE_LOWER_BORDER){
				title.getImg().move(0, VY_DELTA);
				pause(TITLE_SHAKE);
			}
		}
		
		while (title.getImg().getY() + title.getImg().getHeight() >=0){
			title.getImg().move(0, - VY_DELTA);
			pause(TITLE_SHAKE);
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
