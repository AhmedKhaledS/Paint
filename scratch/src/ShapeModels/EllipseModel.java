package ShapeModels;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;

import Controllers.Data;
import Controllers.EllipseController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import jsonShapesProperties.JSONData;

public class EllipseModel implements ShapeModel, Cloneable {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	private Ellipse thisEllipse;
	protected double majorAxis;
	protected double minorAxis;
	private Point secondPt, firstPt;

	public EllipseModel(Point firstPt, Point secondPt) {
		this.firstPt = firstPt;
		this.secondPt = secondPt;
		fillInColor = Color.TRANSPARENT;
	}

	public EllipseModel(double d, double e, double xCent, double yCent) {
		majorAxis = d;
		minorAxis = e;
		firstPt = new Point();
		firstPt.setLocation(xCent, yCent);
		secondPt = new Point();
		secondPt.setLocation(firstPt.getX() + majorAxis,
				firstPt.getY() + minorAxis);
		fillInColor = Color.TRANSPARENT;
	}
	
	public Ellipse clone () throws CloneNotSupportedException {
//		Point modifiedPoint = new Point();
//		modifiedPoint.setLocation(Math.max(0, secondPt.getX()),
//				Math.max(0, secondPt.getY()));
//		secondPt.setLocation(modifiedPoint);
		minorAxis = Math.abs(firstPt.getY() - secondPt.getY());
		majorAxis = Math.abs(firstPt.getX() - secondPt.getX());
		Ellipse ellipse = new Ellipse(firstPt.getX(), firstPt.getY(), majorAxis, minorAxis);
		ellipse.setStroke(borderColor);
		ellipse.setFill(fillInColor);
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(ellipse);
		return ellipse;
	}

	
	public void setModel() {
		Point modifiedPoint = new Point();
		modifiedPoint.setLocation(Math.max(0, secondPt.getX()),
				Math.max(0, secondPt.getY()));
		secondPt.setLocation(modifiedPoint);
		minorAxis = Math.abs(firstPt.getY() - secondPt.getY());
		majorAxis = Math.abs(firstPt.getX() - secondPt.getX());
		Ellipse ellipse = new Ellipse(firstPt.getX(), firstPt.getY(), majorAxis, minorAxis);
		ellipse.setStroke(borderColor);
		ellipse.setFill(fillInColor);
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(ellipse);
		thisEllipse = ellipse;
	}

	/**
	 * get fill color.
	 * @return Color fill in color
	 * */
	public Color getFillInColor() {
		return fillInColor;
	}

	public void setFillInColor(Color color) {
		fillInColor = color;
	}


	/**
	 * returns the border color.
	 * @return the border color
	 * */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * sets the border color.
	 * @param color to be set as a border color
	 * */
	public void setBorderColor(Color color) {
		borderColor = color;
	}

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	public double getBorderWidth() {
		return borderWidth;
	}

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	public void setBorderWidth(double borderWidth) {
		borderWidth = this.borderWidth;
	}

	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * @param event the mouse event that called the draw method
	 * */
	@Override
	public void drawShape(Pane paint, Canvas canvas, Data shapes, JSONData json) {
		//handling the ellipse going out of the canvas
		Point modifiedPoint = new Point();
		modifiedPoint.setLocation(Math.max(0.0, secondPt.getX()),
				Math.max(0, secondPt.getY()));
		secondPt = new Point();
		secondPt.setLocation(modifiedPoint);
		minorAxis = Math.abs(firstPt.getY() - secondPt.getY());
		majorAxis = Math.abs(firstPt.getX() - secondPt.getX());
		if (firstPt.getY() - minorAxis < 0) {
			minorAxis = firstPt.getY();
		}
		Ellipse ellipse = new Ellipse(firstPt.getX(), firstPt.getY(), majorAxis, minorAxis);
		ellipse.setStroke(borderColor);
		ellipse.setFill(fillInColor);
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(ellipse);
		json.addEllipse(ellipse);
		shapes.addEllipse(ellipse);
		paint.getChildren().add(ellipse);
	}
}
