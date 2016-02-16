package ca.uqac;

import ca.uqac.core.FileLoader;
import ca.uqac.core.FileLoaderException;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Document document = null;
        String fallback = "log.xml";

        if(args.length != 1)
        {
            System.err.println("Unexpected arguments.");
            System.err.println("Usage : program <logPath>");
            return;
        }

        try {
            switch (args.length) {
                case 0:
                    throw new FileLoaderException("[ERROR] Argument exception: the application requires you to specify a path to the log file to be parsed", new IllegalArgumentException());

                case 1:
                    document = FileLoader.loadExternalFile(args[0]);
                    break;

                default:
                    System.err.println("[WARN] The application only requires one parameter (path to the log file to be parsed)");
                    System.err.println("[WARN] Trying to read file at " + args[0] + " (all other parameters will be ignored)");
                    document = FileLoader.loadExternalFile(args[0]);
                    break;
            }
        } catch (FileLoaderException e) {
            System.err.println(e.getMessage());
            System.err.println("[FALLBACK] Now using resource file '" + fallback + "' as a fallback (located at src/main/resources/log.xml)");
            document = FileLoader.loadResourceFile(fallback);
        }

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
    }
}
