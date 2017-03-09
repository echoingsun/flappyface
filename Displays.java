import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Displays implements Constants {

	private GLabel lbl;
	private GImage img;
	
	public Displays (String str, int pts){
		
		switch (str){
		case "Title" : img = new GImage ("FlappyFaceTitle.png"); break;
		case "ClickToStart" : img = new GImage ("clicktostart.png"); break;
		case "GameOver" : img = new GImage ("gameover.png"); break;
		case "Score" : img = new GImage("score.png"); break;
		case "Points" : lbl = new GLabel(Integer.toString(pts)); lbl.setFont("Chalkboard-30");
		case "PointsOnScreen" : lbl = new GLabel(Integer.toString(pts)); lbl.setFont("Chalkboard-24");
		case "Instructions": img = new GImage ("Instructions.png"); break;
		case "InstructionLabel" : img = new GImage ("InstructionLabel.png");
		}
		
	}
	
	public GLabel getLbl (){
		return lbl;
	}

	public GImage getImg(){
		return img;
	}
}
