package jsonShapesProperties;

import java.awt.Point;

import javafx.scene.paint.Color;

public class CirclePropeties extends EllipseProperties{
	private Point center;
	private double radius;
	private Color fill;
	private Color stroke;
	public CirclePropeties(Point center, double radiusX, double radiusY, Color f, Color s) {
		super(center, radiusY, radiusY, f, s);
		radius = radiusY;
		// TODO Auto-generated constructor stub
	}
	public Point getCenter() {
		return center;
	}
	public double getRadius() {
		return radius;
	}
	
}
