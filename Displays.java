
/*
 * File: Displays.java
 * ------------------------
 * The class Displays have different types of objects including
 * images and labels. Each performs a different function in the game.
 * The final adding of these objects to the screen is by calling
 * the addDisplay method in the Background class.
 */

import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Displays implements Constants {

	// Two types of Displays for this program:
	private GLabel lbl;
	private GImage img;

	// Constructor: takes a string (indicating what this object is)
	// and an int which would be useful if the Displays objects is a label.
	public Displays(String str, int pts) {
		switch (str) {
		case "Title":
			img = new GImage("FlappyFaceTitle.png");
			break;
		case "ClickToStart":
			img = new GImage("clicktostart.png");
			break;
		case "GameOver":
			img = new GImage("gameover.png");
			break;
		case "Score":
			img = new GImage("score.png");
			break;
		case "Points":
			lbl = new GLabel(Integer.toString(pts));
			lbl.setFont("Chalkboard-30");
		case "PointsOnScreen":
			lbl = new GLabel(Integer.toString(pts));
			lbl.setFont("Chalkboard-24");
		case "Instructions":
			img = new GImage("Instructions.png");
			break;
		case "InstructionLabel":
			img = new GImage("InstructionLabel.png");
			break;
		}
	}

	// Label getter.
	public GLabel getLbl() {
		return lbl;
	}

	// Image getter.
	public GImage getImg() {
		return img;
	}
}
