package paintProject;

import javafx.scene.paint.Color;

public class Triangle extends Polygon{

	private Color fillInColor;
	private Color borderColor;
	private double borderWidth;
	private double side1;
	private double side2;
	private double side3;
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

	public void setSides(double s1, double s2, double s3) {
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}

	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}

	public double getSide3() {
		return side3;
	}

	

}
