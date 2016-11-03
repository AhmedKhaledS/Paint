package jsonShapesProperties;

import java.awt.Point;

import javafx.scene.paint.Color;

public class TriangleProperties {
	
	private Point first;
	private Point second;
	private Point third;
	private Color stroke;
	private Color fill;
	public Point getSecond() {
		return second;
	}
	public Point getThird() {
		return third;
	}
	public Point getFirst() {
		return first;
	}
	public TriangleProperties(Point first, Point second, Point third, Color f, Color s) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
		stroke = s;
		fill =f;
	}
	public Color getStroke() {
		return stroke;
	}
	public Color getFill() {
		return fill;
	}
}
