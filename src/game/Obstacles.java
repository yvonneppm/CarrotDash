package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;

/**
 * The Obstacles class represents obstacles in the game, which can be either rocks or bushes.
 * These obstacles move across the screen and reset when they go off-screen.
 */
public class Obstacles implements Movable{
		private int obstacleX;
		private int obstacleY;
		private int width;
		private int height;
		private boolean isBush;
		private Color color;
		private Random random;
		private int obstacleRock = 450;
		private int obstacleBush = 450;
		private int widthRock = 50;
		private int widthBush = 60;
		private int heightRock = 50;
		private int heightBush = 60;
		private Color colorTree =  new Color(139, 69, 19);
		private Color colorRock =  new Color(128, 128, 128);
		private BufferedImage rock;
		private BufferedImage bush;
		
	    /**
	     * Constructs an Obstacles object with random characteristics.
	     * Initializes position, selects a type (rock or bush), and starts movement.
	     */
		public Obstacles() {
			random = new Random();
			isBush = random.nextBoolean();
			resetPosition();
			getObstacleImage();
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new java.util.TimerTask() {
				public void run() {
					move();
				}
			}, 0, 30);
		}
		

	    /**
	     * Resets the obstacle's position and attributes when it moves off-screen.
	     */
		private void resetPosition() {
			obstacleX = 800 + random.nextInt(200);
			isBush = random.nextBoolean();
			if (isBush) {
				obstacleY = obstacleBush;
			} else {
				obstacleY = obstacleRock;
			}
			
			if (isBush) {
				width = widthBush;
			} else {    
				width = widthRock;
			}
			
			if(isBush) {
				height = heightBush;
			} else {
				height = heightRock;
			}
			
			if (isBush) {
				color = colorTree;
			} else {
				color = colorRock;
			}
		}
		
		/**
		 * Getter for bush
		 * 
		 * @return image of bush
		 */
		public int getObstacleBush() {
			return obstacleBush;
		}
		
		/**
		 * Getter for rock
		 * 
		 * @return image of rock
		 */
		public int getObstacleRock() {
			return obstacleRock;
		}
		
		/**
		 * Getter for width of bush
		 * 
		 * @return width of bush
		 */
		public int getWidthBush() {
			return widthBush;
		}
		
		/**
		 * Getter for width of rock
		 * 
		 * @return width of rock
		 */
		public int getWidthRock() {
			return widthRock;
		}
		
		/**
		 * Getter for height of bush
		 * 
		 * @return height of bush
		 */
		public int getHeightBush() {
			return heightBush;
		}
		
		/**
		 * Getter for height of rock
		 * 
		 * @return height of rock
		 */
		public int getHeightRock() {
			return heightRock;
		}
		
	    /**
	     * Retrieves obstacle images from the resources.
	     */
		public void getObstacleImage() {
			try {
				rock = ImageIO.read(getClass().getResourceAsStream("/obstacles/rock1.png"));
				bush = ImageIO.read(getClass().getResourceAsStream("/obstacles/bush.png"));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	    /**
	     * Paints the obstacle on the screen.
	     *
	     * @param brush The Graphics object used for rendering.
	     */
		void paint(Graphics brush) {
			if (isBush) {
				brush.drawImage(rock,obstacleX,obstacleY,widthRock,heightRock,null);
			} else {
				brush.drawImage(bush,obstacleX, obstacleY,widthBush,heightBush,null);
			}
		}
		
	    /**
	     * Moves the obstacle leftward across the screen.
	     * Resets its position when it goes off-screen.
	     */
		@Override
		public void move() {
			obstacleX -= 4;
			
			if(obstacleX < -40) {
				resetPosition();
			}
		}
		
	    /**
	     * Returns a bounding box for collision detection.
	     *
	     * @return A Rectangle representing the obstacle's bounds.
	     */
		public Rectangle getBounds() {
			return new Rectangle(obstacleX, obstacleY, width-30, height-10);
		}


}
