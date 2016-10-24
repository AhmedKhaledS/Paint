package paintProject;

import java.awt.geom.Point2D;

import javafx.scene.paint.Color;

public class Square extends Rectangle {

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	private double side;
	private Point2D.Double center;

	public Square(double xCent, double yCent, double s) {
		super(xCent, yCent, s, s);
		center = new Point2D.Double();
		center.setLocation(xCent, yCent);
		side = s;
	}

	public double getSide() {
		return side;
	}

	public void setSide(double side) {
		this.side = side;
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
}
