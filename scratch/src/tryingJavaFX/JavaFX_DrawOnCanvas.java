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
import paintProject.PaintLine;
import paintProject.PaintRectangle;

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
	/** Button to perform Dynamic Class Loading. */
	private Button dynamicLoad;

	private Point previous;

	private Point befPrevious;

	private int[] actionsCounter;

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
		Canvas canvas = new Canvas(700, 700);
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
					if (actionsCounter[0] > 1) {
						Point current = new Point();
						current.setLocation(event.getX(), event.getY());
						PaintLine currentLine = new PaintLine(previous, current);
						currentLine.drawShape(paintPane);
						clearActions(actionsCounter);
					} else {
						previous = new Point();
						previous.setLocation(event.getX(), event.getY());
					}
					break;
				}
				case 'r': {
//					actionsCounter[1]++;
//					if (actionsCounter[1] > 2) {
//						graphicsContext.beginPath();
//						graphicsContext.moveTo(previous.getX(), previous.getY());
//						graphicsContext.lineTo(event.getX(), event.getY());
//						graphicsContext.stroke();
//						clearActions(actionsCounter);
//					} else if (actionsCounter[1] > 1) {
//						previous = new Point();
//						previous.setLocation(event.getX(), event.getY());
//						graphicsContext.beginPath();
//						graphicsContext.moveTo(befPrevious.getX(), befPrevious.getY());
//						graphicsContext.lineTo(previous.getX(), previous.getY());
//						graphicsContext.stroke();
//					} else {
//						befPrevious = new Point();
//						befPrevious.setLocation(event.getX(), event.getY());
//					}
//					break;
					break;
				}
				case 'e': {
					actionsCounter[2]++;
					break;
				}
				case 'f': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					break;
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
				} else if (state == 'r') {
					if (previous == null) {
						previous = new Point();
						previous.setLocation(event.getX(), event.getY());
					} 
//					else {
//						graphicsContext.setLineDashes(5);
//						graphicsContext.lineTo(previous.getX(), event.getY());
//						graphicsContext.stroke();
//						graphicsContext.moveTo(previous.getX(), previous.getY());
//						graphicsContext.lineTo(previous.getY(), event.getX());
//						graphicsContext.stroke();
//						graphicsContext.moveTo(previous.getX(), previous.getY());
//					}
				}
			}
		});

		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (state == 'r') {
					double centerX = (previous.getX() + event.getX()) / 2;
					double centerY = (previous.getY() + event.getY()) / 2;
					double width = Math.abs(event.getX() - previous.getX());
					double height = Math.abs(event.getY() - previous.getY());
					PaintRectangle rectangle;
					rectangle = new PaintRectangle(centerX, centerY, height, width);
					rectangle.setBorderColor(colorPicker.getValue());
					rectangle.drawShape(paintPane);
					previous = null;
				}
			}
		});

		Group root = new Group();
		initializeButtons();
		buttonActions();
		paintPane.getChildren().add(canvas);
		HBox hBox = new HBox();
		hBox.getChildren().add(colorPicker);
		hBox.getChildren().addAll(free, line, ellipse, rectangle);
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
	 *            the array of actions to be cleared
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