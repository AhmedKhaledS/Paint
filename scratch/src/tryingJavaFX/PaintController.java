package tryingJavaFX;

import java.awt.Point;
import java.awt.Stroke;
import java.util.Arrays;
import java.util.Collections;

import ShapeModels.LineModel;
import ShapeModels.RectangleModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
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
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;

public class PaintController {
	
	/** Color Picker to choose colors from. */
	@FXML
	private ColorPicker colorPicker;
	/** Button to insert a new Rectangle. */
	@FXML
	private Button rectangle;
	/** Button to insert a new Line. */
	@FXML
	private Button line;
	/** Button to do free sketching. */
	@FXML
	private Button free;
	/** Button to insert a new Ellipse. */
	@FXML
	private Button ellipse;
	/** Button to perform Dynamic Class Loading. */
	@FXML
	private Button dynamicLoad;

	private Point previous;
	
	private int[] actionsCounter;

	private final int NoOfButtons = 5;
	/**
	 * state of the current drawing mode(rectangle, line, free sketching, etc.).
	 */
	private char state;
	
	@FXML
	private Canvas canvas;
	@FXML
	private Pane paintPane;
	public void start(Stage primaryStage,Parent root, Scene scene) {
		
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		initDraw(graphicsContext);

		actionsCounter = new int[NoOfButtons];
		clearActions(actionsCounter);
		
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
						LineModel currentLine = new LineModel(previous, current);
						currentLine.drawShape(paintPane);
						clearActions(actionsCounter);
					} else {
						previous = new Point();
						previous.setLocation(event.getX(), event.getY());
					}
					break;
				}
				case 'r': {
					break;
				}
				case 'e': {
					actionsCounter[2]++;
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
					RectangleModel rectangle;
					rectangle = new RectangleModel(centerX, centerY, height, width);
					rectangle.setBorderColor(colorPicker.getValue());
					rectangle.drawShape(paintPane);
					previous = null;
				}
			}
		});

//		Group root = new Group();
//		initializeButtons();
//		buttonActions();
//		paintPane.getChildren().add(canvas);
//		HBox hBox = new HBox();
//		hBox.getChildren().add(colorPicker);
//		hBox.getChildren().addAll(free, line, ellipse, rectangle);
//		VBox vBox = new VBox();
//		vBox.getChildren().addAll(hBox, paintPane);
//		root.getChildren().addAll(vBox);
//		scene = new Scene(root, 700, 725);
		primaryStage.setTitle("Vector Drawing");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
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
	public void freeOnAcion(ActionEvent event) {
		state = 'f';
		clearActions(actionsCounter);
	}
	public void lineOnAcion(ActionEvent event) {
		state = 'l';
		clearActions(actionsCounter);
	}
	public void rectangleOnAcion(ActionEvent event) {
		clearActions(actionsCounter);
		previous = null;
	}
	public void EllipseOnAcion(ActionEvent event) {
		state = 'e';
		clearActions(actionsCounter);
	}
		
	private void initDraw(GraphicsContext gc) {
		//colorPicker = new ColorPicker();
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
}
