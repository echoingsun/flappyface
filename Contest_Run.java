import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
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

	private boolean notHit(Bird aBird){

		boolean upperLeft = sky.getElementAt(aBird.getImg().getX()-1, aBird.getImg().getY() -1) == null;
		boolean bottomLeft = sky.getElementAt(aBird.getImg().getX() -1, aBird.getImg().getY() + aBird.getImg().getHeight() +1) == null;
		
		GObject obj = sky.getElementAt(aBird.getImg().getX() + aBird.getImg().getWidth() + 1, aBird.getImg().getY() -1);
		boolean uR = obj == sky.getImg();
		boolean upperRight = sky.getElementAt(aBird.getImg().getX() + aBird.getImg().getWidth() + 1, aBird.getImg().getY() -1) == null;
		boolean bottomRight = sky.getElementAt(aBird.getImg().getX() + aBird.getImg().getWidth() + 1, aBird.getImg().getY() + aBird.getImg().getHeight() +1) == null;
		boolean top = aBird.getImg().getY() -1 <= 0;
		boolean bottom = aBird.getImg().getY() + aBird.getImg().getHeight() + 1 >= sky.getHeight();
		
		if (upperLeft && bottomLeft && upperRight && bottomRight && !top && !bottom){
			return true;
		} 
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
