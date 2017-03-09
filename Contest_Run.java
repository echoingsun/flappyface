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
	private Pairs blockPairs1 = new Pairs();
	private Pairs blockPairs2 = new Pairs();
	private PairComp comp = new PairComp();
	

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		loadCanvas();
		loadBird();
		enableMouse();
		blockPairs1 = sky.addBlockPairs(BLOCK_START_POINT);
		blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + BLOCK_INTERVAL);

		

		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			
			if (blockPairs1.getX() + blockPairs1.getWidth() <= - BLOCK_INTERVAL){
				sky.remove(blockPairs1);
				blockPairs1 = blockPairs2;
				blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + + BLOCK_INTERVAL);
			} else {
				blockPairs1.moveLeft();
				
			}			
			pause(DELAY);
			
			blockPairs2.moveLeft();
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
