package ca.uqac;

import org.jdom2.Element;

import java.util.List;

public class Q3 {

    public static void findConcurrentApplications(List<Element> events) {
        LoadedApps loadedAppsPerUser = new LoadedApps();
        boolean noMatches = true;

        for (Element event : events) {

            Element launched = event.getChild("launch");
            if(null != launched) {
                String appName = launched.getChild("application").getText();
                int concurrentUsers = loadedAppsPerUser.addUser(appName, launched.getChild("user").getText());
                if(concurrentUsers >= 2) {
                    noMatches = false;
                    System.out.println("    - Application " + appName + " is currently used by " + concurrentUsers + " different users at the same time");
                }
            }

            Element logout = event.getChild("logout");
            if(null != logout) {
                String username = logout.getChild("user").getText();
                loadedAppsPerUser.removeUser(username);
            }
        }

        if(noMatches) {
            System.out.println("No concurrent application usage could be found");
        }
    }
}
