package paintProject;

import java.awt.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Line extends Polygon{

	private double length;
	private double width;
	private Point start;
	private Point end;
	private Color color;

	/**
	 * get fill color.
	 * @return Color fill in color
	 * */
	@Override
	public Color getFillInColor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * sets the fill-in color of the shape.
	 * @param color the color to be set
	 * */
	@Override
	public void setFillInColor(Color color) {
		// No area to be painted
		
	}

	/**
	 * returns the border color.
	 * @return the border color
	 * */
	@Override
	public Color getBorderColor() {
		// TODO Auto-generated method stub
		return color;
	}
	@Override
	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		color = this.color;
	}

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	@Override
	public double getBorderWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	@Override
	public void setBorderWidth(double borderWidth) {
		// TODO Auto-generated method stub
		width = borderWidth;
	}

	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * @param event the mouse event that called the draw method
	 * */
	@Override
	public Canvas drawShape(Canvas current, MouseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}
}
