package dev.xoapp.kappah.scheduler;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.effect.Effect;
import cn.nukkit.entity.effect.EffectType;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;

public class KappahMode extends Task {

    @Override
    public void onRun(int i) {
        for (Session session : SessionFactory.getSessions().values()) {
            Player player = session.getPlayer();

            if (player == null) {
                getHandler().cancel();
                return;
            }

            if (session.isNull()) {
                getHandler().cancel();
                return;
            }

            if (session.isVanish()) {
                player.addEffect(Effect.get(EffectType.INVISIBILITY).setVisible(false));
                player.addEffect(Effect.get(EffectType.NIGHT_VISION).setVisible(false));
            }

            String tipMessage = String.format(
                    "&fOnline: &e%s &7| &fTPS: &e%s &7| &fVanish: &r%s",
                    Server.getInstance().getOnlinePlayers().size(),
                    Server.getInstance().getTicksPerSecond(),
                    (session.isVanish() ? "&aEnabled" : "&cDisabled")
            );

            player.sendTip(TextFormat.colorize(tipMessage));
        }
    }
}
