package dev.xoapp.kappah.handlers;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.Tag;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kappah.forms.FormFactory;
import dev.xoapp.kappah.items.types.UnVanish;
import dev.xoapp.kappah.session.Session;
import dev.xoapp.kappah.session.SessionFactory;
import dev.xoapp.kappah.utils.Kappah;

import java.util.Objects;

public class ItemHandler implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItem();

        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        PlayerInteractEvent.Action clickAir = PlayerInteractEvent.Action.RIGHT_CLICK_AIR;
        PlayerInteractEvent.Action clickBlock = PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK;

        if (event.getAction() != clickAir && event.getAction() != clickBlock) {
            return;
        }

        assert item != null;
        String nbt = Objects.requireNonNull(item.getNamedTag()).getString("_kappah");

        if (nbt == null) {
            return;
        }

        event.setCancelled();

        switch (nbt) {
            case "vanish" -> {
                session.setVanish(true);
                player.sendMessage(TextFormat.colorize("&aVanish successfully enabled"));
                player.getInventory().setItem(8, new UnVanish());
                player.setAllowFlight(true);
            }

            case "unvanish" -> {
                session.setVanish(false);
                player.sendMessage(TextFormat.colorize("&aVanish successfully disabled"));
                session.sendKit();
                player.setAllowFlight(false);
            }

            case "teleport" -> player.showFormWindow(FormFactory.getTeleportForm(player));
        }
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        Player i_player = (Player) event.getEntity();
        Player player = (Player) event.getDamager();

        Session session = SessionFactory.getSession(player.getName());

        if (session == null) {
            return;
        }

        Item item = player.getInventory().getItemInHand();

        assert item != null;
        String nbt = Objects.requireNonNull(item.getNamedTag()).getString("kappah");

        if (nbt == null) {
            return;
        }

        event.setCancelled();

        switch (nbt) {
            case "freeze" -> {
                String freezeMessage = String.format("&7Player &e%s &7was frozen by &a%s", i_player.getName(), player.getName());
                String unFreezeMessage = String.format("&7Player &e%s &7was unfrozen by &a%s", i_player.getName(), player.getName());

                if (i_player.isImmobile()) {
                    i_player.setImmobile(false);
                    Server.getInstance().broadcastMessage(TextFormat.colorize(unFreezeMessage));
                    return;
                }

                i_player.setImmobile(true);
                Server.getInstance().broadcastMessage(TextFormat.colorize(freezeMessage));
            }

            case "information" -> Kappah.sendInformation(player, i_player);
        }
    }
}
