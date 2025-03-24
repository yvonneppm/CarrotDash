package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;

/**
 * The Bunny class represents the player character (bunny) in Carrot Dash. 
 * It handles bunny movement, jumping mechanics, and keyboard input.
 */
public class Bunny extends Polygon implements Movable, KeyListener {
	private boolean jump;
	private BufferedImage bunny1;
	private int bunnyX = 40;
	private int bunnyY = 450;
	private double bunnyRotate = 0;
	
	/**
     * Constructs a new Bunny instance.
     * Initializes the bunny's shape, position, and sets up movement timing.
     * 
     * @param inShape the array of points defining the bunny's shape
     * @param inPosition the initial position of the bunny
     * @param inRotation the initial rotation of the bunny
     */
	public Bunny(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
		getBunnyImage();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new java.util.TimerTask() {
			@Override
			public void run() {
				move();
			}
		}, 0, 30);
	}
	
	/**
     * Renders the bunny image on the screen.
     * 
     * @param brush the Graphics object used for drawing
     */
	void paint(Graphics brush) {
		//BufferedImage image = bunny1;
		//brush.drawImage(image, bunnyX, bunnyY, 50, 50, null);
		Graphics2D bunny2d = (Graphics2D) brush;
		AffineTransform orginalTransform = bunny2d.getTransform();
		bunny2d.translate(bunnyX + 25, bunnyY + 25);
		bunny2d.rotate(bunnyRotate);
		bunny2d.drawImage(bunny1, -25, -25, 50, 50, null);
		bunny2d.setTransform(orginalTransform);
	}
	
	/**
     * Loads the bunny image from resources.
     * Handles potential IO exceptions during image loading.
     */
	public void getBunnyImage() {
		try {
			bunny1 = ImageIO.read(getClass().getResourceAsStream("/bunny/bunny1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Updates the bunny's position based on jumping state and gravity.
     * Implements the Movable interface.
     */
	@Override
	public void move() {
		// TODO Auto-generated method stub
		int jumpHeight = 200;
		int gravity = 6;
		if (jump) {
			if (bunnyY >= 450) {
				jump = false;
			}
			bunnyY -= jumpHeight;
			bunnyRotate += 0.15;
			if (bunnyRotate > 360) {
				bunnyRotate = 0;
			}
		} else if (bunnyY < 450) {
			bunnyY += gravity;
			bunnyRotate -= 0.15;
		} else {
			bunnyY = 450;
			bunnyRotate = 0;
		}
	}
	
	/**
     * Handles key typed events (not used in this implementation).
     * Implements KeyListener interface.
     * 
     * @param e the KeyEvent to process
     */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	/**
     * Handles key press events, specifically for jumping.
     * Implements KeyListener interface.
     * 
     * @param e the KeyEvent to process
     */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE && bunnyY == 450) {
			jump = true;
		}
	}
	
	/**
     * Handles key release events, specifically for jumping.
     * Implements KeyListener interface.
     * 
     * @param e the KeyEvent to process
     */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump = false;
		}
	}
	
	/**
     * Returns the bounding rectangle of the bunny for collision detection.
     * 
     * @return Rectangle representing the bunny's current bounds
     */
	public Rectangle getBounds() {
		return new Rectangle(bunnyX, bunnyY, 50, 50);
	}
}
