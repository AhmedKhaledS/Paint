package jsonShapesProperties;

import java.awt.Point;
import java.util.ArrayList;

import ShapeModels.MouseGestures;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import tryingJavaFX.Data;

public class JSONData implements Cloneable{

	private ArrayList<RectangleProperties> rectangles;
	private ArrayList<LineProperties> lines;
	private ArrayList<TriangleProperties> triangles;
	private ArrayList<EllipseProperties> ellipses;

	public JSONData() {
		rectangles = new ArrayList<RectangleProperties>();
		lines = new ArrayList<LineProperties>();
		triangles = new ArrayList<TriangleProperties>();
		ellipses = new ArrayList<EllipseProperties>();
	}
	public void addRectangle(Rectangle rect) {
		Point upleft = new Point();
		upleft.setLocation(rect.getX(), rect.getY());
		RectangleProperties rectangle = new RectangleProperties(upleft,
				rect.getWidth(), rect.getHeight(),(Color) rect.getFill() ,(Color)rect.getStroke());
		rectangles.add(rectangle);
	}
	public void addTriangle(Polygon tri) {
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		p1.setLocation(tri.getPoints().get(0), tri.getPoints().get(1));
		p3.setLocation(tri.getPoints().get(2), tri.getPoints().get(3));
		p2.setLocation(tri.getPoints().get(4), tri.getPoints().get(5));
		TriangleProperties triangle = new TriangleProperties(p1, p2, p3,
				(Color) tri.getFill(), (Color)tri.getStroke());
		triangles.add(triangle);
	}
	public void addEllipse(Ellipse ell) {
		Point center = new Point();
		center.setLocation(ell.getCenterX(), ell.getCenterY());
		EllipseProperties ellipse = new EllipseProperties(center, ell.getRadiusX(),
				ell.getRadiusY(), (Color) ell.getFill(), (Color) ell.getStroke());
		ellipses.add(ellipse);
	}
	public void addLine(Line l) {
		Point start = new Point();
		Point end = new Point();
		start.setLocation(l.getStartX(), l.getStartY());
		end.setLocation(l.getEndX(), l.getEndY());
		LineProperties line = new LineProperties(start, end, (Color) l.getStroke());
		lines.add(line);
	}
	public JSONData clone() {
		JSONData copy = new JSONData();
		//System.out.println(rectangles.size());
		for (RectangleProperties cur : rectangles) {
			Rectangle r = new Rectangle();
			r.setX(cur.getUpperLeft().getX());
			r.setY(cur.getUpperLeft().getY());
			r.setWidth(cur.getWidth());
			r.setHeight(cur.getHeight());
			copy.addRectangle(r);
		}
		for (EllipseProperties cur : ellipses) {
			Ellipse e = new Ellipse();
			e.setCenterX(cur.getCenter().getX());
			e.setCenterY(cur.getCenter().getY());
			e.setRadiusX(cur.getRadiusX());
			e.setRadiusY(cur.getRadiusY());
			copy.addEllipse(e);
		}
		for (TriangleProperties cur : triangles) {
			Polygon t = new Polygon();
			t.getPoints().add(cur.getFirst().getX());
			t.getPoints().add(cur.getFirst().getY());
			t.getPoints().add(cur.getSecond().getX());
			t.getPoints().add(cur.getSecond().getY());
			t.getPoints().add(cur.getThird().getX());
			t.getPoints().add(cur.getThird().getY());
			copy.addTriangle(t);
		}
		for (LineProperties cur : lines) {
			Line l = new Line();
			l.setStartX(cur.getStart().getX());
			l.setStartY(cur.getStart().getY());
			l.setEndX(cur.getEnd().getX());
			l.setEndY(cur.getEnd().getY());
			copy.addLine(l);
		}
		return copy;
	}
	public Data converToData() {
		Data shapes = new Data();
		MouseGestures drag = new MouseGestures();
		for (RectangleProperties cur : rectangles) {
			Rectangle r = new Rectangle();
			r.setX(cur.getUpperLeft().getX());
			r.setY(cur.getUpperLeft().getY());
			r.setWidth(cur.getWidth());
			r.setHeight(cur.getHeight());
			r.setStroke(Color.BLACK);
			r.setFill(cur.getFill());
			drag.makeDraggable(r);
			shapes.addRectangle(r);
		}
		for (EllipseProperties cur : ellipses) {
			Ellipse e = new Ellipse();
			e.setCenterX(cur.getCenter().getX());
			e.setCenterY(cur.getCenter().getY());
			e.setRadiusX(cur.getRadiusX());
			e.setRadiusY(cur.getRadiusY());
			e.setStroke(cur.getStroke());
			e.setFill(cur.getFill());
			drag.makeDraggable(e);
			shapes.addEllipse(e);
		}
		for (TriangleProperties cur : triangles) {
			Polygon t = new Polygon();
			t.getPoints().add(cur.getFirst().getX());
			t.getPoints().add(cur.getFirst().getY());
			t.getPoints().add(cur.getSecond().getX());
			t.getPoints().add(cur.getSecond().getY());
			t.getPoints().add(cur.getThird().getX());
			t.getPoints().add(cur.getThird().getY());
			t.setStroke(cur.getStroke());
			t.setFill(cur.getFill());
			drag.makeDraggable(t);
			shapes.addTriangle(t);
		}
		for (LineProperties cur : lines) {
			Line l = new Line();
			l.setStartX(cur.getStart().getX());
			l.setStartY(cur.getStart().getY());
			l.setEndX(cur.getEnd().getX());
			l.setEndY(cur.getEnd().getY());
			l.setStroke(cur.getStroke());
			drag.makeDraggable(l);
			shapes.addLine(l);
		}
		return shapes;
	}
	public int getSize() {
		return rectangles.size();
	}
}
