import acm.graphics.GLabel;

public class Displays implements Constants {

	private GLabel lbl;
	public Displays (String str, String content, int pts){
		
		lbl = new GLabel("");
		
		if (str.equals("Instructions")){
			lbl = new GLabel (content);
			
		}
		
		if (str.equals("Points")){
			lbl = new GLabel( Integer.toString(pts));
		}
		
	
	}
	
	public GLabel getLbl (){
		return lbl;
	}
}
