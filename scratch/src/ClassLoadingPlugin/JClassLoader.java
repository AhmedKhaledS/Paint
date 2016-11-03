package ClassLoadingPlugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import ShapeModels.ShapeModel;

public class JClassLoader extends ClassLoader {
	public String invokeMethod(File file) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, MalformedURLException {
			try {
				String classFile = file.getName();
				String className = "";
				className = classFile.replaceAll(".class", "");
				URLClassLoader loader = URLClassLoader.
						newInstance(new
						URL[] { file.toURI().toURL() });
				@SuppressWarnings({ "unchecked" })
				Class<? extends ShapeModel> currentLoadedClass =
				(Class<? extends ShapeModel>)loader.
				loadClass("ShapeModels." + className);
				return className;
//				Constructor<? extends ShapeModel> currentClass =
//						(Constructor<? extends ShapeModel>)currentLoadedClass.
//						getConstructor();
				
//				System.out.println(currentLoadedClass.getName());
//				Object constructorInstance = currentClass.newInstance();
//				Method methodInstance = currentLoadedClass.getMethod(methodName);
//				System.out.println("Invoked method name: " + methodInstance.getName());
				// required to implement what is needed in the method!
//				methodInstance.invoke(constructorInstance);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return null;
	}
}