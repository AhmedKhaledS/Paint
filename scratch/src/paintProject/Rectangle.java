package paintProject;

import java.awt.geom.Point2D;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Rectangle extends Polygon{

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	
	private double length;
	private double width;
	private Point2D.Double center;
	
	public Rectangle(double xCent, double yCent, double len, double wid) {
		center = new Point2D.Double();
		center.setLocation(xCent, yCent);
		length = len;
		width = wid;
	}

	public Color getFillInColor() {
		// TODO Auto-generated method stub
		return fillInColor;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
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
