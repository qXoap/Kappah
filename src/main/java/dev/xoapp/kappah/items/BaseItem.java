package dev.xoapp.kappah.items;

import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BaseItem extends Item {

    public BaseItem(
            String id,
            @NotNull String compound_name,
            String custom_name
    ) {
        super(id, 0);

        setCustomName(TextFormat.colorize(custom_name));

        CompoundTag named_tag = Objects.requireNonNull(getNamedTag());
        setNamedTag(named_tag.putString("_kappah", compound_name));
    }
}
