package ShapeModels;

import java.awt.Point;
import java.awt.geom.Point2D;

import Controllers.Data;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jsonShapesProperties.JSONData;

public class SquareModel extends RectangleModel {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double side;
	private Point firstPt, secondPt;

	public SquareModel(Point firstPt, Point secondPt) {
		super(firstPt, secondPt);
		this.firstPt = firstPt;
		this.secondPt = secondPt;
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
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
	public void drawShape(Pane paint, Canvas canvas, Data shapes, JSONData json) {
		super.drawShape(paint, canvas, shapes, json);
		return;
	}
}
