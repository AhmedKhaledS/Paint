package ShapeModels;

import java.awt.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineJoin;
import tryingJavaFX.Data;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class LineModel extends PolygonModel{

	private double length;
	private double width;
	private Point start;
	private Point end;
	private Color color;
	
	public LineModel(Point start, Point end) {
		this.start = start;
		this.end = end;
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
	 * */
	public void drawShape(Pane paint, Data shapes) {
		Line line = new Line();
		line.setStartX(start.getX());
		line.setStartY(start.getY());
		// canvas boundaries..
		if (end.getX() > 699) {
			line.setEndX(699);
		} else {
			line.setEndX(end.getX());			
		}
		int len = (int)paint.getLayoutY();
		if (end.getY() < 5) {
			line.setEndY(5);
		} else if (end.getY() > 799) {
			line.setEndY(799);
		} else {
			line.setEndY(end.getY());
		}
		MouseGestures drag = new MouseGestures();
		drag.makeDraggable(line);
		paint.getChildren().add(line);
		shapes.addLine(line);
		return;
	}

	@Override
	public void drawShape(Pane paint) {
		// TODO Auto-generated method stub
		
	}
}
