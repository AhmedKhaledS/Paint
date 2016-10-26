package paintProject;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Triangle extends Polygon{

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double side1;
	private double side2;
	private double side3;

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

	public void setSides(double s1, double s2, double s3) {
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}

	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}

	public double getSide3() {
		return side3;
	}

	@Override
	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		return;
	}

	

}
