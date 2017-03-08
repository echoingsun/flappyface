import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.*;

public class Contest_Run extends Program implements Constants {

	private Background sky = new Background();
	private Bird bird = new Bird();
	private Pairs blockPairs = new Pairs();
	private double distance = 0;

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		loadCanvas();
		loadBird();
		enableMouse();
		blockPairs = sky.addBlockPairs(-500);
		while (bird.notHit(sky)) {
			bird.freeMove();
			pause(DELAY);
			
/*			blockPairs.moveLeft();
			if (distance <= BLOCK_WIDTH){
				distance += blockPairs.vx;
			} else {
				distance = 0;
			}			
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
