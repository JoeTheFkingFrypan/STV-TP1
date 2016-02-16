package ca.uqac;

import ca.uqac.core.FileLoader;
import ca.uqac.core.FileLoaderException;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Document document = null;
        Integer questionNumber = null;
        String fallback = "log.xml";

        try {
            switch (args.length) {
                case 0:
                    throw new FileLoaderException("[ERROR] Argument exception: the application requires you to specify a path to the log file to be parsed", new IllegalArgumentException());

                case 1:
                    document = FileLoader.loadExternalFile(args[0]);
                    System.out.println("[INFO] No other parameter detected (no question number provided). Running all questions on loaded file");
                    break;

                case 2:
                    document = FileLoader.loadExternalFile(args[0]);
                    questionNumber = Integer.parseInt(args[1]);
                    break;

                default:
                    System.err.println("[WARN] The application only requires one parameter (path to the log file to be parsed)");
                    System.err.println("[WARN] Trying to read file at " + args[0] + " (all other parameters will be ignored)");
                    System.err.println("[WARN] Usage : 'program <logPath> <questionNumber>'");
                    System.err.println("[WARN] Question number parameter is optional");
                    document = FileLoader.loadExternalFile(args[0]);
                    break;
            }
        } catch (FileLoaderException e) {
            System.err.println(e.getMessage());
            System.err.println("[FALLBACK] Now using resource file '" + fallback + "' as a fallback (located at src/main/resources/log.xml)");
            document = FileLoader.loadResourceFile(fallback);
        }

        List<Element> events = document.getRootElement().getChildren();
        if(null != questionNumber) {
            runQuestion(questionNumber, events);
        } else {
            for(int i=1; i<=5; i++) {
                runQuestion(i, events);
            }
        }
    }

    private static void runQuestion(int questionNumber, List<Element> events) {
        switch (questionNumber) {
            case 1:
                System.out.println("\n======================= [QUESTION #1] =======================\n");
                Q1.findSessionsLastingForOverOneHour(events);
                break;

            case 2:
                System.out.println("\n======================= [QUESTION #2] =======================\n");
                Q2.findFilesWrittenWithoutBeingOpened(events);
                break;

            case 3:
                System.out.println("\n======================= [QUESTION #3] =======================\n");
                Q3.findConcurrentApplications(events);
                break;

            case 4:
                System.out.println("\n======================= [QUESTION #4] =======================\n");
                Q4.findLineEndings(events);
                break;

            case 5:
                System.out.println("\n======================= [QUESTION #5] =======================\n");
                Q5.findBruteForceAttempts(events);
                break;

            default:
                System.err.println("[ERROR] This question number does not exist. Correct range is [1-5].");
                break;
        }
    }
}