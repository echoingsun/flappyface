import acm.graphics.*;

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
		this.move(0, -VY_DELTA);
		
	}
	
	
	

	
	
}
