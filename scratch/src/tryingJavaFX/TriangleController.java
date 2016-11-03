package tryingJavaFX;

import java.awt.Point;

import jsonShapesProperties.JSONData;
import ShapeModels.TriangleModel;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class TriangleController {
	Point firstPoint, secondPoint, thirdPoint;
	
	public void setDimensions(Point firstPoint, Point secondPoint, Point thirdPoint) {
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
		this.thirdPoint = thirdPoint;
	}
	public void draw(Pane pntPane,ColorPicker colorPicker, Data shapes, JSONData json) {
		TriangleModel triangle = new TriangleModel(firstPoint, secondPoint, thirdPoint);
		triangle.setBorderColor(colorPicker.getValue());
		triangle.drawShape(pntPane, shapes, json);
	}
}
