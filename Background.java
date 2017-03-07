import acm.graphics.*;

public class Background extends GCanvas implements Constants{

	GImage img;

	public Background (String fileName){
		this.img = new GImage (fileName);
		this.img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		/*this.add(img,0,0);*/
	}
}
