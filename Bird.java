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
		img.move(0, -VY_DELTA);
		
	}
	
	public void freeMove(){
		RandomGenerator rg = new RandomGenerator ();
		double vx = 0;
		double vy = rg.nextDouble(MIN_SPEED, MAX_SPEED);
		this.img.move(vx, vy);
	}


	
	
	

	
	
}
