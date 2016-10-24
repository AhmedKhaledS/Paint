package paintProject;

import javafx.scene.paint.Color;

abstract public class Shape implements IShape{

	private Color fillInColor;
	private Color BorderColor;
	private double borderWidth;
	@Override
	abstract public Color getFillInColor();

	@Override
	abstract public void setFillInColor(Color color);

	@Override
	abstract public Color getBorderColor();

	@Override
	abstract public void setBorderColor(Color color);

	@Override
	abstract public double getBorderWidth();

	@Override
	abstract public void setBorderWidth(double borderWidth);

}
