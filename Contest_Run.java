

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Contest_Run extends GraphicsProgram implements Constants{

	public void init (){
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run (){
		println(getWidth()*1.0, getHeight()*1.0);
	}

}
