package dev.xoapp.kappah;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import dev.xoapp.kappah.scheduler.async.GetExtraDataAsync;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Server.getInstance()
                .getScheduler()
                .getAsyncTaskThreadPool()
                .submit(new GetExtraDataAsync(player));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Session session = SessionFactory.getSession(player.getName());
        if (session == null) {
            return;
        }

        session.close();
        SessionFactory.unregister(player.getName());
    }
}