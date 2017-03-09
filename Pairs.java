import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pairs extends GCompound implements Constants {

	RandomGenerator rg = new RandomGenerator();
	double vx = rg.nextDouble(-MOVE_MAX_SPEED, -MOVE_MIN_SPEED);
	double moveSpeed = vx;

	public Pairs() {

		for (int i = 0; i < PAIR_NUM; i++) {
			Blocks bottom = new Blocks(false);
			Blocks upper = new Blocks(true);

			double bottomY = rg.nextDouble(0.6 * APPLICATION_HEIGHT, 0.8 * APPLICATION_HEIGHT);
			double upperY = bottomY - rg.nextDouble(0.3 * APPLICATION_HEIGHT, 0.5 * APPLICATION_HEIGHT)
					- upper.getImg().getHeight();

			this.add(upper.getImg(), BLOCK_INTERVAL + (BLOCK_INTERVAL + BLOCK_WIDTH)* i, upperY);
			this.add(bottom.getImg(), BLOCK_INTERVAL + (BLOCK_INTERVAL + BLOCK_WIDTH)* i, bottomY);
		}

	}

	public void moveLeft() {
		this.move(moveSpeed, 0);

	}
}
