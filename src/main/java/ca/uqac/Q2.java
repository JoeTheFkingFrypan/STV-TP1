package ca.uqac;

import org.jdom2.Element;

import java.util.HashSet;
import java.util.List;

public class Q2 {

    public static void findFilesWrittenWithoutBeingOpened(List<Element> events) {
        HashSet<String> openedFiles = new HashSet<>();
        boolean noMatches = true;

        for (Element event : events) {

            Element opened = event.getChild("open");
            if(null != opened) {
                openedFiles.add(opened.getChild("name").getText());
            }

            Element closed = event.getChild("close");
            if(null != closed) {
                String fileName = closed.getChild("name").getText();
                if(openedFiles.contains(fileName)) {
                    openedFiles.add(closed.getChild("name").getText());
                }
            }

            Element written = event.getChild("write");
            if(null != written) {
                String fileName = written.getChild("name").getText();
                if(!openedFiles.contains(fileName)) {
                    noMatches = false;
                    String user = written.getChild("user").getText();
                    System.out.println("    - File " + fileName + " has been written without being opened first by user " + user);
                }
            }
        }

        if(noMatches) {
            System.out.println("No file was written without being opened first");
        }
    }
}
