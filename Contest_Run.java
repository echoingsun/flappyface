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
		blockPairs = sky.addBlockPairs(BLOCK_START_POINT);
		sky.pairArray.add(blockPairs);
		
		
		double distance = 0;
		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
			blockPairs.moveLeft();
			if (distance <= BLOCK_INTERVAL + BLOCK_WIDTH){
				distance += Math.abs(blockPairs.vx);
			} else {
				distance = 0;
				
				Pairs newBlockPairs = sky.addBlockPairs(BLOCK_START_POINT);
				sky.pairArray.add(newBlockPairs);
				blockPairs.add(newBlockPairs, blockPairs.getWidth() + BLOCK_INTERVAL, 0);
				
			}			
			pause(DELAY);

			for (int i = 0; i < sky.pairArray.size(); i++){
				if (sky.pairArray.get(i).getX() + BLOCK_WIDTH < 200){
					blockPairs.remove(sky.pairArray.get(i));
					sky.remove(sky.pairArray.get(i));
				}
			}
			
			
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
