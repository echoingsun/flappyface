import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.*;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants {

	private GImage img;
	RandomGenerator rg = new RandomGenerator();

	public void init() {
		addBackground();
	}

	private void addBackground() {
		img = new GImage("bckgrnd.png");
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}

	/*
	 * Method addBird puts the flying object onto the screen at a random
	 * position at the left, on the upper part of the screen.
	 */
	public void addBird(Bird bird) {

		double x = this.getWidth() * 0.5 - bird.getImg().getWidth() * 0.5;
		double y = this.getHeight() * 0.5 - bird.getImg().getHeight() * 0.5;
		this.add(bird.getImg(), x, y);
	}

	public Pairs addBlockPairs(double x) {
		Pairs pairs = new Pairs();
		this.add(pairs, x, 0);
		return pairs;
	}

	public Displays addDisplay(String str, int pts) {

		switch (str) {
		case "Title": {
			Displays title = new Displays("Title", 0);
			double x = this.getWidth() * 0.5 - title.getImg().getWidth() * 0.5;
			this.add(title.getImg(), x, TITLE_HEIGHT);
			return title;
		}
		case "ClickToStart": {
			Displays clickToStart = new Displays ("ClickToStart", 0); 
			double x = this.getWidth() * 0.5 - clickToStart.getImg().getWidth() * 0.5;
			this.add (clickToStart.getImg(), x, CLICK_START_HEIGHT); 
			return clickToStart;
		}
		case "GameOver": {
			Displays gameOver = new Displays("GameOver", 0); 
			double x = this.getWidth() * 0.5 - gameOver.getImg().getWidth() * 0.5;
			this.add (gameOver.getImg(), x, GAMEOVER_HEIGHT); 
			return gameOver;
		}
		case "Score": {
			Displays youVeScored = new Displays("Score",0); 
			double x = this.getWidth() * 0.5 -youVeScored.getImg().getWidth() * 0.5; 
			this.add (youVeScored.getImg(), x, SCORELABEL_HEIGHT); 
			return youVeScored; 
		}
		case "Points": {
			Displays points = new Displays("Points",pts); 
			double x = this.getWidth() * 0.5 - points.getLbl().getWidth() * 0.5; 
			this.add (points.getLbl(), x, PTS_HEIGHT); 
			return points;
		}
		case "PointsOnScreen": {
			Displays pointsOnScreen = new Displays("PointsOnScreen", pts); 
			double x = this.getWidth() * 0.5 - 150; 
			double y = this.getHeight() * 0.5 + pointsOnScreen.getLbl().getAscent() * 0.5; 
			this.add (pointsOnScreen.getLbl(), x, y); 
			return pointsOnScreen;
		}
		case "InstructionLabel": {
			Displays instructionLabel = new Displays("InstructionLabel", 0);
			double x = this.getWidth() * 0.5 - instructionLabel.getImg().getWidth() * 0.5;
			this.add (instructionLabel.getImg(), x, INFO_LABEL_HEIGHT); 
			return instructionLabel; 
		}
		case "Instructions": {
			Displays instruction = new Displays("Instructions", 0); 
			this.add (instruction.getImg(), INFO_X, INFO_Y); 
			return instruction; 
		}
		}
		return null;
	}

	public GImage getImg() {
		return img;
	}

}
