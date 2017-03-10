
/*
 * File: Blocks.java
 * ----------------------------
 * This class defines a single block.
 * These blocks will be used by a GCompound class, "Pairs".
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Blocks implements Constants {

	// Each block has an image to it.
	private GImage img;

	// Constructor: generate a block.
	// There're bottom blocks and upper blocks, and each has a different image.
	// By passing a boolean value, there can be upper and bottom blocks.
	public Blocks(boolean isFlip) {
		GImage upperBlock = new GImage(PIPEFLIP_FILENAME);
		GImage bottomBlock = new GImage(PIPE_FILENAME);

		if (isFlip == true) {
			img = upperBlock;
		} else {
			img = bottomBlock;
		}
	}

	// Image getter.
	public GImage getImg() {
		return img;
	}
}
