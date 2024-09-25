package dev.xoapp.kappah;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import dev.xoapp.kappah.scheduler.async.GetExtraDataAsync;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;
import dev.xoapp.kappah.utils.Kappah;

public class EventHandler implements Listener {

    @cn.nukkit.event.EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Server.getInstance()
                .getScheduler()
                .getAsyncTaskThreadPool()
                .submit(new GetExtraDataAsync(player));

        Session session = SessionFactory.getSession(player.getName());
        if (session == null) {
            return;
        }

        session.close();
        SessionFactory.unregister(player.getName());
    }
}
