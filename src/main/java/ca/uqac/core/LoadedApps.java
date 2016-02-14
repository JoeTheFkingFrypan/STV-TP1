package ca.uqac.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LoadedApps {
    Map<String, HashSet<String>> appsPerUser = new HashMap<>();

    public LoadedApps() {
        this.appsPerUser = new HashMap<>();
    }

    public int addUser(String appName, String username) {
        HashSet<String> users;
        if(this.appsPerUser.containsKey(appName)) {
            users = this.appsPerUser.get(appName);
        } else {
            users = new HashSet<>();
        }
        users.add(username);
        this.appsPerUser.put(appName, users);
        return users.size();
    }

    public void removeUser(String username) {
        for (Map.Entry<String,HashSet<String>> entry : this.appsPerUser.entrySet()) {
            String key = entry.getKey();
            HashSet<String> users = entry.getValue();
            if(users.contains(username)) {
                users.remove(username);
                this.appsPerUser.replace(key,users);
            }
        }
    }
}
