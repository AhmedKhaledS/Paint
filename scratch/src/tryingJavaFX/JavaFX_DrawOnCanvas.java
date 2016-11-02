package tryingJavaFX;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.JOptionPane;

//import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ShapeModels.*;

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
	/** Button to save current architecture */
	private Button save;
	/** Button to load saved architecture */
	private Button load;
	/** Button to perform Dynamic Class Loading. */
	private Button dynamicLoad;
	/** Button to delete Shapes. */
	private Button delete;
	/** Button to undp.*/
	private Button Undo;
	/** Button for redo. */
	private Button Redo;
	/** previous coordinates of the mouse on the canvas. */
	private Point previous;
	/** point before the previous coordinates of the mouse on the canvas. */
	private Point befPrevious;
	/** array of counters of clicks made in each mode. */
	private int[] actionsCounter;
	/** Stack for normal operations.*/
	private Stack<Data> redo;
	/** Stack for undo operations. */
	private Stack<Data> undo;
	/** number of buttons available. */
	private final int NoOfButtons = 5;
	private boolean operationAfterUndo;
	private boolean undoPressed;
	private boolean operationAfterRedo;
	private boolean redoPressed;
	public boolean deletePressed;

	/** Whole data*/
	private Data shapes;
	/**
	 * state of the current drawing mode(rectangle, line, free sketching, etc.).
	 */
	private char state;
	/**preview rectangle upper left X coordinate.*/
	private SimpleDoubleProperty rectX;
	/**preview rectangle upper left Y coordinate.*/
	private SimpleDoubleProperty rectY;
	/**preview line start point x coordinate.*/
	private SimpleDoubleProperty linefx;
	/**preview line start point y coordinate.*/
	private SimpleDoubleProperty linefy;
	/**preview line end point x coordinate.*/
	private SimpleDoubleProperty linesx;
	/**preview line end point y coordinate.*/
	private SimpleDoubleProperty linesy;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty firstX, firstRX;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty firstY, firstRY;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty secondX, secondRX;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty secondY, secondRY;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty width, widthR;
	/**preview shapes movable values.*/
	private SimpleDoubleProperty length, lengthR;
	/**preview rectangle.*/
	private Rectangle previewRect;
	/**preview line.*/
	private Line previewLine;
	/**preview ellipse.*/
	private Ellipse previewEllipse;
	/** required for I/O operations.*/
	private DataManipulation data;
	/** Pane to draw shapes on.*/
	private Pane paintPane;
	/** Canvas to draw on. */
	private Canvas canvas;
	private Tools base;
	/**
	 * Initializes the drawing environment.
	 * 
	 * @param primaryStage
	 *            stage at which all components are appended
	 **/
	public void start(Stage primaryStage) {
		try {
			Class.forName("tryingJavaFX.Data");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		redo = new Stack<Data>();
		undo = new Stack<Data>();
		shapes = new Data();
		base = new Tools();
		canvas = base.getCvs();
		operationAfterUndo = false;
		undoPressed = false;
		redoPressed = false;
		operationAfterRedo = false;
		final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		paintPane = base.getPane();
		colorPicker = base.getColorPicker();
		initDraw(graphicsContext);
		LineController lineCtrl = new LineController();
		RectangleController rectangleCtrl = new RectangleController();
		EllipseController ellipseCtrl = new EllipseController();
		TriangleController triangleCtrl = new TriangleController();
		actionsCounter = new int[NoOfButtons];
		clearActions(actionsCounter);
		// Initially as a line segment.
		state = 'f';
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch (state) {
				case 'l': {
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					lineCtrl.setPreviousPoint(previous);
					linefx.setValue(event.getX());
					linefy.setValue(event.getY());
					linesx.setValue(event.getX());
					linesy.setValue(event.getY());
					break;
				}
				case 'r': {
					firstRX.setValue(event.getX());
					firstRY.setValue(event.getY());
					secondRX.setValue(event.getX());
					secondRY.setValue(event.getY());
					previous = new Point();
					previous.setLocation(event.getX(), event.getY());
					rectangleCtrl.setFirstPt(previous);
					rectX.setValue(event.getX());
					rectY.setValue(event.getY());
					break;
				}
				case 'e': {
					firstX.setValue(event.getX());
					firstY.setValue(event.getY());
					secondX.setValue(event.getX());
					secondY.setValue(event.getY());
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
						triangleCtrl.setDimensions(befPrevious, previous, last);
						triangleCtrl.draw(paintPane, colorPicker, shapes);
						try {
							redo.push(shapes.clone());
							////////////
							if (undoPressed) {
//								undo.clear();
								undoPressed = false;
							}
							if (redoPressed) {
								redoPressed = false;
							}
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						befPrevious = null;
						previous = null;
						actionsCounter[3] = 0;
					}
				}
				case 'd': {
					if (event.getSource() instanceof Rectangle) {
						paintPane.getChildren().remove((Node) event.getSource());
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
				} else if (state == 'l') {
					linesx.setValue(event.getX());
					linesy.setValue(event.getY());
				} else if (state == 'r') {
					secondRX.setValue(event.getX());
					secondRY.setValue(event.getY());
					if (secondRX.doubleValue() < 0) {
						secondRX.setValue(0);
					}
					if (secondRY.doubleValue() < 0) {
						secondRY.setValue(0);
					}
					rectX.setValue(Math.min(firstRX.doubleValue(), secondRX.doubleValue()));
					rectY.setValue(Math.min(firstRY.doubleValue(), secondRY.doubleValue()));
					widthR.setValue(Math.abs(firstRX.doubleValue() - secondRX.doubleValue()));
					lengthR.setValue(Math.abs(firstRY.doubleValue() - secondRY.doubleValue()));
				} else if (state == 'e') {
					secondX.setValue(event.getX());
					secondY.setValue(event.getY());
					if (secondX.doubleValue() < 0) {
						secondX.setValue(0);
					}
					if (secondY.doubleValue() < 0) {
						secondY.setValue(0);
					}
					width.setValue(Math.abs(secondX.doubleValue() - firstX.doubleValue()));
					length.setValue(Math.abs(secondY.doubleValue() - firstY.doubleValue()));
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
					rectangleCtrl.drawRectangle(paintPane, canvas, colorPicker, shapes);
					try {
						redo.push(shapes.clone());
						if (undoPressed) {
//							undo.clear();
							undoPressed = false;
						}
						if (redoPressed) {
							
							redoPressed = false;
						}
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					firstRX.setValue(0);
					firstRY.setValue(0);
					secondRX.setValue(0);
					secondRY.setValue(0);
					widthR.setValue(0);
					lengthR.setValue(0);
				} else if (state == 'e') {
					Point current = new Point();
					current.setLocation(event.getX(), event.getY());
					ellipseCtrl.setDimensions(previous, current);
					ellipseCtrl.draw(paintPane, canvas, colorPicker, shapes);
					try {
						redo.push(shapes.clone());
						if (undoPressed) {
//							undo.clear();
							undoPressed = false;
						}
						if (redoPressed) {
							redoPressed = false;
						}
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					firstX.setValue(0);
					firstY.setValue(0);
					secondX.setValue(0);
					secondY.setValue(0);
					width.setValue(0);
					length.setValue(0);
					previous = null;
				} else if (state == 'l') {
					Point end = new Point();
					end.setLocation(event.getX(), event.getY());
					lineCtrl.setEndPoint(end);
					lineCtrl.drawLine(paintPane, shapes);
					// update new line in our state(stack).
					try {
						redo.push(shapes.clone());
						if (undoPressed) {
//							undo.clear();
							undoPressed = false;
						}
						if (redoPressed) {
							redoPressed = false;
						}
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
//					System.out.println(redo);
					firstX.setValue(0);
					firstY.setValue(0);
					secondX.setValue(0);
					secondY.setValue(0);
					width.setValue(0);
					length.setValue(0);
					linefx.setValue(0);
					linefy.setValue(0);
					linesx.setValue(0);
					linesy.setValue(0);
					previous = null;
				} else if (state == 'f') {
					graphicsContext.moveTo(event.getX(), event.getY());
				} else if (state == 'd') {
					if (event.getSource() instanceof Rectangle) {
						paintPane.getChildren().remove((Node) event.getSource());
					}
				}
			}
		});
		construct();
		setCanvas(canvas, paintPane, primaryStage);
		paintPane.getChildren().add(previewLine);
		paintPane.getChildren().add(previewRect);
		paintPane.getChildren().add(previewEllipse);
	}

	/**
	 * sets the preview tools.
	 */
	private void construct() {
		firstX = new SimpleDoubleProperty();
		firstY = new SimpleDoubleProperty();
		secondX = new SimpleDoubleProperty();
		secondY = new SimpleDoubleProperty();
		width = new SimpleDoubleProperty();
		length = new SimpleDoubleProperty();
		firstRX = new SimpleDoubleProperty();
		firstRY = new SimpleDoubleProperty();
		secondRX = new SimpleDoubleProperty();
		secondRY = new SimpleDoubleProperty();
		widthR = new SimpleDoubleProperty();
		lengthR = new SimpleDoubleProperty();
		linefy = new SimpleDoubleProperty();
		linefx = new SimpleDoubleProperty();
		linesx = new SimpleDoubleProperty();
		linesy = new SimpleDoubleProperty();
		rectX = new SimpleDoubleProperty();
		rectY = new SimpleDoubleProperty();
		firstX.setValue(0);
		linefx.setValue(0);
		firstY.setValue(0);
		linefy.setValue(0);
		secondX.setValue(0);
		linesy.setValue(0);
		secondY.setValue(0);
		linesx.setValue(0);
		rectX.set(0);
		rectY.set(0);
		firstRX.setValue(0);
		firstRY.setValue(0);
		secondRX.setValue(0);
		secondRY.setValue(0);
		previewRect = new Rectangle();
		previewEllipse = new Ellipse();
		previewLine = new Line();
		previewLine.startXProperty().bind(linefx);
		previewLine.startYProperty().bind(linefy);
		previewLine.endXProperty().bind(linesx);
		previewLine.endYProperty().bind(linesy);
		width.setValue(0);
		length.setValue(0);
		widthR.setValue(0);
		lengthR.setValue(0);
		previewRect.setStroke(Color.BLACK);
		previewRect.setFill(Color.TRANSPARENT);
		previewRect.xProperty().bind(rectX);
		previewRect.yProperty().bind(rectY);
		previewRect.widthProperty().bind(widthR);
		previewRect.heightProperty().bind(lengthR);
		previewEllipse.centerXProperty().bind(firstX);
		previewEllipse.centerYProperty().bind(firstY);
		previewEllipse.radiusXProperty().bind(width);
		previewEllipse.radiusYProperty().bind(length);
		previewEllipse.setStroke(Color.BLACK);
		previewEllipse.setFill(Color.TRANSPARENT);
	}

	/**
	 * sets the canvas and other layouts for drawing.
	 * 
	 * @param cvs
	 *            canvas for free sketching
	 * @param pntPne
	 *            pane for shapes addition
	 * @param primaryStage
	 *            Application Stage
	 */
	private void setCanvas(Canvas cvs, Pane pntPne, Stage primaryStage) {
		Group root = new Group();
		initializeButtons();
		buttonActions(cvs);
		pntPne.getChildren().add(cvs);
		HBox hBox = new HBox();
		hBox.getChildren().add(colorPicker);
		hBox.getChildren().addAll(free, line, ellipse, rectangle, triangle, save, load, Undo, Redo, delete);
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox, pntPne);
		root.getChildren().addAll(vBox);
		Scene scene = new Scene(root, 700, 725);
		primaryStage.setTitle("Vector Drawing");
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	/**
	 
* Clears the actions array.
	 * 
	 * @param array
	 *            the array of actions to be cleared.
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
		delete = new Button("Delete");
		deletePressed = true;
		save = new Button("Save");
		load = new Button("Load");
		Undo = new Button("Undo");
		Redo = new Button("Redo");
		Image rec = new Image("file:rect.png");
	}

	/**
	 * sets action handlers for buttons.
	 */
	public void buttonActions(Canvas paint) {
		free.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'f';
				clearActions(actionsCounter);
				paint.toFront();
			}
		});
		line.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'l';
				clearActions(actionsCounter);
				previous = null;
				previewLine = new Line();
				previewLine.startXProperty().bind(linefx);
				previewLine.startYProperty().bind(linefy);
				previewLine.endXProperty().bind(linesx);
				previewLine.endYProperty().bind(linesy);
				// previewRect = null;
			}
		});
		rectangle.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'r';
				clearActions(actionsCounter);
				previous = null;
				previewRect = new Rectangle();
				previewRect.setStroke(Color.BLACK);
				previewRect.setFill(Color.WHITE);
				//previewRect.xProperty().bind(firstRX);
				//previewRect.yProperty().bind(firstRY);
				previewRect.widthProperty().bind(widthR);
				previewRect.heightProperty().bind(lengthR);
			}
		});
		ellipse.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'e';
				clearActions(actionsCounter);
				previous = null;
				previewEllipse = new Ellipse();
				previewEllipse.centerXProperty().bind(firstX);
				previewEllipse.centerYProperty().bind(firstY);
				previewEllipse.radiusXProperty().bind(width);
				previewEllipse.radiusYProperty().bind(length);
				// previewRect = null;
				previewLine = null;
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
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				state = 'd';
				deletePressed = true;
				System.out.println(deletePressed);
			}
		});
		Undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!redo.isEmpty()) {
					undo.push(redo.peek());
					redo.pop();
//					undoPressed = true;
					// here we update the pane!
					paintPane.getChildren().clear();
					paintPane.getChildren().add(canvas);
					if (!redo.isEmpty()) {
						// Updating reference to the new pane..
						try {
							shapes = redo.peek().clone();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						// will be changed yet..
						shapes = new Data();
					}
					shapes.updatePane(paintPane);
				} else {
					JOptionPane.showMessageDialog(null, "Nothing to be undone!",
							 "Undo error!",
							 JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		Redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!undo.isEmpty()) {
					redo.push(undo.peek());
					undo.pop();
					redoPressed = true;					
					paintPane.getChildren().clear();
					paintPane.getChildren().add(canvas);
					if (!redo.isEmpty()) {
						shapes = redo.peek();
					} else {
						shapes = new Data();
					}
					shapes.updatePane(paintPane);
				} else {
					JOptionPane.showMessageDialog(null, "Nothing to be redone!",
							 "Redo error!",
							 JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
//		save.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				data = new DataManipulation();
////				data.saveXML(base);
//				try {
//					data.saveJSON(canvas, paintPane, colorPicker, shapes);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		});
//		load.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				try {
//					data.loadJSON(canvas, paintPane, colorPicker);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		});
	}

	public Check getDeleteStatus() {
		//System.out.println(deletePressed);
		return new Check(deletePressed, paintPane);
	}
	/**
	 * sets the Color picker and the default colors for sketching.
	 * 
	 * @param gc
	 *            the graphics content of the Scene
	 */
	private void initDraw(GraphicsContext gc) {
//		colorPicker = new ColorPicker();
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