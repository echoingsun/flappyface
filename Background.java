import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.program.*;
import acm.util.RandomGenerator;

public class Background extends GCanvas implements Constants{

	private GImage img;
	RandomGenerator rg = new RandomGenerator();
	
	
	public void init (){
		addBackground();
	}

	private void addBackground() {		
		img = new GImage ("bckgrnd.png");
		img.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		add(img);
	}

	/*
	 * Method addBird puts the flying object onto the screen
	 * at a random position at the left, on the upper part of the screen.
	 */
	public void addBird(Bird bird) {
		
		double x = this.getWidth() * 0.5 - bird.getImg().getWidth() * 0.5;
		double y = this.getHeight() * 0.5 - bird.getImg().getHeight() * 0.5;		
		this.add (bird.getImg(), x, y);
	}
	

	public Pairs addBlockPairs(double x){
		Pairs pairs = new Pairs();
		this.add(pairs, x, 0);
		return pairs;
	}

	public Displays addDisplay(String str){
		if (str.equals("Title")) {
			Displays title = new Displays ("Title", "", 0);
			double x = this.getWidth() * 0.5 - title.getImg().getWidth() * 0.5;
			double y = this.getHeight() * 0.5 - title.getImg().getHeight() * 0.5;		
			this.add (title.getImg(), x, y);
			return title;
		} else if (str.equals("ClickToStart")){
			Displays clickToStart = new Displays ("ClickToStart", "", 0);
			double x = this.getWidth() * 0.5 - clickToStart.getImg().getWidth() * 0.5;
			double y = this.getHeight() * 0.5 + clickToStart.getImg().getHeight() * 0.6;
			this.add (clickToStart.getImg(), x, y);
			return clickToStart;
		} else if (str.equals("GameOver")){
			Displays gameOver = new Displays("GameOver", "",0);
			double x = this.getWidth() * 0.5 - gameOver.getImg().getWidth() * 0.5;
			double y = this.getHeight() * 0.5 - gameOver.getImg().getHeight();
			this.add (gameOver.getImg(), x, y);
			return gameOver;
		}
		return null;
	}
	public GImage getImg() {
		return img;
	}
	

}
