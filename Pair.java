import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair implements Constants{
	
	GCompound pair;
	
	public Pair (Blocks bottom, Blocks upper){
		pair.add(bottom.getImg());
		pair.add(upper.getImg());
		
	}
	
	public void moveLeft(){
		
		RandomGenerator rg = new RandomGenerator();
		double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);
		pair.move(vx, 0);
	}
	
	/*
	 * Method addBlocks add a pair of blocks to the screen.
	 */
	public void addBlocks() {
		// add the bottom block.
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(this.getHeight() * 0.6, this.getHeight() * 0.8);
		this.add(bottom.getImg(), BLOCK_START_POINT,bottomY);
		
		// add the upper block
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (this.getHeight() * 0.3, this.getHeight() * 0.5) - upper.getImg().getHeight();
		this.add(upper.getImg(), BLOCK_START_POINT, upperY);

	}
}
