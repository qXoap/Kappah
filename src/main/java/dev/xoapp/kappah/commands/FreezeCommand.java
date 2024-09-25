package dev.xoapp.kappah.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

public class FreezeCommand extends Command {

    public FreezeCommand() {
        super("freeze");

        setPermission("kappah.command.freeze");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args[0].isEmpty()) {
            sender.sendMessage(TextFormat.colorize("&cUsage /freeze (player)"));
            return false;
        }

        Player player = Server.getInstance().getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(TextFormat.colorize("&cThis player is not online"));
            return false;
        }

        String freezeMessage = String.format("&7Player &e%s &7was frozen by &a%s", player.getName(), sender.getName());
        String unFreezeMessage = String.format("&7Player &e%s &7was unfrozen by &a%s", player.getName(), sender.getName());

        if (player.isImmobile()) {
            player.setImmobile(false);
            Server.getInstance().broadcastMessage(TextFormat.colorize(unFreezeMessage));
            return false;
        }

        player.setImmobile(true);
        Server.getInstance().broadcastMessage(TextFormat.colorize(freezeMessage));
        return true;
    }
}
