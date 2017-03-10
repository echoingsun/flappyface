
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
 * *****************************************************
 * THE FACE CAN APPEAR TO HAVE HIT THE BLOCKS AT A DISTANCE FROM THE BLOCKS.
 * THIS IS BECAUSE THE HIT CHECK IS PERFORMED BASED ON THE OUTER BOUNDARY
 * OF THE IMAGE WHILE SOME PARTS OF THE IMAGE ARE TRANSPARENT SO THAT
 * THE BOUNDARY CANNOT BE SEEN.
 */

import java.applet.AudioClip;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class Contest_Run extends Program implements Constants {

	private RandomGenerator rg = new RandomGenerator();
	private MouseAdapter myListener;
	
	private AudioClip theme = MediaTools.loadAudioClip(THEME_FILENAME);
	private AudioClip titleMusic = MediaTools.loadAudioClip(TITLEMSC_FILENAME);
	private AudioClip clickSound = MediaTools.loadAudioClip(CLICKSND_FILENAME);
	private AudioClip lose = MediaTools.loadAudioClip(LOSEMSC_FILENAME);

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

				// For every 100 points user scores,
				// flip the face once.
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
	 * Method calculateScore generates the score player sees on screen. For each
	 * move, let count add by 1. Since the number is going to be large, let the
	 * actual score be a third of the count number. For each move, also updated
	 * the label that shows the score.
	 */
	private void calculateScore() {
		count++;
		pts = count / 3;
		updatePoints(ptsOnScreen, pts, face.getImg().getX() - ptsOnScreen.getLbl().getWidth(),
				face.getImg().getY() + face.getImg().getHeight() * 0.5);

	}

	/*
	 * Method gameOver list a number of actions that happens after the player
	 * loses, including: (1) switch the music (2) disable the mouselistener that
	 * helps move the face (3) re-arrange the graphics of the close screen
	 */
	private void gameOver() {
		lose.play();
		theme.stop();

		// Once the player loses, he/she cannot click the mouse
		// to lift the face.
		sky.removeMouseListener(myListener);

		// Change the face image to an unhappy one.
		face.changeImg();
		pause(GAMEOVER_DELAY);

		// Remove the blockPairs and the simultaneous display of the score.
		sky.remove(blockPairs1);
		sky.remove(blockPairs2);
		sky.remove(ptsOnScreen.getLbl());

		// Rearrange the graphics.
		centerFace();
		titleMusic.loop();
		showGameOver();
	}

	/*
	 * Method updatePoints takes in the object points, the value of points, and
	 * the XY position of the score label. Based on these parameters, it resets
	 * the value and position of the object "points".
	 */
	private void updatePoints(Displays points, int pts, double x, double y) {
		points.getLbl().setLabel(Integer.toString(pts));
		points.getLbl().setLocation(x, y);
	}

	/*
	 * Method showGameOver does the following: (1) display a game over message
	 * (2) concludes the game by showing how much the player has scored (3)
	 * graphics improvement: let the title float (4) click on "clickToQuit"
	 * exits the program.
	 */
	private void showGameOver() {
		
		Displays gameOver = sky.addDisplay("GameOver", 0);
		sky.addDisplay("Score", 0);
		sky.addDisplay("Points", pts);
		Displays clickToQuit = sky.addDisplay("ClickToQuit", 0);
		
		// Add mouseClick event to "ClickToQuit".
		enableQuit(clickToQuit);
		
		// Float the "gameover" title while looping the face images
		// among the 4 images.
		while (true) {
			face.faceLoop(rg.nextInt(0, face.fileNames.length - 1));
			floatDisplay(gameOver, GAMEOVER_UPPER_BORDER, GAMEOVER_LOWER_BORDER, TITLE_MOVE_AMT, TITLE_FLOAT_DELAY);
		}
	}

	/*
	 * Method enableQuit takes in the clickToQuit image and 
	 * add a mouse clicked event to it: if clicked, exit the program.
	 */
	private void enableQuit(Displays clickToQuit) {
		clickToQuit.getImg().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				exit();
			}
		});
	}

	/*
	 * Method centerFace moves the face to the center of the screen wherever the
	 * face is, after the player loses the game.
	 */
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

	/*
	 * Method floatDisplay takes in a display object and some parameters that
	 * defines the floating movement of the object. The object would look like
	 * floating in the air with two while loops and these parameters.
	 */
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

	/*
	 * Method loadTitle basically shows the welcome screen. It does the
	 * following: (1) places some display images (2) add mouse events to some of
	 * the images. (3) float the title before player clicks "clickstoStart" to start
	 * the game. (4) after player clicks, remove the objects on this welcome
	 * screen.
	 */
	private void loadTitle() {

		Displays title = sky.addDisplay("Title", 0);
		Displays clickToStart = sky.addDisplay("ClickToStart", 0);
		Displays instructionLabel = sky.addDisplay("InstructionLabel", 0);

		// This will display instruction when player hovers the mouse over
		// the instruction label.
		addMouseHover(instructionLabel);

		// This will let the user to start the game by clicking on the
		// "clickToStart" image.
		enableMouse(clickToStart);
		
		// When player hasn't started the game by clicking the canvas, do the
		// following: float title.
		while (mouseClicked == false) {
			floatDisplay(title, TITLE_UPPER_BORDER, TITLE_LOWER_BORDER, TITLE_MOVE_AMT, TITLE_FLOAT_DELAY);
		}

		// Once mouse is clicked, remove (or, move) the components out of the
		// canvas.
		sky.remove(clickToStart.getImg());
		sky.remove(instructionLabel.getImg());
		while (title.getImg().getY() + title.getImg().getHeight() >= 0) {
			title.getImg().move(0, -VY_DELTA);
			pause(TITLE_FLOAT_DELAY);
		}

		sky.remove(title.getImg());
		titleMusic.stop();
	}

	/*
	 * Method addMouseHover enables the display of a "pop-up" when user hovers
	 * the mouse over the label showing "more info". This is achieved through
	 * adding mouseEntered and mouseExited events. The usage of the mouseAdapter
	 * referenced:
	 * http://stackoverflow.com/questions/16360004/removing-mouselistener-when-
	 * something-happens
	 */
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

	/*
	 * Method replaceBlockPairs (1) removes a blockPair that has gone out of the
	 * screen, (2) makes the second blockPair that has been following the first,
	 * the first one. (3) generate a new blockPair that comes as second after
	 * the newly-replaced first.
	 */
	private void replaceBlockPairs() {
		sky.remove(blockPairs1);
		blockPairs1 = blockPairs2;
		blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + +BLOCK_INTERVAL);
	}

	/*
	 * Method loadBlocks puts two blocks on the canvas. They will later be
	 * replaced by calling replaceBlockPairs.
	 */
	private void loadBlocks() {
		blockPairs1 = sky.addBlockPairs(BLOCK_START_POINT);
		blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + BLOCK_INTERVAL);
	}

	/*
	 * Method enableMouse only enables player to "click to start". boolean
	 * mouseClicked will change to true after the click on "clickToStart",
	 * which means player officially starts the game.
	 */
	private void enableMouse(Displays clickToStart) {
		clickToStart.getImg().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClicked = true;
			}
		});
	}

	/*
	 * Method enableFaceMove renews the value of mouseAdapter myListener. It
	 * carries a mouseClicked event. For each click, move the face upwards as
	 * dictated by the face.moveUp method. Also, plays a sound effect with each
	 * click. The use of mouseAdapter referenced:
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

	/*
	 * Method loadFace adds the face to the center of the canvas.
	 */
	private void loadFace() {
		sky.addFace(face);
	}

	/*
	 * Method loadCanvas adds the canvas sky, and start to loop the music.
	 */
	private void loadCanvas() {
		add(sky);
		sky.init();
		titleMusic.loop();
	}

}
