package ShapeModels;

import java.awt.Point;

import Controllers.Data;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineJoin;
import jsonShapesProperties.JSONData;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class LineModel extends PolygonModel implements Cloneable{

	private double length;
	private double width;
	private Point start;
	private Point end;
	private Color color;
	private Line thisLine;
	
	public LineModel(Point start, Point end) {
		this.start = start;
		this.end = end;
		Line line = new Line();
		line.setStartX(start.getX());
		line.setStartY(start.getY());
		// canvas boundaries..
		if (end.getX() > 699) {
			line.setEndX(699);
		} else {
			line.setEndX(end.getX());			
		}
		if (end.getY() < 5) {
			line.setEndY(5);
		} else if (end.getY() > 799) {
			line.setEndY(799);
		} else {
			line.setEndY(end.getY());
		}
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(line);
		thisLine = line;
	}

	/**
	 * returns the border color.
	 * @return the border color
	 * */
	@Override
	public Color getBorderColor() {
		return color;
	}
	@Override
	public void setBorderColor(Color color) {
		color = this.color;
	}

	/**
	 * gets the border width value.
	 * @return double value indicating the width color
	 * */
	@Override
	public double getBorderWidth() {
		return width;
	}

	/**
	 * sets the border width with the given value.
	 * @param borderWidth the value to be set as border width
	 * */
	@Override
	public void setBorderWidth(double borderWidth) {
		width = borderWidth;
	}
	
	public Line getClone() throws CloneNotSupportedException {
		Line clone = new Line();
		clone.setStartX(thisLine.getStartX());
		clone.setStartY(thisLine.getStartY());
		clone.setEndX(thisLine.getEndX());
		clone.setEndY(thisLine.getEndY());
		return clone;	
	}
	/**
	 * draw shape using the canvas.
	 * @param current the canvas to which painted materials will be appended
	 * */
	@Override
	public void drawShape(Pane paint, Canvas canvas, Data shapes, JSONData json) {
		Line line = new Line();
		line.setStartX(start.getX());
		line.setStartY(start.getY());
		// canvas boundaries..
		line.setEndX(end.getX());			
		if (end.getY() < 5) {
			line.setEndY(5);
		}
		line.setEndY(end.getY());
		thisLine = line;
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(line);
		paint.getChildren().add(line);
		json.addLine(thisLine);
		shapes.addLine(thisLine);
		return;
	}

}
