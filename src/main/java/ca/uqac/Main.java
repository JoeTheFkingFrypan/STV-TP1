package ca.uqac;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File inputFile = new File(classLoader.getResource("log.xml").getFile());
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);

            List<Element> events = document.getRootElement().getChildren();

            System.out.println("\n======================= [QUESTION #1] =======================\n");
            Q1.findSessionsLastingForOverOneHour(events);
            System.out.println("\n======================= [QUESTION #2] =======================\n");
            Q2.findFilesWrittenWithoutBeingOpened(events);
            System.out.println("\n======================= [QUESTION #3] =======================\n");
            Q3.findConcurrentApplications(events);
            System.out.println("\n======================= [QUESTION #4] =======================\n");
            Q4.findLineEndings(events);
            System.out.println("\n======================= [QUESTION #5] =======================\n");
            Q5.findBruteForceAttempts(events);

        } catch (IOException | JDOMException e) {
            System.err.println("An error occured while processing file");
            e.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }
}
