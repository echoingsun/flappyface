
/*
 * File: Contest_Run.java
 * ------------------------------
 * This is the main class file for the game.
 * This is a parody ;) to the runaway game "Flappy bird".
 * Player uses the mouse to control the bird from falling or hitting the blocks (chimneys).
 * 
 */

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class Contest_Run extends Program implements Constants {

	private RandomGenerator rg = new RandomGenerator();
	private Background sky = new Background();
	private Bird bird = new Bird();
	private Pairs blockPairs1 = new Pairs();
	private Pairs blockPairs2 = new Pairs();
	private Displays ptsOnScreen;
	
	private MouseAdapter myListener;
	AudioClip theme = MediaTools.loadAudioClip("theme.au");
	AudioClip titleMusic = MediaTools.loadAudioClip("title.au");
	AudioClip clickSound = MediaTools.loadAudioClip("clickSound.au");
	AudioClip lose = MediaTools.loadAudioClip("lose.au");
	
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
		theme.loop();
		enableBirdMove();
		
		
		
		loadBlocks();
		ptsOnScreen = sky.addDisplay("PointsOnScreen", pts);
		
		while (bird.notHit(sky)) {
			//updatePoints(ptsOnScreen, pts);
			bird.freeMove();
			pause(DELAY);
			
			if (blockPairs1.getX() + blockPairs1.getWidth() <= -BLOCK_INTERVAL) {
				replaceBlockPairs();				
			} else {
				blockPairs1.moveLeft();
				pause(DELAY);
				count ++;
				pts = count / 3;
				updatePoints(ptsOnScreen,pts);
				if (pts % 100 == 0){
					bird.flip();
				}
			}

			blockPairs2.moveLeft();
			pause(DELAY);

		}
		
		gameOver();


	}



	private void gameOver() {
		lose.play();
		theme.stop();
		
		sky.removeMouseListener(myListener);
		
		bird.changeImg();
		pause(GAMEOVER_DELAY);
		
		sky.remove(blockPairs1);
		sky.remove(blockPairs2);
		sky.remove(ptsOnScreen.getLbl());
		
		centerBird();
		titleMusic.loop();
		showGameOver();
		
		
		

		
	}
	
	private void updatePoints(Displays points, int pts){
		points.getLbl().setLabel(Integer.toString(pts));
	}

	private void showGameOver() {
		
		Displays gameOver = sky.addDisplay("GameOver" , 0);
		sky.addDisplay("Score", 0);
		sky.addDisplay("Points", pts);
		while(true){
			bird.faceLoop(rg.nextInt(0,bird.fileNames.length-1));
			floatDisplay(gameOver,GAMEOVER_UPPER_BORDER, GAMEOVER_LOWER_BORDER,TITLE_MOVE_AMT,TITLE_FLOAT);
		}
		
	}

	private void centerBird() {

		double cy = sky.getHeight() * 0.5;
		while (bird.getImg().getY() + bird.getImg().getHeight() * 0.5 < cy){
			bird.getImg().move(0, MOVE_SPEED);
			pause(DELAY);
		}
		while (bird.getImg().getY() + bird.getImg().getHeight() * 0.5 > cy){
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

		Displays title = sky.addDisplay("Title",0);
		Displays clickToStart = sky.addDisplay("ClickToStart",0);
		
		while (mouseClicked == false){
			floatDisplay(title, TITLE_UPPER_BORDER, TITLE_LOWER_BORDER,TITLE_MOVE_AMT,TITLE_FLOAT);
		}
		
		sky.remove(clickToStart.getImg());
		while (title.getImg().getY() + title.getImg().getHeight() >=0){
			title.getImg().move(0, - VY_DELTA);
			pause(TITLE_FLOAT);
		}
		
		sky.remove(title.getImg());
		titleMusic.stop();
		
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

	/*
	 * http://stackoverflow.com/questions/16360004/removing-mouselistener-when-something-happens
	 */
	private void enableBirdMove() {
		myListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				bird.moveUp();
				clickSound.play();
			}
		};
		sky.addMouseListener(myListener);
	}
	
	private void loadBird() {
		sky.addBird(bird);
		
	}

	private void loadCanvas() {
		add(sky);
		sky.init();
		titleMusic.loop();
	}

}
