/*
 * Class: Pairs.java
 * -------------------------
 * "Pairs" is a GCompound that includes PAIR_NUM of blocks of random heights.
 * The game is continuously generating (and removing) such pairs.
 */

import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pairs extends GCompound implements Constants {

	// Used to generate random heights for blocks.
	RandomGenerator rg = new RandomGenerator();

	public Pairs() {

		// Generate PAIR_NUM of block pairs (upper and bottom).
		// Each block will have a random height.
		// The blocks are then added to this GCompound.
		for (int i = 0; i < PAIR_NUM; i++) {
			Blocks bottom = new Blocks(false);
			Blocks upper = new Blocks(true);

			double bottomY = rg.nextDouble(0.6 * APPLICATION_HEIGHT, 0.8 * APPLICATION_HEIGHT);
			double upperY = bottomY - rg.nextDouble(0.2 * APPLICATION_HEIGHT, 0.4 * APPLICATION_HEIGHT)
					- upper.getImg().getHeight();

			this.add(upper.getImg(), BLOCK_INTERVAL + (BLOCK_INTERVAL + BLOCK_WIDTH) * i, upperY);
			this.add(bottom.getImg(), BLOCK_INTERVAL + (BLOCK_INTERVAL + BLOCK_WIDTH) * i, bottomY);
		}

	}

	/*
	 * Method moveLeft enables the GCompound Pairs to move left.
	 * This makes the blocks look like they move together in one.
	 */
	public void moveLeft() {
		this.move(-MOVE_SPEED, 0);
	}
}
