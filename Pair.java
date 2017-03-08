import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair extends GCompound implements Constants{
	
	GCompound pair;
	RandomGenerator rg = new RandomGenerator();
	
	public Pair (){
		pair = new GCompound();
		
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(APPLICATION_HEIGHT * 0.6, APPLICATION_HEIGHT * 0.8);
		
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (APPLICATION_HEIGHT * 0.3, APPLICATION_HEIGHT * 0.5) - upper.getImg().getHeight();
		
		pair.add(bottom.getImg(), 0,0);
		// pair.add(upper.getImg(), BLOCK_START_POINT, upperY);
		
	}
	
	public void moveLeft(){
		
		double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);
		pair.move(vx, 0);
	}
	

}
