import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.*;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants{

	public void init (){
		addBackground();
	}

	private void addBackground() {		
		GImage img = new GImage ("background.jpg");
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}

	/*
	 * Method addBird puts the flying object onto the screen.
	 */
	public void addBird() {
		RandomGenerator rg = new RandomGenerator();
		
		Bird bird = new Bird();
		double x = 0;
		double y = rg.nextDouble (0, this.getHeight()- bird.getImg().getHeight());
		
		this.add (bird.getImg(), 0,0);
	}
	
	/*
	 * Method addBlocks add a pair of blocks to the screen.
	 */
	public void addBlocks() {
		
	}
}
