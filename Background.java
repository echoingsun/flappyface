/*
 * File: Background.java
 * ------------------------------
 * This class defines the background, and its related methods,
 * particularly, adding other classes of objects to the canvas.
 */

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants {

	RandomGenerator rg = new RandomGenerator();

	// The background will have an image to it.
	private GImage img;

	public void init() {
		addBackground();
	}

	// Set the background.
	private void addBackground() {
		img = new GImage(CVS_FILENAME);
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}

	/*
	 * Method addFace puts the flying object onto the center of the screen.
	 */
	public void addFace(Face face) {
		double x = this.getWidth() * 0.5 - face.getImg().getWidth() * 0.5;
		double y = this.getHeight() * 0.5 - face.getImg().getHeight() * 0.5;
		this.add(face.getImg(), x, y);
	}

	/*
	 * Method addBlockPairs puts a GCompound pair onto the screen. The position
	 * will vary, therefore a parameter is passed into this method.
	 */
	public Pairs addBlockPairs(double x) {
		Pairs pairs = new Pairs();
		this.add(pairs, x, 0);
		return pairs;
	}

	/*
	 * Method addDisplay gets a certain type of Displays object and add them
	 * onto the screen.
	 */
	public Displays addDisplay(String str, int pts) {

		switch (str) {
		case "Title": {
			Displays title = new Displays("Title", 0);
			double x = this.getWidth() * 0.5 - title.getImg().getWidth() * 0.5;
			this.add(title.getImg(), x, TITLE_HEIGHT);
			return title;
		}
		case "ClickToStart": {
			Displays clickToStart = new Displays("ClickToStart", 0);
			double x = this.getWidth() * 0.5 - clickToStart.getImg().getWidth() * 0.5;
			this.add(clickToStart.getImg(), x, CLICK_START_HEIGHT);
			return clickToStart;
		}
		case "GameOver": {
			Displays gameOver = new Displays("GameOver", 0);
			double x = this.getWidth() * 0.5 - gameOver.getImg().getWidth() * 0.5;
			this.add(gameOver.getImg(), x, GAMEOVER_HEIGHT);
			return gameOver;
		}
		case "Score": {
			Displays youVeScored = new Displays("Score", 0);
			double x = this.getWidth() * 0.5 - youVeScored.getImg().getWidth() * 0.5;
			this.add(youVeScored.getImg(), x, SCORELABEL_HEIGHT);
			return youVeScored;
		}
		case "Points": {
			Displays points = new Displays("Points", pts);
			double x = this.getWidth() * 0.5 - points.getLbl().getWidth() * 0.5;
			this.add(points.getLbl(), x, PTS_HEIGHT);
			return points;
		}
		case "PointsOnScreen": {
			Displays pointsOnScreen = new Displays("PointsOnScreen", pts);
			double y = this.getHeight() * 0.5 + pointsOnScreen.getLbl().getAscent() * 0.5;
			
			// The position is an initial one. It will be updated.
			this.add(pointsOnScreen.getLbl(), POINTS_SCREEN_X, y);
			return pointsOnScreen;
		}
		case "InstructionLabel": {
			Displays instructionLabel = new Displays("InstructionLabel", 0);
			double x = this.getWidth() * 0.5 - instructionLabel.getImg().getWidth() * 0.5;
			this.add(instructionLabel.getImg(), x, INFO_LABEL_HEIGHT);
			return instructionLabel;
		}
		case "Instructions": {
			Displays instruction = new Displays("Instructions", 0);
			this.add(instruction.getImg(), INFO_X, INFO_Y);
			return instruction;
		}
		case "ClickToQuit": {
			Displays clickToQuit = new Displays("ClickToQuit", 0);
			double x = this.getWidth() * 0.5 - clickToQuit.getImg().getWidth() * 0.5;
			this.add(clickToQuit.getImg(),x,CLICK_QUIT_HEIGHT);
			return clickToQuit;
		}
		}
		return null;
	}

	// Image getter.
	public GImage getImg() {
		return img;
	}
}
