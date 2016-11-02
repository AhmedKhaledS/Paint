package ShapeModels;

import java.awt.Point;
import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tryingJavaFX.Data;

public class RectangleModel extends PolygonModel implements Cloneable {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	private Rectangle thisRectangle;
	private Point secondPt;
	private Point firstPt;
	
	public RectangleModel(Point firstPt, Point secondPt) {
		this.firstPt = firstPt;
		this.secondPt = secondPt;
		fillInColor = Color.TRANSPARENT;
	}
	
	public void setProperties() {
		Rectangle rectangle = new Rectangle();
		double length, width;
		Point upperLeft = new Point();
		length = Math.abs(secondPt.getX() - firstPt.getX());
		width = Math.abs(secondPt.getY() - firstPt.getY());
		upperLeft.setLocation(Math.min(firstPt.getX(), secondPt.getX()),
				Math.min(firstPt.getY(), secondPt.getY()));
		rectangle.setX(upperLeft.getX());
		rectangle.setY(upperLeft.getY());
		rectangle.setWidth(length);
		rectangle.setHeight(width);
		rectangle.setStroke(borderColor);
		rectangle.setFill(fillInColor);
		MouseGestures mg = new MouseGestures();
		mg.makeDraggable(rectangle);
		thisRectangle = rectangle;
	}
	/**
	 * get fill color.
	 * @return Color fill in color
	 * */
	public Color getFillInColor() {
		// TODO Auto-generated method stub
		return fillInColor;
	}

	/**
	 * sets the fill-in color of the shape.
	 * @param color the color to be set
	 * */
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
		return borderWidth;
	}

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	public void setBorderWidth(double borderWidth) {
		borderWidth = this.borderWidth;
	}
	
	public Rectangle getClone() throws CloneNotSupportedException {
		Rectangle clone = new Rectangle();
		clone.setX(thisRectangle.getX());
		clone.setY(thisRectangle.getY());
		clone.setWidth(thisRectangle.getWidth());
		clone.setHeight(thisRectangle.getHeight());
		clone.setWidth(clone.getWidth());
		clone.setHeight(clone.getHeight());
		clone.setStroke(borderColor);
		clone.setFill(fillInColor);
		return clone;
	}

	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * @param event the mouse event that called the draw method
	 * */
	public void drawRect(Pane paint, Canvas canvas, Data shapes) {
		// TODO Auto-generated method stub
		Rectangle rectangle = new Rectangle();
		Point upperLeft = new Point();
		double centerX, centerY, length, width;
		if (secondPt.getX() > canvas.getWidth()) {
			centerX = (firstPt.getX() + canvas.getWidth() - 3) / 2.0;
			length = Math.abs(canvas.getWidth() - firstPt.getX());
			secondPt.setLocation(canvas.getWidth(), secondPt.getY());
		} else {
			centerX = (firstPt.getX() + secondPt.getX()) / 2.0;
			length = Math.abs(secondPt.getX() - firstPt.getX());
		} if (secondPt.getY() > canvas.getHeight()) {
			centerY = (firstPt.getY() + canvas.getHeight() - 3) / 2.0;
			width = Math.abs(canvas.getHeight() - firstPt.getY());	
			secondPt.setLocation(secondPt.getX(),  canvas.getHeight());
		} else if (secondPt.getY() < 0){
			centerY = (firstPt.getY() + 0) / 2;
			width = Math.abs(5 - firstPt.getY());
			secondPt.setLocation(secondPt.getX(), 5);
		} else {
			centerY = (firstPt.getY() + secondPt.getY()) / 2.0;
			width = Math.abs(secondPt.getY() - firstPt.getY());
		}
		upperLeft.setLocation(Math.min(firstPt.getX(), secondPt.getX()),
				Math.min(firstPt.getY(), secondPt.getY()));
		rectangle.setX(upperLeft.getX());
		rectangle.setY(upperLeft.getY());
		rectangle.setWidth(length);
		rectangle.setHeight(width);
		rectangle.setStroke(borderColor);
		rectangle.setFill(fillInColor);
		MouseGestures mg = new MouseGestures();
		mg.makeDraggable(rectangle);
		thisRectangle = rectangle;
		shapes.addRectangle(rectangle);
		paint.getChildren().add(rectangle);
	}

	@Override
	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		
	}

}
