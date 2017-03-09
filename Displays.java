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
		case "Points" : lbl = new GLabel(Integer.toString(pts)); lbl.setFont("ChalkBoard - 32");

		}
		
	}
	
	public GLabel getLbl (){
		return lbl;
	}

	public GImage getImg(){
		return img;
	}
}
