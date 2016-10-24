package paintProject;

import javafx.scene.paint.Color;

public interface IShape {

	public Color getFillInColor();

	public void setFillInColor(Color color);
	
	public Color getBorderColor();

	public void setBorderColor(Color color);
	
	public double getBorderWidth();

	public void setBorderWidth(double borderWidth);
}
