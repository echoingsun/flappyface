
/*
 * File: Contest_Run.java
 * ------------------------------
 * This is the main class file for the game.
 * This is a parody ;) to the runaway game "Flappy bird".
 * Player uses the mouse to control the bird from falling or hitting the blocks.
 * -------------------------------
 * Highlights and functions of this program:
 * (1) Mouse events MouseClicked, MouseEntered and MouseExited
 *     that control the display of objects;
 * (2) Improved graphic designs;
 * (3) Audio clips enabled.
 * (4) Interaction between classes;
 * (5) Keep the player score up-to-date on the screen.
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
	private MouseAdapter myListener;

	/*
	 * Instance variables for the program. Since general classes do not seem to
	 * support functions such as "pause", all methods and objects that involve
	 * movement need to be defined as instance variables here.
	 */
	private Background sky = new Background();
	private Face face = new Face();
	private Pairs blockPairs1 = new Pairs();
	private Pairs blockPairs2 = new Pairs();
	private Displays ptsOnScreen;

	private AudioClip theme = MediaTools.loadAudioClip("theme.au");
	private AudioClip titleMusic = MediaTools.loadAudioClip("title.au");
	private AudioClip clickSound = MediaTools.loadAudioClip("clickSound.au");
	private AudioClip lose = MediaTools.loadAudioClip("lose.au");

	// Used to record whether mouse has been clicked for a certain event to
	// happen.
	private boolean mouseClicked = false;

	// Count and pts are used to calculate player's points.
	private int count = 0;
	private int pts = 0;

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {

		loadCanvas();
		enableMouse();

		loadTitle();

		loadFace();
		theme.loop();
		enableFaceMove();

		loadBlocks();
		loadPoints();

		gameRun();
		gameOver();

	}

	/*
	 * Method loadPoints add the label that displays the score to the canvas
	 * "sky".
	 */
	private void loadPoints() {
		ptsOnScreen = sky.addDisplay("PointsOnScreen", pts);
	}

	/*
	 * Method gameRun defines the logic of one game (when player hasn't lost
	 * yet).
	 */
	private void gameRun() {
		
		// face.notHit(sky) is the condition that ensures the game runs.
		while (face.notHit(sky)) {
			
			// Move the face.
			face.freeMove();
			pause(DELAY);

			// This program makes two block pairs regenerating and replacing
			// each other to enable the visual effect of "infinite blocks".
			// If blockPair1 completely passes the screen, remove it, 
			// and remake it to the rightmost of the screen.
			// Otherwise keep it moving.
			if (blockPairs1.getX() + blockPairs1.getWidth() <= -BLOCK_INTERVAL) {
				replaceBlockPairs();
			} else {
				blockPairs1.moveLeft();
				pause(DELAY);
				
				calculateScore();
				if (pts % 100 == 0) {
					face.flip();
				}
			}

			// The second blockPair also needs to move along.
			blockPairs2.moveLeft();
			pause(DELAY);

		}

	}

	/*
	 * Method calculateScore generates the score player sees on screen.
	 * For each move, let count add by 1.
	 * Since the number is going to be large, let the actual score
	 * be a third of the count number.
	 * For each move, also updated the label that shows the score.
	 */
	private void calculateScore() {
		count++;
		pts = count / 3;
		updatePoints(ptsOnScreen, pts, face.getImg().getX() - ptsOnScreen.getLbl().getWidth(),
				face.getImg().getY() + face.getImg().getHeight() * 0.5);
		
	}

	private void gameOver() {
		lose.play();
		theme.stop();

		sky.removeMouseListener(myListener);

		face.changeImg();
		pause(GAMEOVER_DELAY);

		sky.remove(blockPairs1);
		sky.remove(blockPairs2);
		sky.remove(ptsOnScreen.getLbl());

		centerFace();
		titleMusic.loop();
		showGameOver();

	}

	private void updatePoints(Displays points, int pts, double x, double y) {
		points.getLbl().setLabel(Integer.toString(pts));
		points.getLbl().setLocation(x, y);
	}

	private void showGameOver() {

		Displays gameOver = sky.addDisplay("GameOver", 0);
		sky.addDisplay("Score", 0);
		sky.addDisplay("Points", pts);
		while (true) {
			face.faceLoop(rg.nextInt(0, face.fileNames.length - 1));
			floatDisplay(gameOver, GAMEOVER_UPPER_BORDER, GAMEOVER_LOWER_BORDER, TITLE_MOVE_AMT, TITLE_FLOAT_DELAY);
		}

	}

	private void centerFace() {

		double cy = sky.getHeight() * 0.5;
		while (face.getImg().getY() + face.getImg().getHeight() * 0.5 < cy) {
			face.getImg().move(0, MOVE_SPEED);
			pause(DELAY);
		}
		while (face.getImg().getY() + face.getImg().getHeight() * 0.5 > cy) {
			face.getImg().move(0, -MOVE_SPEED);
			pause(DELAY);
		}
	}

	private void floatDisplay(Displays ds, int upperBorder, int lowerBorder, double moveAmt, int delayTime) {
		while (ds.getImg().getY() >= upperBorder) {
			ds.getImg().move(0, -moveAmt);
			pause(delayTime);
		}
		while (ds.getImg().getY() <= lowerBorder) {
			ds.getImg().move(0, moveAmt);
			pause(delayTime);
		}
	}

	private void loadTitle() {

		Displays title = sky.addDisplay("Title", 0);
		Displays clickToStart = sky.addDisplay("ClickToStart", 0);
		Displays instructionLabel = sky.addDisplay("InstructionLabel", 0);
		addMouseHover(instructionLabel);

		while (mouseClicked == false) {
			floatDisplay(title, TITLE_UPPER_BORDER, TITLE_LOWER_BORDER, TITLE_MOVE_AMT, TITLE_FLOAT_DELAY);
		}

		sky.remove(clickToStart.getImg());
		sky.remove(instructionLabel.getImg());
		while (title.getImg().getY() + title.getImg().getHeight() >= 0) {
			title.getImg().move(0, -VY_DELTA);
			pause(TITLE_FLOAT_DELAY);
		}

		sky.remove(title.getImg());
		titleMusic.stop();

	}

	private void addMouseHover(Displays instructionLabel) {

		instructionLabel.getImg().addMouseListener(new MouseAdapter() {

			Displays instructions = new Displays("Instrutions", 0);

			public void mouseEntered(MouseEvent e) {
				instructions = sky.addDisplay("Instructions", 0);

			}

			public void mouseExited(MouseEvent e) {
				sky.remove(instructions.getImg());
			}
		});

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
	 * http://stackoverflow.com/questions/16360004/removing-mouselistener-when-
	 * something-happens
	 */
	private void enableFaceMove() {
		myListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				face.moveUp();
				clickSound.play();
			}
		};
		sky.addMouseListener(myListener);
	}

	private void loadFace() {
		sky.addFace(face);
	}

	private void loadCanvas() {
		add(sky);
		sky.init();
		titleMusic.loop();
	}

}
