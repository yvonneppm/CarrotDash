package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;

/**
 * The Flower class represents different types of flowers moving across the screen.
 * Implements the Movable interface to enable movement functionality.
 * The flowers continuously move from right to left and reset their position when they go off-screen.
 */
public class Flower implements Movable{
	private BufferedImage pinkFlower;
	private BufferedImage redFlower;
	private BufferedImage sunflower;
	private int pinkflowerX;
	private int redflowerX;
	private int sunflowerX;
	private int flowerY;
	private int flowerSpeed = 5;
	
    /**
     * Constructor for the Flower class.
     * Initializes flower positions, loads images, and starts a timer for movement.
     */
	public Flower() {
		flowerY = 460;
		pinkflowerX = 100;
		redflowerX = 350;
		sunflowerX = 630;
		getFlowerImage();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new java.util.TimerTask() {
			@Override
			public void run() {
				move();

			}
		}, 0, 30);
		
	}
	
    /**
     * Loads flower images from the resources folder.
     * Catches and prints any exceptions if the images cannot be loaded.
     */
	public void getFlowerImage() {
		try {
			pinkFlower = ImageIO.read(getClass().getResourceAsStream("/flowers/pinkFlower.png"));
			redFlower = ImageIO.read(getClass().getResourceAsStream("/flowers/redFlower.png"));
			sunflower = ImageIO.read(getClass().getResourceAsStream("/flowers/sunflower.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Draws the flowers onto the given Graphics object.
     *
     * @param brush The Graphics object used for rendering.
     */
	void paint(Graphics brush) {
		brush.drawImage(pinkFlower, pinkflowerX, flowerY, 40, 40, null);
		brush.drawImage(redFlower, redflowerX, flowerY, 40, 40, null);
		brush.drawImage(sunflower, sunflowerX, flowerY, 40, 40, null);
	}
	
    /**
     * Moves all flowers to the left by decreasing their x-coordinates.
     * If a flower moves off-screen, it resets its position to the right.
     */
	@Override
	public void move() {
	    pinkflowerX -= flowerSpeed;
	    redflowerX -= flowerSpeed;
	    sunflowerX -= flowerSpeed;


	    if (pinkflowerX < -40) {
	        pinkflowerX = 800;
	    }
	    if (redflowerX < -40) {
	        redflowerX = 800;
	    }
	    if (sunflowerX < -40) {
	        sunflowerX = 800;
	    }
	}
}
