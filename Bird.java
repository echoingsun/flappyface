import acm.graphics.*;
import acm.util.RandomGenerator;

public class Bird implements Constants {

	private GImage img;
	private GImage img01 = new GImage("face01.png");
	private GImage img02 = new GImage("face02.png");

	public Bird() {
		img01.setSize(BIRD_WIDTH, BIRD_HEIGHT);
		img02.setSize(BIRD_WIDTH, BIRD_HEIGHT);
		//this.img = new GImage("face01.png");
		this.img = img01;
	}

	public GImage getImg() {
		return img;
	}

	public void moveUp() {
		this.img.move(0, -VY_DELTA);
		
	}
	
	public void freeMove(){
		RandomGenerator rg = new RandomGenerator ();
		double vx = 0;
		double vy = rg.nextDouble(FALL_MIN_SPEED, FALL_MAX_SPEED);
		this.img.move(vx, vy);
	}
	
	public void flip(){
		int i = 0;
		if (i == 0){
			this.img = img02;
			i = 1;
		} else {
			this.img = img01;
			i = 0;
		}
	}
	

	public boolean notHit(Background sky){

		boolean upperLeft = sky.getElementAt(this.getImg().getX()-1, this.getImg().getY() -1) == null || sky.getElementAt(this.getImg().getX()-1, this.getImg().getY() -1) == sky.getImg();
		boolean bottomLeft = sky.getElementAt(this.getImg().getX() -1, this.getImg().getY() + this.getImg().getHeight() +1) == null || sky.getElementAt(this.getImg().getX() -1, this.getImg().getY() + this.getImg().getHeight() +1) == sky.getImg();
		boolean upperRight = sky.getElementAt(this.getImg().getX() + this.getImg().getWidth() + 1, this.getImg().getY() -1) == sky.getImg();
		boolean bottomRight = sky.getElementAt(this.getImg().getX() + this.getImg().getWidth() + 1, this.getImg().getY() + this.getImg().getHeight() +1) == sky.getImg();
		boolean top = this.getImg().getY() -1 <= 0;
		boolean bottom = this.getImg().getY() + this.getImg().getHeight() + 1 >= sky.getHeight();
		
		if (upperLeft && bottomLeft && upperRight && bottomRight && !top && !bottom){
			return true;
		} 
		return false;
	}

	
	
	

	
	
}
