package dev.xoapp.kappah.session;

import cn.nukkit.Player;

import java.util.HashMap;
import java.util.Map;

public class SessionFactory {

    private static final Map<String, Session> _sessions = new HashMap<>();

    public static Map<String, Session> getSessions() { return _sessions; }

    public static void register(Player player) {
        _sessions.put(player.getName(), new Session(player.getName()));
    }

    public static Session getSession(String name) {
        return _sessions.get(name);
    }

    public static void unregister(String name) {
        _sessions.remove(name);
    }
}
