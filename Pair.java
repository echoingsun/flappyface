import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair extends GCompound implements Constants{
	
	GCompound pair;
	RandomGenerator rg = new RandomGenerator();
	
	public Pair (){
		Blocks bottom = new Blocks (false);
		double bottomY = rg.nextDouble(APPLICATION_HEIGHT * 0.6, APPLICATION_HEIGHT * 0.8);
		
		Blocks upper = new Blocks (true);
		double upperY = bottomY - rg.nextDouble (APPLICATION_HEIGHT * 0.3, APPLICATION_HEIGHT * 0.5) - upper.getImg().getHeight();
		
		pair.add(bottom.getImg());
		pair.add(upper.getImg());
		
	}
	
	public void moveLeft(){
		
		double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);
		pair.move(vx, 0);
	}
	

}
