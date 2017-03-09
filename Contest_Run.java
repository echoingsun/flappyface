import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.*;

public class Contest_Run extends Program implements Constants {

	private Background sky = new Background();
	private Bird bird = new Bird();
	//private Pairs blockPairs = new Pairs();
	private PairComp comp = new PairComp();

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		loadCanvas();
		loadBird();
		enableMouse();
		//blockPairs = sky.addBlockPairs(BLOCK_START_POINT);
		Pairs blockPairs = sky.addBlockPairs(BLOCK_START_POINT);
		comp.add(blockPairs, blockPairs.getWidth() + BLOCK_INTERVAL,0);
		sky.pairArray.add(blockPairs);
		
		
		double distance = 0;
		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			//blockPairs.moveLeft();
			comp.moveLeft();
			if (distance <= BLOCK_INTERVAL + BLOCK_WIDTH){
				//distance += Math.abs(blockPairs.vx);
				distance += Math.abs(comp.vx);
			} else {
				distance = 0;
				
				Pairs newBlockPairs = sky.addBlockPairs(0);
				sky.pairArray.add(newBlockPairs);
				//blockPairs.add(newBlockPairs, blockPairs.getWidth() + BLOCK_INTERVAL, 0);
				comp.add(newBlockPairs, blockPairs.getWidth() + BLOCK_INTERVAL, 0);
			}			
			pause(DELAY);

/*			if (sky.pairArray.get(0).getX() + BLOCK_WIDTH < 200){
				blockPairs = null;
				for (int i = 1; i < sky.pairArray.size(); i ++){
					blockPairs.add(sky.pairArray.get(i));
				}
				sky.remove(sky.pairArray.get(0));
			}*/
			
			
		}

	}


	private void enableMouse() {
		sky.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				bird.moveUp();
			}
		});

	}

	private void loadBird() {
		sky.addBird(bird);
	}

	private void loadCanvas() {
		add(sky);
		sky.init();
	}

}
