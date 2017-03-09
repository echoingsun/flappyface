import acm.graphics.GCompound;

public class PairComp extends GCompound implements Constants{
	
	RandomGenerator rg = new RandomGenerator();
	double vx = rg.nextDouble(-MOVE_MAX_SPEED, -MOVE_MIN_SPEED);
	
	public PairComp (Pairs pairs){
		this.add(pairs, pairs.getWidth() + BLOCK_INTERVAL, 0);
	}

	public void moveLeft() {
		this.move(vx, 0);

	}
}
