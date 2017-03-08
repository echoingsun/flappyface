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
	private Pairs blockPairs = new Pairs();
	

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		loadCanvas();
		loadBird();
		enableMouse();
		blockPairs = sky.addBlockPairs(BLOCK_START_POINT - BLOCK_INTERVAL);
		
		double distance = 0;
		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			blockPairs.moveLeft();
			if (distance <= BLOCK_INTERVAL){
				distance += Math.abs(blockPairs.vx);
			} else {
				distance = 0;
				
				Pairs newBlockPairs = sky.addBlockPairs(blockPairs.getX() + BLOCK_INTERVAL);

				blockPairs.add(newBlockPairs);
				blockPairs.moveLeft();
			}			
			pause(DELAY);

			
			
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
