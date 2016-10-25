package paintProject;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface IShape {

	/**
	 * get fill color.
	 * @return Color fill in color
	 * */
	public Color getFillInColor();

	public void setFillInColor(Color color);
	
	public Color getBorderColor();

	public void setBorderColor(Color color);
	
	public double getBorderWidth();

	public void setBorderWidth(double borderWidth);
	/**draw shape using the canvas.*/
	public Canvas drawShape(Canvas current, MouseEvent event);
}
