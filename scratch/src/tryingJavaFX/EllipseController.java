package tryingJavaFX;

import java.awt.Point;

import ShapeModels.EllipseModel;
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
	public void draw(Pane pntPane, Canvas cvs, ColorPicker colorPicker) {
		EllipseModel ellipse;
		ellipse = new EllipseModel(startPoint, endPoint);
		ellipse.setBorderColor(colorPicker.getValue());
		ellipse.drawEllipse(pntPane, cvs);
	}
}
