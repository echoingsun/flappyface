import acm.graphics.GImage;
import acm.program.*;

public class Contest_Run extends GraphicsProgram implements Constants{
	
/*	private Background sky = new Background();
	
	public void init (){
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run (){
		loadCanvas();
		
	}

	private void loadCanvas() {
		add(sky);
		sky.init();		
	}*/
	
	public void init (){
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run() {
		Background sky = new Background ("background.jpg");
		add(sky);
	}

}
