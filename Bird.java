import acm.graphics.*;
import acm.util.RandomGenerator;

public class Bird implements Constants {

	private GImage img;

	public Bird() {
		img = new GImage("karel.png");
		img.setSize(BIRD_WIDTH, BIRD_HEIGHT);
	}

	public GImage getImg() {
		return img;
	}

	public void moveUp() {
		this.getImg().move(0, -VY_DELTA);
		
	}
	
	public void freeMove(){
		RandomGenerator rg = new RandomGenerator ();
		double vx = rg.nextDouble(MIN_SPEED, MAX_SPEED);
		double vy = rg.nextDouble(MIN_SPEED, MAX_SPEED);
		this.getImg().move(vx, vy);
	}
	
	
	

	
	
}
