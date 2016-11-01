package tryingJavaFX;

import java.util.ArrayList;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Data {
	private ArrayList<Rectangle> rectangles;
	private ArrayList<Polygon> triangles;
	private ArrayList<Line> lines;
	private ArrayList<Ellipse> ellipses;
	private Double CanvasWidth;
	private Double CanvasHeight;
	
	Data() {
		rectangles = new ArrayList<Rectangle>();
		triangles = new ArrayList<Polygon>();
		ellipses = new ArrayList<Ellipse>();
		lines = new ArrayList<Line>();
	}
	public void addRectangle(Rectangle rect) {
		rectangles.add(rect);
	}
	public void addTriangle(Polygon tri) {
		triangles.add(tri);
	}
	public void addEllipse(Ellipse ell) {
		ellipses.add(ell);
	}
	public void addLine(Line line) {
		lines.add(line);
	}
	public Double getCanvasWidth() {
		return CanvasWidth;
	}
	public void setCanvasWidth(Double canvasWidth) {
		CanvasWidth = canvasWidth;
	}
	public Double getCanvasHeight() {
		return CanvasHeight;
	}
	public void setCanvasHeight(Double canvasHeight) {
		CanvasHeight = canvasHeight;
	}
}
