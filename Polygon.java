package paintProject;

import javafx.scene.paint.Color;

abstract public class Polygon extends Shape{



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
