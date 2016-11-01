package tryingJavaFX;

import java.awt.Point;
import java.util.ArrayList;

import ShapeModels.LineModel;
import ShapeModels.RectangleModel;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Data implements Cloneable {
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
	public Data clone() {
		Data copy = new Data();
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
			Line currentLine = lines.get(i);
			Point start = new Point();
			start.setLocation(currentLine.getStartX(), currentLine.getStartY());
			Point end = new Point();
			end.setLocation(currentLine.getEndX(), currentLine.getEndY());
			LineModel line = new LineModel(start, end);
			Line cloneLine = line.getClone();
			copy.addLine(cloneLine);
		}
		for (int i = 0; i < triangles.size(); i++) {
//			clone.addTriangle(triangles.get(i));
		}
		for (int i = 0; i < rectangles.size(); i++) {
			System.out.println(rectangles.get(i));
			Rectangle currentRect = rectangles.get(i);
			Point upperLeft = new Point();
			upperLeft.setLocation(currentRect.getX(), currentRect.getY());
			double width = currentRect.getWidth();
			double height = currentRect.getHeight();
			Point bottomRight = new Point();
			bottomRight.setLocation(upperLeft.getX() + width, upperLeft.getY() + height);
			RectangleModel rectangle = new RectangleModel(upperLeft, bottomRight);
			rectangle.setProperties();
			Rectangle cloneRect = rectangle.getClone();
			copy.addRectangle(cloneRect);
		}
		for (int i = 0; i < ellipses.size(); i++) {
//			clone.addEllipse(ellipses.get(i));
		}
		return copy;
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
