package dev.xoapp.kappah.forms;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.TextFormat;
import com.denzelcode.form.element.Button;
import com.denzelcode.form.window.SimpleWindowForm;

import java.util.Collection;

public class FormFactory {

    public static SimpleWindowForm getTeleportForm(Player player) {
        SimpleWindowForm form = new SimpleWindowForm("teleport", "Online Players");

        Collection<Player> players = Server.getInstance().getOnlinePlayers().values();
        for (Player i_player : players) {
            if (i_player.getName().equals(player.getName())) {
                continue;
            }

            form.addButton(i_player.getName(),i_player.getName() + "\nTap To Teleport");
        }

        form.addHandler((event) -> {
            Button button = event.getButton();

            if (button == null) {
                return;
            }

            Player i_player = Server.getInstance().getPlayer(button.getName());

            if (i_player == null) {
                player.sendMessage(TextFormat.colorize("&cError, Player not found"));
                return;
            }

            player.teleport(i_player.getLocation());
            player.sendMessage(TextFormat.colorize("&aYou got successfully teleported to &e" + i_player.getName()));
        });

        return form;
    }
}