/*
 * File: Face.java
 * ------------------------
 * This class defines the face. It controls some images to be
 * replaced under certain circumstances.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Face implements Constants {

	// Face will have an image.
	private GImage img;
	
	// isFlipped detects if it's time to swap face.
	private boolean isFlipped = false;
	
	// An array that stores all filenames to be used by the Face class.
	public String[] fileNames = new String[4];	

	// Constructor: set the image of the object Face to a certain image.
	public Face() {
		
		// Put all filenames into the array.
		initializeArray();
		
		// Assign an image.
		this.img = new GImage(fileNames[0]);
	}

	/*
	 * Method initializeArray puts all the filenames to be used into the array.
	 */
	private void initializeArray() {
		fileNames[0] = "face_01.png"; // An exciting face.
		fileNames[1] = "face_02.png"; // A grinning face.
		fileNames[2] = "face_03.png"; // A confused face.
		fileNames[3] = "face_04.png"; // An annoyed face.
	}

	// Image getter.
	public GImage getImg() {
		return img;
	}

	/*
	 * Method moveUp defines the distance the face will move once
	 * mouseClicked is activated.
	 */
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
	
	public void faceLoop(int i){
		this.img.setImage(fileNames[i]);
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
