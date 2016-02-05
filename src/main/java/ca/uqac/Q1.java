package ca.uqac;

import org.jdom2.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1 {

    public static void findSessionsLastingForOverOneHour(List<Element> events) {
        Map<String, Integer> userSessions = new HashMap<>();
        boolean noMatches = true;

        for (Element event : events) {

            Element loginSuccess = event.getChild("login-success");
            if(null != loginSuccess) {
                userSessions.put(loginSuccess.getChild("user").getText(), Integer.parseInt(event.getChild("time").getText()));
            }

            Element logout = event.getChild("logout");
            if(null != logout) {
                String username = logout.getChild("user").getText();
                if(userSessions.containsKey(username)) {
                    int loggedSince = userSessions.get(username);
                    int timeOnline = Integer.parseInt(event.getChild("time").getText()) - loggedSince;
                    if(timeOnline >= 3600) {
                        noMatches = false;
                        System.out.println("    - User " + username + " has been logged in for more than 1h (duration: " + timeOnline + " sec)");
                    }
                    userSessions.remove(username);
                }
            }
        }

        if(noMatches) {
            System.out.println("No session lasted for more than 1 hour");
        }
    }
}
