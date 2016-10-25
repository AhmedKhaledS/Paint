package paintProject;

import java.awt.Point;

import javafx.scene.paint.Color;

public class Line extends Polygon{

	private double length;
	private double width;
	private Point start;
	private Point end;
	private Color color;
	@Override
	public Color getFillInColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setFillInColor(Color color) {
		// No area to be painted
		
	}
	@Override
	public Color getBorderColor() {
		// TODO Auto-generated method stub
		return color;
	}
	@Override
	public void setBorderColor(Color color) {
		// TODO Auto-generated method stub
		color = this.color;
	}
	@Override
	public double getBorderWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	@Override
	public void setBorderWidth(double borderWidth) {
		// TODO Auto-generated method stub
		width = borderWidth;
	}
}
