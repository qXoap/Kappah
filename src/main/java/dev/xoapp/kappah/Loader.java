package dev.xoapp.kappah;

import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;
import com.denzelcode.form.FormAPI;
import dev.xoapp.kappah.commands.FreezeCommand;
import dev.xoapp.kappah.commands.KappahCommand;
import dev.xoapp.kappah.data.PlayerData;
import dev.xoapp.kappah.handlers.ItemHandler;
import dev.xoapp.kappah.handlers.KappahHandler;
import dev.xoapp.kappah.scheduler.KappahMode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Loader extends PluginBase {

    @Getter private static Loader instance;

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getServer().getPluginManager().registerEvents(new KappahHandler(), this);
        getServer().getPluginManager().registerEvents(new ItemHandler(), this);

        commands().forEach((key, command) -> {
            getServer().getCommandMap().register(key, command);
        });

        getServer().getScheduler().scheduleRepeatingTask(new KappahMode(), 20);

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

    public static PlayerData getPlayerData() {
        return new PlayerData();
    }
}
