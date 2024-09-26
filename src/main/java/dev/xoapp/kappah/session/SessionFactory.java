package dev.xoapp.kappah.session;

import cn.nukkit.Player;

import java.util.HashMap;
import java.util.Map;

public class SessionFactory {

    private static final Map<String, Session> sessions = new HashMap<>();

    public static Map<String, Session> getSessions() {
        return sessions;
    }

    public static void register(Player player) {
        sessions.put(player.getName(), new Session(player.getName()));
    }

    public static Session getSession(String name) {
        return sessions.get(name);
    }

    public static void unregister(String name) {
        sessions.remove(name);
    }
}