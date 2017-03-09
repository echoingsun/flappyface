import acm.graphics.*;
import acm.util.RandomGenerator;

public class Bird implements Constants {

	private GImage img;
	private GImage img01 = new GImage("face_01.png");
	private GImage img02 = new GImage("face_02.png");
	private GImage img03 = new GImage("face_03.png");
	private GImage img04 = new GImage("face_04.png");
	
	private boolean isFlipped = false;
	
	public String[] fileNames = new String[4];	

	public Bird() {
		initializeArray();
		this.img = img01;
	}


	private void initializeArray() {
		fileNames[0] = "face_01.png";
		fileNames[1] = "face_02.png";
		fileNames[2] = "face_03.png";
		fileNames[3] = "face_04.png";		
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
		
		this.img.setImage(fileNames[2]);
	}
	
	public void flip() {
		if (isFlipped == false){
			this.img.setImage(fileNames[1]);
			isFlipped = true;
		
		} else {
			this.img.setImage(fileNames[0]);
			isFlipped = false;
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
