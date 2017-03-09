import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Displays implements Constants {

	private GLabel lbl;
	private GImage img;
	
	public Displays (String str, String content, int pts){
		
		switch (str){
		case "Title" : img = new GImage ("karel.png"); break;
		case "Instructions" : lbl = new GLabel (content); break;
		case "Points" : lbl = new GLabel( Integer.toString(pts));

		}
		
	}
	
	public GLabel getLbl (){
		return lbl;
	}

	public GImage getImg(){
		return img;
	}
}
