package tryingJavaFX;

import java.awt.Point;

import ShapeModels.LineModel;
import javafx.scene.layout.Pane;

public class LineController {
	
	private Point startPoint;
	private Point endPoint;
	
	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public Point getPreviousPoint() {
		return startPoint;
	}

	public void setPreviousPoint(Point previousPoint) {
		this.startPoint = previousPoint;
	}
	
	public void drawLine(Pane paintPane, Data shapes) {
		LineModel line = new LineModel(startPoint, endPoint);
		line.drawShape(paintPane, shapes);
	}
}
