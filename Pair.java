import acm.graphics.GCompound;
import acm.util.RandomGenerator;

public class Pair extends GCompound implements Constants{
	GCompound pair;
	RandomGenerator rg = new RandomGenerator();
	
	Blocks upper;
	Blocks bottom;
	
	public Pair (){
		pair = new GCompound();
		upper = new Blocks (true);
		bottom = new Blocks (false);
		
		double upperY = 0;
		double bottomY = upperY + upper.getImg().getHeight() + rg.nextDouble(0.3*APPLICATION_HEIGHT, 0.5* APPLICATION_HEIGHT);
		
		pair.add(upper.getImg(),0,upperY);
		pair.add(bottom.getImg(), 0, bottomY);

		
		



		this.add(pair);
	}
}
