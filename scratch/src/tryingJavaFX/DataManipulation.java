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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class DataManipulation {
	
	public void saveXML(Tools tools) {
		try {
			File file = new File("D:\\file.xml");
//			File filePane = new File("D:\\Paint Project\\OOPPaint\\filePane.xml");
//			File fileClrPicker = new File("D:\\Paint Project\\OOPPaint\\fileClrPicker.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Tools.class);
//			JAXBContext jaxbContextPane = JAXBContext.newInstance(Tools.class);
//			JAXBContext jaxbContextPicker = JAXBContext.newInstance(Tools.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			Marshaller jaxbMarshaller_pane = jaxbContextPane.createMarshaller();
//			Marshaller jaxbMarshaller_picker = jaxbContextPicker.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			jaxbMarshaller_pane.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			jaxbMarshaller_picker.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.marshal(tools, file);
			jaxbMarshaller.marshal(tools, System.out);
//			jaxbMarshaller_pane.marshal(pntPane, filePane);
//			jaxbMarshaller_picker.marshal(clrPicker, fileClrPicker);
		} catch (JAXBException e) {
			 JOptionPane.showMessageDialog(null, "File is not found",
					 "File exception: ",
					 JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void loadXML(Canvas canvas, Pane paintPane, ColorPicker colorPicker) {
		try {
			File file = new File("D:\file.xml");
//			File filePane = new File("D:\\Paint Project\\OOPPaint\\filePane.xml");
//			File fileClrPicker = new File("D:\\Paint Project\\OOPPaint\\fileClrPicker.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Tools.class);
//			JAXBContext jaxbContextPane = JAXBContext.newInstance(Tools.class);
//			JAXBContext jaxbContextPicker = JAXBContext.newInstance(Tools.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//			Unmarshaller jaxbUnmarshaller_pane = jaxbContextPane.createUnmarshaller();
//			Unmarshaller jaxbUnmarshaller_picker = jaxbContextPicker.createUnmarshaller();
			Tools tools = (Tools)jaxbUnmarshaller.unmarshal(file);
//			Canvas cvs
//			Canvas cvs = (Canvas)jaxbUnmarshaller_cvs.unmarshal(fileCvs);
//			Pane pntPane = (Pane)jaxbUnmarshaller_pane.unmarshal(filePane);
//			ColorPicker clrPicker = (ColorPicker)jaxbUnmarshaller_picker.unmarshal(fileClrPicker);
//			canvas = cvs;
//			paintPane = pntPane;
//			colorPicker = clrPicker;
		} catch(JAXBException e) {
			JOptionPane.showMessageDialog(null, "Error while saving.",
					 "File exception: ",
					 JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	public void saveJSON(Canvas cvs, Pane pane, ColorPicker picker, Data shapes) throws IOException {
		Gson gson = new Gson();
		ColorPicker clr = picker;
		String json = gson.toJson(clr);
		System.out.println(json);
		gson.toJson(clr, new FileWriter("D:\\file.json"));
	}
	public void loadJSON(Canvas cvs, Pane pane, ColorPicker picker)
			throws org.json.simple.parser.ParseException {

	}
}
