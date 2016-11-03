package Controllers;

import java.awt.Point;

import ShapeModels.EllipseModel;
import jsonShapesProperties.JSONData;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class EllipseController {
	private Point startPoint;
	private Point endPoint;
	
	public void setDimensions(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	public void draw(Pane pntPane, Canvas cvs, ColorPicker colorPicker, Data shapes, JSONData json) {
		EllipseModel ellipse;
		ellipse = new EllipseModel(startPoint, endPoint);
		ellipse.setBorderColor(colorPicker.getValue());
		ellipse.drawShape(pntPane, cvs, shapes, json);
	}
}
