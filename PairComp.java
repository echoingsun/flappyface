import acm.graphics.GCompound;

public class PairComp extends GCompound implements Constants{
	
	public PairComp (Pairs pairs){
		this.add(pairs, blockPairs.getWidth() + BLOCK_INTERVAL, 0);
	}

}
