package tryingJavaFX;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_DrawOnCanvas extends Application {
 
	ColorPicker colorPicker;
    @Override
    public void start(Stage primaryStage) {
 
        Canvas canvas = new Canvas(400, 400);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext);
        
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
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(colorPicker, canvas);
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 400, 425);
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
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
         gc.setFill(Color.LIGHTGRAY);
         gc.setStroke(Color.BLACK);
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