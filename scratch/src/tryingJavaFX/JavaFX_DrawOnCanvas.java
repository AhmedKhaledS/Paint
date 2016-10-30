package tryingJavaFX;

import java.awt.Point;
import java.util.Arrays;

import ShapeModels.EllipseModel;
import ShapeModels.LineModel;
import ShapeModels.RectangleModel;
import ShapeModels.TriangleModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ShapeModels.*;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_DrawOnCanvas extends Application {

	/** Color Picker to choose colors from. */
	private ColorPicker colorPicker;
	/** Button to insert a new Rectangle. */
	private Button rectangle;
	/** Button to insert a new Line. */
	private Button line;
	/** Button to do free sketching. */
	private Button free;
	/** Button to insert a new Ellipse. */
	private Button ellipse;
	/** Button to insert a new Triangle. */
	private Button triangle;
	/** Button to perform Dynamic Class Loading. */
	private Button dynamicLoad;
	/** previous coordinates of the mouse on the canvas. */
	private Point previous;
	/** point before the previous coordinates of the mouse on the canvas. */
	private Point befPrevious;
	/** array of counters of clicks made in each mode. */
	private int[] actionsCounter;
	/** number of buttons available. */
	private final int NoOfButtons = 5;
	/** state of the current drawing mode(rectangle, line, free sketching, etc.). */
	private char state;
	/**
	 * Initializes the drawing environment.
	 * @param primaryStage
	 * stage at which all components are appended
	 **/
	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(700, 800);
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		initDraw(graphicsContext);
		LineController lineCtrl = new LineController();
		RectangleController rectangleCtrl = new RectangleController();
		EllipseController ellipseCtrl = new EllipseController();
		TriangleController triangleCtrl = new TriangleController();
		actionsCounter = new int[NoOfButtons];
		clearActions(actionsCounter);
		// Initially as a line segment.
		Pane paintPane = new Pane();
		state = 'f';
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch (state) {
				case 'l': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					lineCtrl.setPreviousPoint(previous);
					break;
				}
				case 'r': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					rectangleCtrl.setFirstPt(previous);
					break;
				}
				case 'e': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					break;
				}
				case 't': {
					actionsCounter[3]++;
					if (actionsCounter[3] == 1) {
						befPrevious = new Point();
						befPrevious.setLocation(event.getX(), event.getY());
					} else if (actionsCounter[3] == 2) {
						previous = new Point();
						previous.setLocation(event.getX(), event.getY());
					} else {
						Point last = new Point();
						last.setLocation(event.getX(), event.getY());
						triangleCtrl.setDimensions(befPrevious,  previous, last);
						triangleCtrl.draw(paintPane, colorPicker);
						
						befPrevious = null;
						previous = null;
						actionsCounter[3] = 0;
					}
				}
				default: {
					graphicsContext.beginPath();
					graphicsContext.moveTo(event.getX(), event.getY());
					graphicsContext.stroke();
					break;
				}
				}
			}
		});

		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// free sketching mode only while free state.
				if (state == 'f') {
					graphicsContext.lineTo(event.getX(), event.getY());
					graphicsContext.stroke();
				} else if (state == 't') {
					if (befPrevious == null) {
						befPrevious = new Point();
						befPrevious.setLocation(event.getX(), event.getY());
					} else if (previous == null) {
						previous = new Point();
						previous.setLocation(event.getX(), event.getY());
					}
				}
			}
		});

		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (state == 'r') {
					Point current = new Point();
					current.setLocation(event.getX(), event.getY());
					rectangleCtrl.setLastPoint(current);
					rectangleCtrl.drawRectangle(paintPane, canvas, colorPicker);
				} else if (state == 'e') {
					Point current = new Point();
					current.setLocation(event.getX(), event.getY());
					ellipseCtrl.setDimensions(previous, current);
					ellipseCtrl.draw(paintPane, canvas, colorPicker);
					previous = null;
				} else if (state == 'l') {
					Point end = new Point();
					end.setLocation(event.getX(), event.getY());
					lineCtrl.setEndPoint(end);
					lineCtrl.drawLine(paintPane);
					previous = null;
				}
				else if (state == 'f') {
					graphicsContext.moveTo(event.getX(), event.getY());
				}
			}
		});
		setCanvas(canvas, paintPane, primaryStage);
	}
	
	
	private void setCanvas(Canvas cvs, Pane pntPne, Stage primaryStage) {
		Group root = new Group();
		initializeButtons();
		buttonActions();
		pntPne.getChildren().add(cvs);
		HBox hBox = new HBox();
		hBox.getChildren().add(colorPicker);
		hBox.getChildren().addAll(free, line, ellipse, rectangle, triangle);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox, pntPne);
		root.getChildren().addAll(vBox);
		Scene scene = new Scene(root, 700, 725);
		primaryStage.setTitle("Vector Drawing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * Clears the actions array.
	 * 
	 * @param array
	 * the array of actions to be cleared.
	 */
	private void clearActions(int[] array) {
		Arrays.fill(array, 0);
	}

	/**
	 * Initializes all buttons on the drawing pane.
	 */
	public void initializeButtons() {
		rectangle = new Button("Rectangle");
		line = new Button("Line");
		ellipse = new Button("Ellipse");
		free = new Button("Free");
		triangle = new Button("triangle");
		Image rec = new Image("file:rect.png");
	}

	/**
	 * sets action handlers for buttons.
	 */
	public void buttonActions() {
		free.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'f';
				clearActions(actionsCounter);
			}
		});
		line.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'l';
				clearActions(actionsCounter);
				previous = null;
			}
		});
		rectangle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'r';
				clearActions(actionsCounter);
				previous = null;
			}
		});
		ellipse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'e';
				clearActions(actionsCounter);
				previous = null;
			}
		});
		triangle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 't';
				clearActions(actionsCounter);
				previous = null;
			}
		});
	}

	/**
	 * sets the Color picker and the default colors for sketching.
	 * 
	 * @param gc
	 *            the graphics content of the Scene
	 */
	private void initDraw(GraphicsContext gc) {
		colorPicker = new ColorPicker();
		colorPicker.setValue(Color.BLACK);
		double canvasWidth = gc.getCanvas().getWidth();
		double canvasHeight = gc.getCanvas().getHeight();
		colorPicker.setOnAction(new EventHandler() {
			public void handle(Event t) {
				gc.setStroke(colorPicker.getValue());
			}
		});
		gc.setLineWidth(5);

		gc.fill();
		gc.strokeRect(0, // x of the upper left corner
				0, // y of the upper left corner
				canvasWidth, // width of the rectangle
				canvasHeight); // height of the rectangle

		gc.setFill(colorPicker.getValue());
		gc.setStroke(colorPicker.getValue());
		gc.setLineWidth(1);
	}

	/**
	 * main method.
	 * @args needed of called from outside
	 */
	public static void main(String[] args) {
		launch(args);
	}

}