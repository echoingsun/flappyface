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
		while (notHit(bird)) {
			bird.freeMove();
			pause(DELAY);
		}

	}

	private boolean notHit(Bird aBird) {
		boolean upperLeft = sky.getElementAt(aBird.getImg().getX()-1, aBird.getImg().getY() -1) != null;
		boolean bottomLeft = sky.getElementAt(aBird.getImg().getX() -1, aBird.getImg().getY() + aBird.getImg().getHeight() +1) != null;
		boolean upperRight = sky.getElementAt(aBird.getImg().getX() + aBird.getImg().getWidth() + 1, aBird.getImg().getY() -1) != null;
		boolean bottomRight = sky.getElementAt(aBird.getImg().getX() + aBird.getImg().getWidth() + 1, aBird.getImg().getY() + aBird.getImg().getHeight() +1) != null;
		return false;

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
