package jsonShapesProperties;

import java.awt.Point;

import javafx.scene.paint.Color;

public class EllipseProperties {

	private Point center;
	private double radiusX;
	private double radiusY;
	private Color stroke;
	private Color fill;
	public EllipseProperties(Point center, double radiusX, double radiusY, Color f, Color s) {
		this.center = center;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
	}
	public Point getCenter() {
		return center;
	}
	public double getRadiusX() {
		return radiusX;
	}
	public double getRadiusY() {
		return radiusY;
	}
	public Color getStroke() {
		return stroke;
	}
	public Color getFill() {
		return fill;
	}
	public void setRadiusY(double radiusY) {
		this.radiusY = radiusY;
	}
	
}
