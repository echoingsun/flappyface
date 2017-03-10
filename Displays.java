
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
			img = new GImage(TITLEIMG_FILENAME);
			break;
		case "ClickToStart":
			img = new GImage(CLICK_START_FILENAME);
			break;
		case "GameOver":
			img = new GImage(GAMEOVER_FILENAME);
			break;
		case "Score":
			img = new GImage(SCOREMSG_FILENAME);
			break;
		case "Points":
			lbl = new GLabel(Integer.toString(pts));
			lbl.setFont("Chalkboard-30");
		case "PointsOnScreen":
			lbl = new GLabel(Integer.toString(pts));
			lbl.setFont("Chalkboard-24");
		case "Instructions":
			img = new GImage(INFO_FILENAME);
			break;
		case "InstructionLabel":
			img = new GImage(INFOLABEL_FILENAME);
			break;
		case "ClickToQuit" :
			img = new GImage(CLICK_QUIT_FILENAME);
			break;
		default:
			img = null;
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
