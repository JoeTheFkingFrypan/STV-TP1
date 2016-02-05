package ca.uqac;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.jdom2.Element;

import java.util.List;

public class Q5 {

    public static void findBruteForceAttempts(List<Element> events) {

        Multiset<String> loginAttempts = HashMultiset.create();
        boolean noMatches = true;

        for (Element event : events) {

            Element login = event.getChild("login");
            if(null != login) {
                String username = login.getChild("user").getText();
                loginAttempts.add(username);
                int attempts = loginAttempts.count(username);
                if(attempts >= 3) {
                    noMatches = false;
                    System.out.println("    - Potential bruteforce attempt from user " + username + " (Login attempts : " + attempts + ")");
                }
            }

            Element loginSuccess = event.getChild("login-success");
            if(null != loginSuccess) {
                String username = loginSuccess.getChild("user").getText();
                int attempts = loginAttempts.count(username);
                if(attempts >= 3) {
                    System.out.println("    - User " + username + " has successfully logged in after " + attempts + " tries");
                }
                loginAttempts.remove(username);
            }
        }

        if(noMatches) {
            System.out.println("No bruteforce attempts detected");
        }

    }
}
