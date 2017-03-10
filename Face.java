
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
	 * Method moveUp defines the distance the face will move once mouseClicked
	 * is activated.
	 */
	public void moveUp() {
		this.img.move(0, -VY_DELTA);
	}

	/*
	 * Method freeMove defines the distance the face will fall when no
	 * mouseAction is performed. This distance is random for every game.
	 */
	public void freeMove() {
		RandomGenerator rg = new RandomGenerator();
		double vy = rg.nextDouble(FALL_MIN_SPEED, FALL_MAX_SPEED);
		this.img.move(0, vy);
	}

	/*
	 * Method changeImg changes the image of the face when player loses the
	 * game.
	 */
	public void changeImg() {
		this.img.setImage(fileNames[2]);
	}

	/*
	 * Method faceLoop randomly gets a face in the array and sets face to that
	 * image.
	 */
	public void faceLoop(int i) {
		this.img.setImage(fileNames[i]);
	}

	/*
	 * Method flip changes the image of the face every time player scores a
	 * certain amount of points.
	 */
	public void flip() {
		if (isFlipped == false) {
			this.img.setImage(fileNames[1]);
			isFlipped = true;
		} else {
			this.img.setImage(fileNames[0]);
			isFlipped = false;
		}

	}

	/*
	 * Boolean notHit defines when it is considered game over. Face should not
	 * bump into any blocks. This check is performed through examining the four
	 * corners of the image. Often it might look like the face hasn't hit the
	 * blocks yet, but since the border lies outside the image, this is more or
	 * less inevitable.
	 */
	public boolean notHit(Background sky) {

		boolean upperLeft = sky.getElementAt(this.getImg().getX() - 1, this.getImg().getY() - 1) == null
				|| sky.getElementAt(this.getImg().getX() - 1, this.getImg().getY() - 1) == sky.getImg();
		boolean bottomLeft = sky.getElementAt(this.getImg().getX() - 1,
				this.getImg().getY() + this.getImg().getHeight() + 1) == null
				|| sky.getElementAt(this.getImg().getX() - 1,
						this.getImg().getY() + this.getImg().getHeight() + 1) == sky.getImg();
		boolean upperRight = sky.getElementAt(this.getImg().getX() + this.getImg().getWidth() + 1,
				this.getImg().getY() - 1) == sky.getImg();
		boolean bottomRight = sky.getElementAt(this.getImg().getX() + this.getImg().getWidth() + 1,
				this.getImg().getY() + this.getImg().getHeight() + 1) == sky.getImg();
		boolean top = this.getImg().getY() - 1 <= 0;
		boolean bottom = this.getImg().getY() + this.getImg().getHeight() + 1 >= sky.getHeight();

		if (upperLeft && bottomLeft && upperRight && bottomRight && !top && !bottom) {
			return true;
		}
		return false;
	}

}
