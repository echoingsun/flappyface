import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.program.*;

public class Contest_Run extends Program implements Constants {

	private Background sky = new Background();
	private Bird bird = new Bird();

	public void init() {
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}

	public void run() {
		loadCanvas();
		loadBird();
		enableMouse();



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
