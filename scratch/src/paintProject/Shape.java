package paintProject;

import javafx.scene.paint.Color;

abstract public class Shape implements IShape{

	//Will be checked yet.	
	private Color fillInColor;
	private Color BorderColor;
	private double borderWidth;
	@Override
	abstract public Color getFillInColor();

	/**
	 * sets the fill-in color of the shape.
	 * @param color the color to be set
	 * */
	@Override
	abstract public void setFillInColor(Color color);

	/**
	 * returns the border color.
	 * @return the border color
	 * */
	@Override
	abstract public Color getBorderColor();

	/**
	 * sets the border color.
	 * @param color to be set as a border color
	 * */
	@Override
	abstract public void setBorderColor(Color color);

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	@Override
	abstract public double getBorderWidth();

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	@Override
	abstract public void setBorderWidth(double borderWidth);

}
