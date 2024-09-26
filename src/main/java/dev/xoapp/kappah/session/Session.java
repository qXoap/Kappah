package dev.xoapp.kappah.session;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import dev.xoapp.kappah.items.types.Freeze;
import dev.xoapp.kappah.items.types.PlayerInformation;
import dev.xoapp.kappah.items.types.Teleport;
import dev.xoapp.kappah.items.types.Vanish;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Session {

    @Getter private final String name;

    private Map<Integer, Item> inventoryContents;

    @Getter private boolean vanish = false;

    public Session(String name) {
        this.name = name;
        load();
    }

    public boolean isNull() {
        return SessionFactory.getSession(name) == null;
    }

    public Player getPlayer() {
        return Server.getInstance().getPlayerExact(name);
    }

    public void load() {

        Player player = getPlayer();
        if (player == null) {
            return;
        }

        saveInventories();
        player.getInventory().clearAll();

        sendTools();
    }

    public void setVanish(boolean value) {
        vanish = value;

        Player player = getPlayer();
        if (player == null) {
            return;
        }

        if (!value) {
            player.removeAllEffects();
        }
    }

    public void sendTools() {

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
