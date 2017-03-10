/*
 * File: Constants.java
 * -------------------------
 * This file defines the constants for the progrma.
 */
public interface Constants {
	
	/** PROGRAM: sizes of the application */
	public static final int APPLICATION_WIDTH = 420;
	public static final int APPLICATION_HEIGHT = 712;	
	
	/** PROGRAM: value of main types of pauses */
	public static final int DELAY = 4;
	public static final int GAMEOVER_DELAY = 2000;
	public static final double OFFSET = 0.1;
	
	/** BLOCKS: measurements */
	public static final int BLOCK_HEIGHT = 793;
	public static final int BLOCK_WIDTH = 138;
	public static final int BLOCK_START_POINT = 500;
	public static final int BLOCK_INTERVAL = 162;
	
	/** BLOCKPAIRS: number of pairs */
	public static final int PAIR_NUM = 3;
	
	/** BLOCKPAIRS: speed of moving left */
	public static final double MOVE_SPEED = 1.5;
	
	/** FACE: distance of upward movement */
	public static final int VY_DELTA = 20;
	
	/** FACE: range of free fall speed */
	public static final double FALL_MIN_SPEED = 0.3;
	public static final double FALL_MAX_SPEED = 0.5;
	
	/** FACE: position of centered face */
	public static final int CENTER_FACE = 346;
	
	/** TITLE: measurements */
	public static final int TITLE_HEIGHT = 144;
	public static final int TITLE_UPPER_BORDER = 138;
	public static final int TITLE_LOWER_BORDER = 144;
	public static final int TITLE_FLOAT_DELAY = 100;
	public static final double TITLE_MOVE_AMT = 1.5;
	
	/** GAMEOVER: measurements */
	public static final int GAMEOVER_HEIGHT = 216;
	public static final int GAMEOVER_UPPER_BORDER = 210;
	public static final int GAMEOVER_LOWER_BORDER = 216;
	
	/** OTHER DISPLAYS: positions and measurements*/
	public static final int CLICK_START_HEIGHT = 370;
	public static final int CLICK_QUIT_HEIGHT = 550;
	public static final int INFO_LABEL_HEIGHT = 430;
	public static final int SCORELABEL_HEIGHT = 410;
	public static final int PTS_HEIGHT = 469;
	public static final int INFO_X = 215;
	public static final int INFO_Y = 455;
	public static final int POINTS_SCREEN_X = 160;
	
	/** FILENAME: strings */
	public static final String CVS_FILENAME = "bckgrnd.png";
	public static final String PIPE_FILENAME = "pipe.png";
	public static final String PIPEFLIP_FILENAME = "pipeFlip.png";
	public static final String THEME_FILENAME = "theme.au";
	public static final String TITLEMSC_FILENAME = "title.au";
	public static final String CLICKSND_FILENAME = "clickSound.au";
	public static final String LOSEMSC_FILENAME = "lose.au";
	public static final String TITLEIMG_FILENAME = "FlappyFaceTitle.png";
	public static final String CLICK_START_FILENAME = "clicktostart.png";
	public static final String GAMEOVER_FILENAME = "gameover.png";
	public static final String SCOREMSG_FILENAME = "score.png";
	public static final String INFOLABEL_FILENAME = "InstructionLabel.png";
	public static final String INFO_FILENAME = "Instructions.png";
	public static final String CLICK_QUIT_FILENAME = "clicktoquit.png";

}
