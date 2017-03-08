import acm.graphics.GCanvas;
import acm.graphics.GCompound;
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
		img.setSize(BCKGRND_WIDTH,BCKGRND_HEIGHT);
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
	

	public void addBlockPair(){
		GCompound pair = new GCompound();
		
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(APPLICATION_HEIGHT * 0.6, APPLICATION_HEIGHT * 0.8);
		this.add(bottom.getImg(), BLOCK_START_POINT, bottomY);
		
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (APPLICATION_HEIGHT * 0.3, APPLICATION_HEIGHT * 0.5) - upper.getImg().getHeight();
		this.add(upper.getImg(), BLOCK_START_POINT, upperY);
		
		pair.add(bottom.getImg());
		pair.add(upper.getImg());

		this.add(pair);
		
		double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);
		
		while (true){
			pair.move(vx, 0);
		}
			


	}
	
	public GImage getImg() {
		return img;
	}
	

}
