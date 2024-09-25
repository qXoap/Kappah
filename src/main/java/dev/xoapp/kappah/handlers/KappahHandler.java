package dev.xoapp.kappah.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.inventory.InventoryMoveItemEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.player.PlayerDropItemEvent;
import cn.nukkit.event.player.PlayerFoodLevelChangeEvent;
import cn.nukkit.inventory.InventoryHolder;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;

public class KappahHandler implements Listener {

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        event.setCancelled();
    }

    @EventHandler
    public void onPlayerExhaust(PlayerFoodLevelChangeEvent event) {
        Player player = event.getPlayer();
        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        event.setCancelled();
    }

    @EventHandler
    public void onPlayerPickup(InventoryPickupItemEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof Player player)) {
            return;
        }

        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        event.setCancelled();
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Player player)) {
            return;
        }

        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        event.setCancelled();
    }

    @EventHandler
    public void onSlotChange(InventoryMoveItemEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if (!(holder instanceof Player player)) {
            return;
        }

        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        event.setCancelled();
    }
}
