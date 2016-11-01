package ShapeModels;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

abstract public class PolygonModel implements ShapeModel {

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
