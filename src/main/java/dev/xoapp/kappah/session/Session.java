package dev.xoapp.kappah.session;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.effect.Effect;
import cn.nukkit.entity.effect.EffectType;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import dev.xoapp.kappah.items.types.Freeze;
import dev.xoapp.kappah.items.types.PlayerInformation;
import dev.xoapp.kappah.items.types.Teleport;
import dev.xoapp.kappah.items.types.Vanish;
import dev.xoapp.kappah.scheduler.KappahMode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Session {

    private final String _name;

    private Map<Integer, Item> inventoryContents;

    private boolean _vanish = false;

    public Session(String name) {
        _name = name;
        load();

        Server.getInstance().getScheduler().scheduleRepeatingTask(
                new KappahMode(this), 20
        );
    }

    public Boolean isNull() {
        return SessionFactory.getSession(_name) == null;
    }

    public Player getPlayer() {
        return Server.getInstance().getPlayerExact(_name);
    }

    public void load() {
        Player player = getPlayer();

        if (player == null) {
            return;
        }

        saveInventories();
        player.getInventory().clearAll();

        sendKit();
    }

    public Boolean isVanish() {
        return _vanish;
    }

    public void setVanish(boolean value) {
        _vanish = value;

        Player player = getPlayer();

        if (player == null) {
            return;
        }

        if (!value) {
            player.removeAllEffects();
        }
    }

    public void sendKit() {
        Player player = getPlayer();

        if (player == null) {
            return;
        }

        Inventory inventory = player.getInventory();

        HashMap<Integer, Item> contents = new HashMap<>();
        contents.put(0, new Freeze());
        contents.put(1, new Teleport());
        contents.put(7, new PlayerInformation());
        contents.put(8, new Vanish());

        inventory.setContents(contents);
    }

    public void saveInventories() {
        Player player = getPlayer();

        if (player == null) {
            return;
        }

        inventoryContents = player.getInventory().getContents();
    }

    public void close() {
        Player player = getPlayer();

        if (player == null) {
            return;
        }

        player.getInventory().clearAll();
        player.getInventory().setContents(inventoryContents);
    }
}
