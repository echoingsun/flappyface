import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair extends GCompound implements Constants{

	RandomGenerator rg = new RandomGenerator();
	double vx = rg.nextDouble(-MAX_SPEED, -MIN_SPEED);

	public Pair (){
				
		Blocks bottom = new Blocks (false);
		Blocks upper = new Blocks (true);
		
		double bottomY = rg.nextDouble (0.6*APPLICATION_HEIGHT, 0.8*APPLICATION_HEIGHT);
		double upperY = bottomY - rg.nextDouble(0.3*APPLICATION_HEIGHT, 0.6*APPLICATION_HEIGHT) - upper.getImg().getHeight();
				
		this.add(upper.getImg(),BLOCK_START_POINT, upperY);
		this.add(bottom.getImg(), BLOCK_START_POINT, bottomY);
	}
	
	public void moveLeft(){
		
		while (true){
			this.move(vx, 0);
		}
			
	}
}
