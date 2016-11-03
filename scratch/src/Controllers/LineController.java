package Controllers;

import java.awt.Point;

import jsonShapesProperties.JSONData;
import ShapeModels.LineModel;
import javafx.scene.canvas.Canvas;
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
	
	public void drawLine(Pane paintPane, Canvas canvas, Data shapes, JSONData json) {
		LineModel line = new LineModel(startPoint, endPoint);
		line.drawShape(paintPane, canvas, shapes, json);
	}
}
