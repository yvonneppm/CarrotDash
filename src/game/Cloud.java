package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;

import javax.imageio.ImageIO;


/**
 * The Cloud class represents moving cloud objects in a game.
 * It implements the Movable interface to enable movement functionality.
 * The clouds continuously move from right to left and reset their position when they go off-screen.
 */
public class Cloud implements Movable{
	private BufferedImage cloud1;
	private BufferedImage cloud2;
	private BufferedImage cloud3;
	private BufferedImage cloud4;
	private BufferedImage cloud5;
	private int cloud1X;
	private int cloudY;
	private int cloud2X;
	private int cloud3X;
	private int cloud4X;
	private int cloud5X;
	private int cloudSpeed = 5;
	
    /**
     * Constructor for the Cloud class.
     * Initializes cloud positions, loads images, and starts a timer for movement.
     */
	public Cloud() {
		cloud1X = 20;
		cloudY = 100;
		cloud2X = 175;
		cloud3X = 300;
		cloud4X = 750;
		cloud5X = 550;
		getCloudImage();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new java.util.TimerTask() {
			@Override
			public void run() {
				move();

			}
		}, 0, 30);
	}
	
	/**
     * Moves all clouds to the left by decreasing their x-coordinates.
     * If a cloud moves off-screen, it resets its position to the right.
     */
	@Override
	public void move() {
		// TODO Auto-generated method stub
		cloud1X -= cloudSpeed;
		cloud2X -= cloudSpeed;
		cloud3X -= cloudSpeed;
		cloud4X -= cloudSpeed;
		cloud5X -= cloudSpeed;
	    if (cloud1X < -200) {
	        cloud1X = 800;
	    }
	    if (cloud2X < -200) {
	        cloud2X = 800;
	    }
	    if (cloud3X < -200) {
	        cloud3X = 800;
	    }
	    if (cloud4X < -200) {
	        cloud4X = 800;
	    }
	    if (cloud5X < -200) {
	        cloud5X = 800;
	    }
	}
	
    /**
     * Draws the clouds onto the given Graphics object.
     *
     * @param brush The Graphics object used for rendering.
     */
	void paint(Graphics brush) {
		brush.drawImage(cloud1,cloud1X,cloudY,100,100,null);
		brush.drawImage(cloud2,cloud2X,cloudY+90,100,100,null);
		brush.drawImage(cloud3,cloud3X,cloudY-50,200,200,null);
		brush.drawImage(cloud4,cloud4X,cloudY-50,100,100,null);
		brush.drawImage(cloud5,cloud5X,cloudY+80,100,100,null);
	}
	
    /**
     * Loads cloud images from the resources folder.
     * Catches and prints any exceptions if the images cannot be loaded.
     */
	public void getCloudImage() {
		try {
			cloud1 = ImageIO.read(getClass().getResourceAsStream("/cloud/cloud.png"));
			cloud2 = ImageIO.read(getClass().getResourceAsStream("/cloud/cloud.png"));
			cloud3 = ImageIO.read(getClass().getResourceAsStream("/cloud/cloud.png"));
			cloud4 = ImageIO.read(getClass().getResourceAsStream("/cloud/cloud.png"));
			cloud5 = ImageIO.read(getClass().getResourceAsStream("/cloud/cloud.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
