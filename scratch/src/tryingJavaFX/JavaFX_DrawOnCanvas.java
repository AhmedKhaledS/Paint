package tryingJavaFX;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_DrawOnCanvas extends Application {

	/**Color Picker to choose colors from.*/
	private ColorPicker colorPicker;
	/**Button to insert a new Rectangle.*/
	private Button rectangle;
	/**Button to insert a new Line.*/
	private Button line;
	/**Button to insert a new Ellipse.*/
	private Button ellipse;
	/**Button to perform Dynamic Class Loading.*/
	private Button dynamicload;

	/**state of the current drawing mode(rectangle, line, free sketching, etc.).*/
	private char state;

	/**
	 * Initializes the drawing enviroment.
	 * @param primaryStage stage at which all components are appended
	 * */
    public void start(Stage primaryStage) {
 
        Canvas canvas = new Canvas(400, 400);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
        //ABo khaled edit here allah ykrmk ...check the state for char values
        // f for free Sketch
        // e for ellipse
        // l for line
        // r for rectangles
        // yet more will be added
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
         
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
            	graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
 
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
 
            }
        });
 
        Group root = new Group();
        initializeButtons();
        HBox hBox = new HBox();
        hBox.getChildren().add(colorPicker);
        hBox.getChildren().addAll(ellipse, line, rectangle);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, canvas);
        root.getChildren().addAll(vBox);
        Scene scene = new Scene(root, 400, 425);
        primaryStage.setTitle("Vector Drawing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Initializes all buttons on the drawing pane.
     * */
    public void initializeButtons() {
    	rectangle = new Button("Rectangle");
        line = new Button("Line");
        ellipse = new Button("Ellipse");
        Image rec = new Image("file:rect.png");
        //rectangle.setLayoutY(100);
        //line.setLayoutY(100);line.setLayoutX(50);
        //ellipse.setLayoutY(100);ellipse.setLayoutX(100);
    }

    /**
     * main method.
     * @args needed of called from outside
     * */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * set action handlers for buttons.
     * */
    public void buttonActions() {
    	rectangle.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	        state = 'r';
    	    }
    	});
    	line.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	        state = 'l';
    	    }
    	});
    	ellipse.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	        state = 'e';
    	    }
    	});
    }
    /**
     * sets the Color picker and the default colors for sketching.
     * @param gc the graphics content of the Scene
     * */
    private void initDraw(GraphicsContext gc){
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
         gc.strokeRect(
                 0,              //x of the upper left corner
                 0,              //y of the upper left corner
                 canvasWidth,    //width of the rectangle
                 canvasHeight);  //height of the rectangle
  
         gc.setFill(colorPicker.getValue());
         gc.setStroke(colorPicker.getValue());
         gc.setLineWidth(1);
         
    }
     
}