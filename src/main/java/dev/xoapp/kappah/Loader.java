package dev.xoapp.kappah;

import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;
import com.denzelcode.form.FormAPI;
import dev.xoapp.kappah.commands.FreezeCommand;
import dev.xoapp.kappah.commands.KappahCommand;
import dev.xoapp.kappah.data.PlayerData;
import dev.xoapp.kappah.handlers.ItemHandler;
import dev.xoapp.kappah.handlers.KappahHandler;

import java.util.HashMap;
import java.util.Map;

public class Loader extends PluginBase {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new EventHandler(), this);
        getServer().getPluginManager().registerEvents(new KappahHandler(), this);
        getServer().getPluginManager().registerEvents(new ItemHandler(), this);

        commands().forEach((key, command) -> {
            getServer().getCommandMap().register(key, command);
        });

        FormAPI.init(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private Map<String, Command> commands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put("freeze", new FreezeCommand());
        commands.put("staff", new KappahCommand());

        return commands;
    }

    public static Loader getInstance() {
        return new Loader();
    }

    public static PlayerData getPlayerData() {
        return new PlayerData();
    }
}