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
		double vx = rg.nextDouble(MIN_SPEED, MAX_SPEED);
		double vy = rg.nextDouble(MIN_SPEED, MAX_SPEED);
		this.img.move(vx, vy);
	}

	public boolean notHit(Background sky){
		GObject obj = sky.getElementAt(img.getX()-1, img.getY() -1);
		boolean upperLeft = sky.getElementAt(img.getX()-1, img.getY() -1) == null;
		boolean bottomLeft = sky.getElementAt(img.getX() -1, img.getY() + img.getHeight() +1) == null;
		boolean upperRight = sky.getElementAt(img.getX() + img.getWidth() + 1, img.getY() -1) == null;
		boolean bottomRight = sky.getElementAt(img.getX() + img.getWidth() + 1, img.getY() + img.getHeight() +1) == null;
		boolean top = img.getY() -1 <= 0;
		boolean bottom = img.getY() + img.getHeight() + 1 >= sky.getHeight();
		
		if (upperLeft && bottomLeft && upperRight && bottomRight && !top && !bottom){
			return true;
		} 
		return false;
	}
	
	
	

	
	
}
