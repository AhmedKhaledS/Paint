package paintProject;

import java.awt.Point;
import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Ellipse extends Shape {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double majorAxis;
	private double minorAxis;
	private Point2D.Double center;

	public Ellipse(double mjrAxis, double mnrAxis, double xCent, double yCent) {
		majorAxis = mjrAxis;
		minorAxis = mnrAxis;
		center = new Point2D.Double();
		center.setLocation(xCent, yCent);
	}

	public double getMinorAxis() {
		return minorAxis;
	}

	public void setMinorAxis(double minorAxis) {
		this.minorAxis = minorAxis;
	}

	public double getMajorAxis() {
		return majorAxis;
	}

	public void setMajorAxis(double majorAxis) {
		this.majorAxis = majorAxis;
	}

	
	public Color getFillInColor() {
		// TODO Auto-generated method stub
		return fillInColor;
	}

	public void setFillInColor(Color color) {
		fillInColor = color;
	}


	public Color getBorderColor() {
		// TODO Auto-generated method stub
		return borderColor;
	}

	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		borderColor = color;
	}

	public double getBorderWidth() {
		// TODO Auto-generated method stub
		return borderWidth;
	}

	public void setBorderWidth(double borderWidth) {
		// TODO Auto-generated method stub
		borderWidth = this.borderWidth;
	}

	@Override
	public Canvas drawShape(Canvas current, MouseEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
