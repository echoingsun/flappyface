import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair extends GCompound implements Constants{
	
	GCompound pair;
	RandomGenerator rg = new RandomGenerator();
	
	public Pair (Background sky){
		pair = new GCompound();
		
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(APPLICATION_HEIGHT * 0.6, APPLICATION_HEIGHT * 0.8);
		sky.add(bottom.getImg(), BLOCK_START_POINT, bottomY);
		
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (APPLICATION_HEIGHT * 0.3, APPLICATION_HEIGHT * 0.5) - upper.getImg().getHeight();
		sky.add(upper.getImg(), BLOCK_START_POINT, upperY);
		
		pair.add(bottom.getImg());
		pair.add(upper.getImg());
		
		
	}
	

	

}
