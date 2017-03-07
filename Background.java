import acm.graphics.*;

public class Background extends GCanvas implements Constants{

	public Background(){
		
	}
	
	public void init (){
		addBackground();
	}

	private void addBackground() {
		
		GImage img = new GImage ("background.jpg");
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}

}
