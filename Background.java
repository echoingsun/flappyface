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
		Pair pair = new Pair(this);
		/*this.add(pair, 0, 20);*/
		
		
		
/*		double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);
		
		while (true){
			pair.move(vx, 0);
		}*/
			


	}
	
	public GImage getImg() {
		return img;
	}
	

}
