import acm.graphics.GLabel;

public class Instructions extends GLabel implements Constants {

	GLabel lbl;
	public Instructions (String str, String content, int pts){
		
		if (str.equals("Instructions")){
			lbl = new GLabel (content);
			
		}
	}
	
	public GLabel getLbl (){
		return lbl;
	}
}
