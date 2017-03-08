import acm.graphics.*;
import acm.util.RandomGenerator;

public class Blocks implements Constants {
	
	GImage img;
	boolean isFlip;

	public Blocks() {
		RandomGenerator rg = new RandomGenerator();
		GImage upperBlock = new GImage ("pipeFlip.png");
		GImage bottomBlock = new GImage ("pipe.png");

		if (isFlip == false){
			img = upperBlock;
		} else {
			img = bottomBlock;
		}
		
	}
	
	public void setFlip(){
		isFlip = false;
		isFlip = true;
	}

}
