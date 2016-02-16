package ca.uqac.core;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class FileLoader {

    public static Document loadExternalFile(String path) throws FileLoaderException {
        try {
            File file = new File(path);
            SAXBuilder saxBuilder = new SAXBuilder();
            return saxBuilder.build(file);
        } catch (JDOMException e) {
            System.err.println("[ERROR] Couldn't properly parse XML file located at " + path + ". Please make sure it is correct");
            throw new FileLoaderException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            System.err.println("[ERROR] Unable to load file located at " + path + ". Please check file path and make sure it is correct");
            throw new FileLoaderException(e.getMessage(), e.getCause());
        }
    }

    public static Document loadResourceFile(String name) throws FileLoaderException {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            SAXBuilder saxBuilder = new SAXBuilder();
            return saxBuilder.build(classLoader.getResourceAsStream(name));
        } catch (JDOMException e) {
            System.err.println("[ERROR] Couldn't properly parse XML file named " + name + ". Please make sure it is correct");
            throw new FileLoaderException(e.getMessage(), e.getCause());
        } catch (IOException | NullPointerException e) {
            System.err.println("[ERROR] Unable to load resource file named " + name + ". Please check its name and make sure it is correct");
            throw new FileLoaderException(e.getMessage(), e.getCause());
        }
    }
}
