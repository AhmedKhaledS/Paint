package paintProject;

import java.awt.Point;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PaintTriangle extends PaintPolygon{

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private Point point1;
	private Point point2;
	private Point point3;

	public PaintTriangle(Point p1, Point p2,Point p3) {
		point1 = p1;
		point2 = p2;
		point3 = p3;
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

	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		Polygon triangle =  new Polygon();
		triangle.getPoints().addAll(new Double[]{
			    point1.getX(), point1.getY(),
			    point2.getX(), point2.getY(),
			    point3.getX(), point3.getY() });
		triangle.setStroke(borderColor);
		triangle.setFill(Color.WHITE);
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(triangle);
		paint.getChildren().add(triangle);
		return;
	}

	

}
