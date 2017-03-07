import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.*;

public class Background extends GCanvas implements Constants{

/*	public void init (){
		addBackground();
	}

	private void addBackground() {		
		GImage img = new GImage ("background.jpg");
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}*/

	
	public Background (String filename){
		GImage img = new GImage (filename);
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}
}
