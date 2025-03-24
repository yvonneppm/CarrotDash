package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Ground class represents the ground in the game using a polygon shape.
 * It extends the Polygon class and provides rendering functionality.
 */
public class Ground extends Polygon{
	
    /**
     * Constructor for the Ground class.
     *
     * @param inShape    An array of Points defining the shape of the ground polygon.
     * @param inPosition The initial position of the ground polygon.
     * @param inRotation The initial rotation of the ground polygon.
     */
	public Ground(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Paints the ground polygon onto the given Graphics object.
     * Extracts the polygon's points and fills the shape.
     *
     * @param brush The Graphics object used for rendering.
     */
	void paint(Graphics brush) {
        Point[] points = this.getPoints();

        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int)points[i].x; // Extract x-coordinate
            yPoints[i] = (int)points[i].y; // Extract y-coordinate
        }

        brush.fillPolygon(xPoints, yPoints, points.length);
    }
}
