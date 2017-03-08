import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.*;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants{

	private GImage img;
	RandomGenerator rg = new RandomGenerator();
	
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
		
		double x = this.getWidth() * 0.5 - bird.getImg().getWidth() * 0.5;
		double y = this.getHeight() * 0.5 - bird.getImg().getHeight() * 0.5;		
		this.add (bird.getImg(), x, y);
	}
	
	/*
	 * Method addBlocks add a pair of blocks to the screen.
	 */
	public void addBlocks() {
		// add the bottom block.
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(this.getHeight() * 0.6, this.getHeight() * 0.8);
		this.add(bottom.getImg(), BLOCK_START_POINT,bottomY);
		
		// add the upper block
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (this.getHeight() * 0.3, this.getHeight() * 0.5) - upper.getImg().getHeight();
		this.add(upper.getImg(), BLOCK_START_POINT, upperY);
	}
	
	public GImage getImg() {
		return img;
	}
	

}
