package jsonShapesProperties;

import java.awt.Point;

import javafx.scene.paint.Color;

public class RectangleProperties {

	private Point upperLeft;
	private double width;
	private double height;
	private Color fill;
	private Color stroke;

	public RectangleProperties(Point upleft, double wid, double len, Color f, Color s) {
		upperLeft = new Point();
		upperLeft.setLocation(upleft.getX(), upleft.getY());
		width = wid;
		height = len;
		fill = f;
		stroke = s;
	}

	public Point getUpperLeft() {
		Point cpy = new Point();
		cpy.setLocation(upperLeft.getX(), upperLeft.getY());
		return cpy;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Color getFill() {
		return fill;
	}

	public Color getStroke() {
		return stroke;
	}
	
}
