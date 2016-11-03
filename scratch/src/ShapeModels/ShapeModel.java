package ShapeModels;

import Controllers.Data;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jsonShapesProperties.JSONData;

public interface ShapeModel extends Cloneable {
	/**
	 * returns the border color.
	 * @return the border color
	 * */
	public Color getBorderColor();

	/**
	 * sets the border color.
	 * @param color to be set as a border color
	 * */
	public void setBorderColor(Color color);

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	public double getBorderWidth();

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	public void setBorderWidth(double borderWidth);

	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * */
	public void drawShape(Pane paint, Canvas canvas, Data shapes, JSONData json);
}
