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
		
		comp.add(blockPairs1, 0,0);
		comp.add(blockPairs2, blockPairs1.getX() + blockPairs1.getWidth() + BLOCK_INTERVAL, 0);
		

		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			
			if (blockPairs1.getX() + blockPairs1.getWidth() <= - BLOCK_INTERVAL){
				sky.remove(blockPairs1);
				blockPairs1 = blockPairs2;
				blockPairs2 = sky.addBlockPairs(blockPairs1.getX() + blockPairs1.getWidth() + + BLOCK_INTERVAL);
			} else {
				//blockPairs1.moveLeft();
				comp.move(-1.5, 0);
				
			}			
			pause(DELAY);
			
/*			blockPairs2.moveLeft();
			pause(DELAY);*/

			
			
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
