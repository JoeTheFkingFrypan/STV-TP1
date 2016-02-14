package ca.uqac.core;

import org.jdom2.Element;

public class ClickParser {

    public static Click build(Element event) {
        Click current = null;
        if(null != event.getChild("click")) {
            current = new Click(event.getChild("click"));
        }
        return current;
    }

    public static boolean clicksAreNotNull(Click current, Click previous, Click antePrevious) {
        return null != current && null != previous && null != antePrevious;
    }

    public static boolean clicksAllRelateToSameUser(Click current, Click previous, Click antePrevious) {
        String previousUsername = previous.getUsername();
        return current.relateToSameUser(previousUsername) && antePrevious.relateToSameUser(previousUsername);
    }

    public static boolean clicksAllRelateToSameApplication(Click current, Click previous, Click antePrevious) {
        String previousApplication = previous.getApplication();
        return current.relateToSameApplication(previousApplication) && antePrevious.relateToSameApplication(previousApplication);
    }

    public static boolean clicksAllRelateToTheSameUserAndApp(Click current, Click previous, Click antePrevious) {
        return clicksAreNotNull(current, previous, antePrevious) &&
               clicksAllRelateToSameUser(current, previous, antePrevious) &&
               clicksAllRelateToSameApplication(current, previous, antePrevious);
    }

    public static boolean hasDifferentXCoordinates(Click current, Click previous) {
        return current.hasDifferentXCoordinates(previous.getX());
    }

    public static boolean hasSameXCoordinates(Click current, Click antePrevious) {
        return current.hasSameXCoordinates(antePrevious.getX());
    }
}
