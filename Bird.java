import acm.graphics.*;
import acm.util.RandomGenerator;

public class Bird implements Constants {

	private GImage img;
	private GImage img01 = new GImage("face_01.png");
	private GImage img02 = new GImage("face_02.png");
	private GImage img03 = new GImage("face_03.png");
	private GImage img04 = new GImage("face_04.png");
	
	private GImage[] imgArray = new GImage[4];

	public Bird() {
		
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
	

	public void changeImg(){
		
		this.img.setImage(img03.getImage());
	}
	
	public void flip() {
		if (this.img.getImage() == img01.getImage()){
			this.img.setImage(img02.getImage());
		
		} else {
			this.img.setImage(img01.getImage());
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
