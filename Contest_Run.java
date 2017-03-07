import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.program.*;

public class Contest_Run extends Program implements Constants{
	
	private Background sky = new Background();
	private Bird bird = new Bird();
	
	public void init (){
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run (){
		loadCanvas();
		loadBird();

		sky.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// If the mouse is clicked, the oval moves upwards by a certain
				// amount.
				
			}
		});		
		
	}

	private void loadBird() {
		sky.addBird();
	}

	private void loadCanvas() {
		add(sky);
		sky.init();		
	}


}
