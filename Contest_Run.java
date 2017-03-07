

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Contest_Run extends GraphicsProgram implements Constants{
	
	private Background sky;
	
	public void init (){
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run (){
		sky = new Background ("background.jpg");
		add (sky,0,0);
	}

}
