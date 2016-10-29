package tryingJavaFX;

import java.awt.Point;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.Collections;

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
import paintProject.*;

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

	/**
	 * state of the current drawing mode(rectangle, line, free sketching, etc.).
	 */
	private char state;

	/**
	 * Initializes the drawing environment.
	 * 
	 * @param primaryStage
	 *            stage at which all components are appended
	 **/
	public void start(Stage primaryStage) {
		Canvas canvas = new Canvas(700, 800);
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		initDraw(graphicsContext);

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
					actionsCounter[0]++;
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					break;
				}
				case 'r': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
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
						PaintTriangle triangle = new PaintTriangle(befPrevious, previous, last);
						triangle.setBorderColor(colorPicker.getValue());
						triangle.drawShape(paintPane);
						befPrevious = null;
						previous = null;
						actionsCounter[3] = 0;
					}
				}
				default: {
					graphicsContext.beginPath();
					graphicsContext.moveTo(event.getX(), event.getY());
					graphicsContext.stroke();
//					previous = new Point();
//					previous.setLocation(event.getX(), event.getY());
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
					double centerX, centerY, length, width;
					if (event.getX() > canvas.getWidth()) {
						centerX = (previous.getX() + canvas.getWidth() - 3) / 2;
							length = Math.abs(699 - previous.getX());
					} else {
						centerX = (previous.getX() + event.getX()) / 2;
						length = Math.abs(event.getX() - previous.getX());
					} if (event.getY() > canvas.getHeight()) {
						centerY = (previous.getY() + canvas.getHeight() - 3) / 2;
						width = Math.abs(canvas.getHeight() - previous.getY());
						
					} else if (event.getY() < 0){
						centerY = (previous.getY() + 0) / 2;
						width = Math.abs(5 - previous.getY());
					} else {
						centerY = (previous.getY() + event.getY()) / 2;
						width = Math.abs(event.getY() - previous.getY());
					}
					PaintRectangle rectangle;
					rectangle = new PaintRectangle(centerX, centerY, length, width);
					rectangle.setBorderColor(colorPicker.getValue());
					rectangle.drawShape(paintPane);
				} else if (state == 'e') {
					double centerX = 0.0, centerY = 0.0, minor = 0.0, major = 0.0;
					if (event.getX() > canvas.getWidth()) {
						centerX = (previous.getX() + canvas.getWidth()) / 2.0;
					} else {
						centerX = (previous.getX() + event.getX()) / 2.0;
					}
					if (event.getY() <= 0) {
						centerY = (previous.getY() + 0) / 2.0;
					} else if (event.getY() > canvas.getHeight()) {
						centerY = (previous.getY() + canvas.getHeight() - 6.0) / 2.0;
					} else {
						centerY = (previous.getY() + event.getY()) / 2.0;
					}
					minor = Math.abs(previous.getY() - centerY);
					major = Math.abs(previous.getX() - centerX);
					PaintEllipse ellipse;
					ellipse = new PaintEllipse(minor, major, centerX, centerY);
					ellipse.setBorderColor(colorPicker.getValue());
					ellipse.drawShape(paintPane);
					previous = null;
				} else if (state == 'l') {
					if (actionsCounter[0] > 0) {
						PaintLine line;
						Point end = new Point();
						end.setLocation(event.getX(), event.getY());
						line = new PaintLine(previous, end);
						line.drawShape(paintPane);
						clearActions(actionsCounter);
						previous = null;
					}
				}
				else if (state == 'f') {
					graphicsContext.moveTo(event.getX(), event.getY());
				}
			}
		});
		

		Group root = new Group();
		initializeButtons();
		buttonActions();
//		paintPane.setLayoutX(canvas.getLayoutX());
//		paintPane.setLayoutY(canvas.getLayoutY());
//		paintPane.setMaxHeight(canvas.getHeight());
//		paintPane.setMaxWidth(canvas.getWidth());
		paintPane.getChildren().add(canvas);
		HBox hBox = new HBox();
		hBox.getChildren().add(colorPicker);
		hBox.getChildren().addAll(free, line, ellipse, rectangle, triangle);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox, paintPane);
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
		// rectangle.setLayoutY(100);
		// line.setLayoutY(100);line.setLayoutX(50);
		// ellipse.setLayoutY(100);ellipse.setLayoutX(100);
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
	 * 
	 * @args needed of called from outside
	 */
	public static void main(String[] args) {
		launch(args);
	}

}