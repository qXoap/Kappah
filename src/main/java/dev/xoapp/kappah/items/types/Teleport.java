package dev.xoapp.kappah.items.types;

import cn.nukkit.Player;
import cn.nukkit.item.ItemID;
import cn.nukkit.math.Vector3;
import dev.xoapp.kappah.forms.FormFactory;
import dev.xoapp.kappah.items.BaseItem;

public class Teleport extends BaseItem {

    public Teleport() {
        super(ItemID.COMPASS, "teleport", "&l&eTeleport");
    }
}
