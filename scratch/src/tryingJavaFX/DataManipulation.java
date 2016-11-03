package tryingJavaFX;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import jsonShapesProperties.JSONData;

import java.io.BufferedReader;
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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.xml.XML;;
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
	public void saveJSON(JSONData shapes, Data shaps) throws IOException {
		XStream save;  
		JSONData data = new JSONData();
		data = shapes.clone();
		//System.out.println(data.getSize());
		save = new XStream(new JettisonMappedXmlDriver());
	    //save.setMode(XStream.NO_REFERENCES);
	    save.alias("JSONData", JSONData.class);
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
		JSONData loaded = new JSONData();
		load.setMode(XStream.NO_REFERENCES);
        load.alias("JSONData", JSONData.class);
        FileReader reader;
        try {
			reader = new FileReader("data.json");
			loaded = (JSONData) load.fromXML(reader);
			shapes = loaded.converToData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return shapes;
	}
}
