import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class PairComp extends GCompound implements Constants{
	
	RandomGenerator rg = new RandomGenerator();
	double vx = rg.nextDouble(-MOVE_MAX_SPEED, -MOVE_MIN_SPEED);
	GCompound comp;
	
	public PairComp (){
		comp = new GCompound();
	}

	public void moveLeft() {
		this.move(vx, 0);

	}
}
