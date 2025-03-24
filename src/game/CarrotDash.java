package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Main game class for Carrot Dash, extending the Game framework.
 * This class manages the game loop, rendering, and game state for a bunny-themed obstacle avoidance game.
 * 
 */
class CarrotDash extends Game {
	static int score = 0;
	private Bunny bunny;
	private Ground ground;
	private ArrayList<Grass> grasses;
	private Flower flower;
	private Cloud cloud;
	private static ArrayList<Obstacles> obstacles = new ArrayList<Obstacles>();
	private boolean isGameOver = false;
	private boolean isGameStarted = false;
	private boolean isRunning = true;
	private GameStartMessage gameStartMessage;
	private GameOverMessage gameOverMessage;
	
	/**
     * Constructs a new CarrotDash game instance.
     * Initializes all game objects, sets up input handling, and starts the game loop thread.
     */
	public CarrotDash() {
		super("Carrot dash",800,600);
		this.setFocusable(true);
		this.requestFocus(); 
		Point[] bunnyPoints = {new Point(100,100)};
		Point[] groundPoints = {new Point(0,600),new Point(0,440),new Point(800,440),new Point(800,600)};
		bunny = new Bunny(bunnyPoints,new Point(100,500),0);
		this.addKeyListener(bunny);
		ground = new Ground(groundPoints,new Point(200,540),0);
		Point[] grassPoints = {new Point(0,5),new Point(0,0),new Point(5,0),new Point(5,5)};
		grasses = new ArrayList<>();
		int x = 10;
		for (int i = 0; i <= 40; i++) {
			grasses.add(new Grass(grassPoints, new Point(x, 510), 45));
			x += 20;
		}
		flower = new Flower();
		cloud = new Cloud();
		addObstacle();
		gameStartMessage = new GameStartMessage();
		gameOverMessage = new GameOverMessage();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(!isGameStarted && e.getKeyCode() == KeyEvent.VK_SPACE) {
					isGameStarted = true;
				}
				if(isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
					restartGame();
				}
			}
		});

		new Thread(() -> {
			while (isRunning) {
				if (!isGameOver && isGameStarted) {
					move(); 
				}
				repaint();
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
	
	/**
     * Adds a new obstacle to the game.
     */
  	public static void addObstacle() {
  		obstacles.add(new Obstacles());
  	}
  	
  	/**
     * Updates the position of all obstacles in the game.
     */ 	
  	public static void updateObstacles() {
  		for (Obstacles obstacle : obstacles) {
  			obstacle.move();
  		}
  	}
      	
  	/**
     * Renders the game graphics.
     * Handles different game states (start screen, game play, game over) and draws all game elements.
     * 
     * @param brush the Graphics object used for drawing
     */
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	if(!isGameStarted) {
    		gameStartMessage.paint(brush);
    		return;
    	}
    	
    	if (isGameOver) {
    		gameOverMessage.paint(brush);
            return;
        }
    	brush.setColor(new Color(172, 210, 227));
    	brush.fillRect(0,0,width,height);
        brush.setColor(Color.white);
        brush.drawString("Score: " + score, 30, 30);
    	updateObstacles();
    	flower.paint(brush);
    	bunny.paint(brush);
    	for (Obstacles obstacle : obstacles) {
    		obstacle.paint(brush);
    	}
    	brush.setColor(new Color(75, 156, 62));
    	ground.paint(brush);
    	brush.setColor(new Color(42, 92, 34));
    	for (Grass grass : grasses) {
            grass.paint(brush);
        }
    	cloud.paint(brush);
    }
	
	/**
     * Checks for collisions between the bunny and obstacles.
     * 
     * @return true if a collision occurred, false otherwise
     */
	public boolean collision() {
		for (Obstacles obstacle : obstacles) {
			if (bunny.getBounds().intersects(new Rectangle(obstacle.getBounds()))) {
				isGameOver = true;
				return true;
			}
		}
		return false;
	}
	
	/**
     * Updates the game state by moving game objects and checking collisions.
     */
	public void move() {
		if (isGameOver) {
			return;
		}
		score++;
		bunny.move();
		for (Obstacles obstacle : obstacles) {
			obstacle.move();
		}
		collision();
	}
	
	/**
     * Resets the game state to start a new game.
     */
	public void restartGame() {
        isGameOver = false;
        score = 0;
        obstacles.clear();
        addObstacle();
        bunny = new Bunny(new Point[]{new Point(100,100)}, new Point(100,500), 0);
        this.addKeyListener(bunny);
        repaint();
    }
	
	/**
     * Inner class for displaying the game start message.
     */
	private class GameStartMessage {
		/**
         * Draws the start screen message.
         * 
         * @param brush the Graphics object used for drawing
         */
        public void paint(Graphics brush) {
        	brush.setColor(Color.black);
        	brush.fillRect(0,0,width,height);
    		brush.setColor(Color.white);
    		brush.setFont(new Font("Pixelify Sans", Font.BOLD, 40));
    		brush.drawString("C A R R O T  D A S H", 205, 200);
    		brush.setFont(new Font("Press Start 2P", Font.BOLD, 20));
    		brush.drawString("Press SPACE to Start", 290, 300);
        }
    }
	
	/**
     * Inner class for displaying the game over message.
     */
    private class GameOverMessage {
    	/**
         * Draws the game over screen message including final score.
         * 
         * @param brush the Graphics object used for drawing
         */
        public void paint(Graphics brush) {
        	brush.setColor(Color.black);
        	brush.fillRect(0,0,width,height);
            brush.setColor(Color.red);
            brush.setFont(new Font("Pixelify Sans", Font.BOLD, 40));
            brush.drawString("G A M E  O V E R!", 240, 270);
            brush.setColor(Color.white);
            brush.setFont(new Font("Press Start 2P", Font.BOLD, 20));
            brush.drawString("Press 'R' to Restart", 305, 340);
            brush.setFont(new Font("Press Start 2P", Font.BOLD, 30));
            brush.drawString("Your Score: " + score, 290, 310);
        }
    }
    /**
     * Main entry point for the Carrot Dash game.
     * 
     * @param args command line arguments (not used)
     */
	public static void main (String[] args) {
   		CarrotDash a = new CarrotDash();
		a.repaint();
  }
}