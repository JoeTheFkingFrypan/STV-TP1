package ca.uqac;

import ca.uqac.core.Click;
import ca.uqac.core.ClickParser;
import ca.uqac.core.Line;
import org.jdom2.Element;

import java.util.List;

public class Q4 {

    public static void findLineEndings(List<Element> events) {

        //We're assuming all events are in chronological order
        Element previousEvent = null;
        Element antePreviousEvent = null;

        for (Element currentEvent : events) {

            boolean lineFound = false;
            Line currentLine = null;
            Click current = ClickParser.build(currentEvent);

            if(previousEvent != null && antePreviousEvent != null) {
                Click previous = ClickParser.build(previousEvent);
                Click antePrevious = ClickParser.build(antePreviousEvent);

                //Checking the last 3 events are clicks, from the same user, in the same application
                if(ClickParser.clicksAllRelateToTheSameUserAndApp(current, previous, antePrevious)) {

                    if(ClickParser.hasDifferentXCoordinates(current, previous)) {
                        //find a and b defining the line y = ax + b
                        currentLine = new Line(current, previous);
                        if(currentLine.match(antePrevious)) {
                            lineFound = true;
                            currentLine.findExtremities(current, previous, antePrevious);
                        }
                    } else if(ClickParser.hasSameXCoordinates(current, antePrevious)) {
                        lineFound = true;
                        currentLine = new Line(current);
                        currentLine.findExtremities(current, previous, antePrevious);
                    }

                    if(lineFound) {
                        //checking if all points are on the same straight line
                        System.out.format("    - Line with extremities [(%d,%d),(%d,%d)] in application %s  by user %s\n",
                                currentLine.getFirstExtremityCoordinateX(),
                                currentLine.getFirstExtremityCoordinateY(),
                                currentLine.getSecondExtremityCoordinateX(),
                                currentLine.getSecondExtremityCoordinateY(),
                                current.getUsername(),
                                current.getApplication()
                        );
                    }
                }
            }

            //updating previous event and antePrevious event
            if(current != null)
            {
                antePreviousEvent = previousEvent;
                previousEvent = currentEvent;
            }
        }

    }
}
