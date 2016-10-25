package simplermouseinteraction;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.lang.Math;

public class tryNerror extends Application{
	public static void main(String[] a) {
		launch(a);		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Group g = new Group();
		for (int i = 0; i < 5; i++) {
		    Rectangle r = new Rectangle();
		    r.setY(i * 20);
		    r.setWidth(300);
		    r.setHeight(10);
		    r.setFill(Color.RED);
		    g.getChildren().add(r);
		}
		Scene s = new Scene(g, 400, 500);
		arg0.setScene(s);
		arg0.show();
	}
}