package paintProject;

import java.awt.Point;
import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PaintEllipse implements IShape {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double majorAxis;
	private double minorAxis;
	private Point2D.Double center;

	public PaintEllipse(double mjrAxis, double mnrAxis, double xCent, double yCent) {
		majorAxis = mjrAxis;
		minorAxis = mnrAxis;
		center = new Point2D.Double();
		center.setLocation(xCent, yCent);
		fillInColor = Color.WHITE;
	}

	public double getMinorAxis() {
		return minorAxis;
	}

	public void setMinorAxis(double minorAxis) {
		this.minorAxis = minorAxis;
	}

	public double getMajorAxis() {
		return majorAxis;
	}

	public void setMajorAxis(double majorAxis) {
		this.majorAxis = majorAxis;
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
	@Override
	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		Ellipse ellipse = new Ellipse(center.getX(), center.getY(), minorAxis, majorAxis);
		ellipse.setStroke(borderColor);
		ellipse.setFill(fillInColor);
//		MouseGestures drag = new MouseGestures();
//		drag.makeDraggable(ellipse);
		paint.getChildren().add(ellipse);
	}
}
