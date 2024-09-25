package dev.xoapp.kappah.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;

public class KappahCommand extends Command {

    public KappahCommand() {
        super("staff");
        setAliases(new String[]{"mod", "kappah"});

        setPermission("kappah.command");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!sender.isPlayer()) {
            return false;
        }

        Player player = sender.asPlayer();
        Session session = SessionFactory.getSession(player.getName());

        if (session != null) {
            session.close();
            SessionFactory.unregister(player.getName());

            player.sendMessage(TextFormat.colorize("&aYou successfully leaved the Kappah"));
            return false;
        }

        SessionFactory.register(player);
        player.sendMessage(TextFormat.colorize("&aYou successfully entered in Kappah"));
        return true;
    }
}
