package tryingJavaFX;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;

@XmlRootElement
public class Tools {
	@XmlElementWrapper(name ="toolsss")
	@XmlElement(type=Tools.class, name="book")
	private Canvas cvs;
	private Pane pane;
	private ColorPicker colorPicker;
	Tools() {
		cvs = new Canvas(700, 600);
		pane = new Pane();
		colorPicker = new ColorPicker();
	}
	
	public Canvas getCvs() {
		return cvs;
	}
	public Pane getPane() {
		return pane;
	}
	public ColorPicker getColorPicker() {
		return colorPicker;
	}
}
