package paintProject;

import javafx.scene.paint.Color;

public class Circle extends Ellipse{


	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	private double radius;

	public Circle(double radius, double xCent, double yCent) {
		super(radius * 2, radius * 2, xCent, yCent);
	}
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
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
