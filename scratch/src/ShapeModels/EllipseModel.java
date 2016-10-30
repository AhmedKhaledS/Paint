package ShapeModels;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllipseModel implements ShapeModel {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double majorAxis;
	private double minorAxis;
	private Point secondPt, firstPt;

	public EllipseModel(Point firstPt, Point secondPt) {
		this.firstPt = firstPt;
		this.secondPt = secondPt;
		fillInColor = Color.WHITE;
	}

//	public double getMinorAxis() {
//		return minorAxis;
//	}
//
//	public void setMinorAxis(double minorAxis) {
//		this.minorAxis = minorAxis;
//	}
//
//	public double getMajorAxis() {
//		return majorAxis;
//	}
//
//	public void setMajorAxis(double majorAxis) {
//		this.majorAxis = majorAxis;
//	}

	public EllipseModel(double d, double e, double xCent, double yCent) {
		// TODO Auto-generated constructor stub
		majorAxis = d;
		minorAxis = e;
		firstPt.setLocation(xCent, yCent);
	}

	/**
	 * get fill color.
	 * @return Color fill in color
	 * */
	public Color getFillInColor() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return borderColor;
	}

	/**
	 * sets the border color.
	 * @param color to be set as a border color
	 * */
	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		borderColor = color;
	}

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	public double getBorderWidth() {
		// TODO Auto-generated method stub
		return borderWidth;
	}

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	public void setBorderWidth(double borderWidth) {
		// TODO Auto-generated method stub
		borderWidth = this.borderWidth;
	}

	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * @param event the mouse event that called the draw method
	 * */
	public void drawEllipse(Pane paint, Canvas canvas) {
		double centerX = 0.0, centerY = 0.0, minor = 0.0, major = 0.0;
		if (secondPt.getX() > canvas.getWidth()) {
			centerX = (firstPt.getX() + canvas.getWidth()) / 2.0;
		} else {
			centerX = (firstPt.getX() + secondPt.getX()) / 2.0;
		}
		if (secondPt.getY() <= 0) {
			centerY = (firstPt.getY() + 0) / 2.0;
		} else if (secondPt.getY() > canvas.getHeight()) {
			centerY = (firstPt.getY() + canvas.getHeight() - 6.0) / 2.0;
		} else {
			centerY = (firstPt.getY() + secondPt.getY()) / 2.0;
		}
		minor = Math.abs(firstPt.getY() - centerY);
		major = Math.abs(firstPt.getX() - centerX);
		
		
		Ellipse ellipse = new Ellipse(centerX, centerY, major, minor);
		ellipse.setStroke(borderColor);
		ellipse.setFill(fillInColor);
//		MouseGestures drag = new MouseGestures();
//		drag.makeDraggable(ellipse);
		paint.getChildren().add(ellipse);
	}

	@Override
	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		
	}
}
