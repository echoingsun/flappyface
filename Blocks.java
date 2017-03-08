import acm.graphics.*;
import acm.util.RandomGenerator;

public class Blocks implements Constants {

	GImage img;

	public Blocks(boolean isFlip) {

		GImage upperBlock = new GImage("pipeFlip.png");
		GImage bottomBlock = new GImage("pipe.png");

		if (isFlip == true) {
			img = upperBlock;

		} else {
			img = bottomBlock;
		}

	}

	public GImage getImg() {
		return img;
	}


}
