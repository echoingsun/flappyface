import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.*;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants{

	private GImage img;
	public void init (){
		addBackground();
	}

	private void addBackground() {		
		img = new GImage ("background.jpg");
		img.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(img);
	}

	/*
	 * Method addBird puts the flying object onto the screen
	 * at a random position at the left, on the upper part of the screen.
	 */
	public void addBird(Bird bird) {
		
		RandomGenerator rg = new RandomGenerator();
		
		double x = 0;
		double y = rg.nextDouble (0, (this.getHeight()- bird.getImg().getHeight()) * 0.5);		
		this.add (bird.getImg(), x, y);
	}
	
	/*
	 * Method addBlocks add a pair of blocks to the screen.
	 */
	public void addBlocks() {
		
	}
	
	public GImage getImg() {
		return img;
	}
	

}
