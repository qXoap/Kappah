package dev.xoapp.kappah.scheduler;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.effect.Effect;
import cn.nukkit.entity.effect.EffectType;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kappah.session.Session;

public class KappahMode extends Task {

    private final Session _session;

    public KappahMode(Session session) {
        _session = session;
    }

    @Override
    public void onRun(int i) {
        Player player = _session.getPlayer();

        if (player == null) {
            getHandler().cancel();
            return;
        }

        if (_session.isNull()) {
            getHandler().cancel();
            return;
        }

        if (_session.isVanish()) {
            player.addEffect(Effect.get(EffectType.INVISIBILITY).setVisible(false));
            player.addEffect(Effect.get(EffectType.NIGHT_VISION).setVisible(false));
        }

        String isVanish = _session.isVanish() ? "&aEnabled" : "&cDisabled";

        String tipMessage = String.format(
                "&fOnline: &e%s &7| &fTPS: &e%s &7| &fVanish: &r%s",
                Server.getInstance().getOnlinePlayers().size(),
                Server.getInstance().getTicksPerSecond(),
                isVanish
        );

        player.sendTip(TextFormat.colorize(tipMessage));
    }
}
