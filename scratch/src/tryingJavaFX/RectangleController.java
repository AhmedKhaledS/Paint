package tryingJavaFX;

import java.awt.Point;

import ShapeModels.RectangleModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

public class RectangleController {
	
	private Point firstPt;
	private Point lastPoint;

	public Point getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(Point lastPoint) {
		this.lastPoint = lastPoint;
	}

	public Point getFirstPt() {
		return firstPt;
	}

	public void setFirstPt(Point firstPt) {
		this.firstPt = firstPt;
	}
	
	public void drawRectangle(Pane pntPane, Canvas cvs, ColorPicker clrPkr, Data shapes) {
		RectangleModel rectangle;
		rectangle = new RectangleModel(firstPt, lastPoint);
		rectangle.setBorderColor(clrPkr.getValue());
		rectangle.drawRect(pntPane, cvs, shapes);
	}
	

}
