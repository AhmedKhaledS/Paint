package tryingJavaFX;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//import com.google.gson.Gson;

public class DataManipulation {
	
	public void saveXML(Data shapes) {
		Data data = new Data();
		try {
			data = shapes.clone();
			XStream save = new XStream(new StaxDriver());
			save.alias("Data", Data.class);
			File file = new File("data.xml");
			FileWriter writer;
			try {
				writer = new FileWriter(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        try {
	            if (!file.exists())
	                file.createNewFile();
	            writer = new FileWriter(file, false);
	            writer.write(save.toXML(data));
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Data loadXML(Canvas canvas, Pane paintPane, ColorPicker colorPicker, Data shapes) {
		File file = new File("data.xml");
		try {
			FileReader reader = new FileReader(file);
			XStream load = new XStream(new StaxDriver());
			load.alias("Data", Data.class);
			Data loaded = new Data();
			loaded = (Data) load.fromXML(reader);
			try {
				shapes = loaded.clone();
				return shapes;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shapes;
	}
	public void saveJSON(Data shapes) throws IOException {
		XStream save;  
		Data data = new Data();
		try {
			data = shapes.clone();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		save = new XStream(new JettisonMappedXmlDriver());
	        save.setMode(XStream.NO_REFERENCES);
	        save.alias("Data", Data.class);
	        File file = new File("data.json");
	        FileWriter writer = new FileWriter(file);
	        try {
	            if (!file.exists())
	                file.createNewFile();
	            writer = new FileWriter(file, false);
	            writer.write(save.toXML(data));
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public Data loadJSON(Canvas cvs, Pane pane, ColorPicker picker, Data shapes) {
		XStream load;
		load = new XStream(new JettisonMappedXmlDriver());
        load.alias("Data", Data.class);
        FileReader fileReader;
        try {
            fileReader = new FileReader("data.json");
            Data loaded = new Data();
            loaded = (Data) load.fromXML(fileReader);
            try {
				shapes = loaded.clone();
				return shapes;
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return shapes;
	}
}
