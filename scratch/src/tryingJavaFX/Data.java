package tryingJavaFX;

import java.awt.Point;
import java.util.ArrayList;

import ShapeModels.EllipseModel;
import ShapeModels.LineModel;
import ShapeModels.RectangleModel;
import ShapeModels.TriangleModel;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

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
	public Data clone() throws CloneNotSupportedException {
		Data copy = new Data();
		for (int i = 0; i < lines.size(); i++) {
//			System.out.println(lines.get(i));
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
//			System.out.println(triangles.get(i));
			Polygon currentTriangle = triangles.get(i);
			Point first = new Point();
			Point second = new Point();
			Point third = new Point();
			ObservableList<Double> points = currentTriangle.getPoints();
			for (int i1 = 0; i1 < points.size(); i1++) {
				if (i1 == 0) {					
					first.setLocation(points.get(i1), points.get(i1 + 1));
					i1++;
				} else if (i1 == 2) {
					second.setLocation(points.get(i1), points.get(i1 + 1));
					i1++;
				} else {
					third.setLocation(points.get(i1), points.get(i1 + 1));
					i1++;
				}
			}
//			System.out.println(first + "\n" + second + "\n" + third);
			TriangleModel triangle = new TriangleModel(first, second, third);
			triangle.setBorderColor((Color)currentTriangle.getStroke());
			Polygon triangleClone = triangle.clone();
			copy.addTriangle(triangleClone);
		}
		for (int i = 0; i < rectangles.size(); i++) {
//			System.out.println(rectangles.get(i));
			Rectangle currentRect = rectangles.get(i);
			Point upperLeft = new Point();
			upperLeft.setLocation(currentRect.getX(), currentRect.getY());
			double width = currentRect.getWidth();
			double height = currentRect.getHeight();
			Point bottomRight = new Point();
			bottomRight.setLocation(upperLeft.getX() + width, upperLeft.getY() + height);
			RectangleModel rectangle = new RectangleModel(upperLeft, bottomRight);
			rectangle.setProperties();
			rectangle.setBorderColor((Color)currentRect.getStroke());
			Rectangle cloneRect = rectangle.getClone();
			copy.addRectangle(cloneRect);
		}
		for (int i = 0; i < ellipses.size(); i++) {
			Ellipse currentEll = ellipses.get(i);
			Point center = new Point();
			center.setLocation(currentEll.getCenterX(), currentEll.getCenterY());
			Double major = currentEll.getRadiusX();
			Double minor = currentEll.getRadiusY();
			Point first = new Point();
			first.setLocation(center.getX() - major, center.getY() - minor);
			Point second = new Point();
			second.setLocation(center.getX() + major, center.getY() - minor);
//			EllipseModel ellipse = new EllipseModel(first, second);
//			ellipse.setModel();
//			Ellipse ellipseClone = ellipse.clone();
//			copy.addEllipse(ellipseClone);
//			System.out.println(ellipseClone);
		}
		return copy;
	}
	
	public void updatePane(Pane pane) {
		for (Rectangle curr : rectangles) {
			pane.getChildren().add(curr);
		}
		for (Polygon curr : triangles) {
			pane.getChildren().add(curr);
		}
		for (Ellipse curr : ellipses) {
			pane.getChildren().add(curr);
		}
		for (Line curr : lines) {
			pane.getChildren().add(curr);
		}
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
