package jsonShapesProperties;

import java.awt.Point;

import javafx.scene.paint.Color;

public class LineProperties {

	private Point start, end;
	private Color stroke;
	public LineProperties(Point start, Point end, Color s) {
		super();
		this.start = start;
		this.end = end;
		stroke = s;
	}

	public Point getStart() {
		return start;
	}

	public Color getStroke() {
		return stroke;
	}

	public Point getEnd() {
		return end;
	}
	
}
