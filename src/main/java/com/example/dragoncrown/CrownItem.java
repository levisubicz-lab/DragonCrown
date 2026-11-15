package com.example.dragoncrown;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Equippable;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class CrownItem {

    /** Egyezik a resource pack-ben lévő assets/dragon_crown/items/dragon_crown.json-nal. */
    public static final Key MODEL_KEY = Key.key("dragon_crown", "dragon_crown");

    private static final MiniMessage MM = MiniMessage.miniMessage();

    private CrownItem() {}

    public static ItemStack create() {
        ItemStack crown = new ItemStack(Material.PAPER);

        crown.setData(DataComponentTypes.ITEM_MODEL, MODEL_KEY);

        crown.setData(DataComponentTypes.EQUIPPABLE,
                Equippable.equippable(EquipmentSlot.HEAD).build());

        crown.setData(DataComponentTypes.UNBREAKABLE);

        crown.setData(DataComponentTypes.CUSTOM_NAME,
                MM.deserialize("<yellow>Dragon Crown</yellow>")
                        .decoration(TextDecoration.ITALIC, false));

        crown.setData(DataComponentTypes.LORE, ItemLore.lore(List.of(
                MM.deserialize("<gray>Crafted from the dragon egg.</gray>")
                        .decoration(TextDecoration.ITALIC, false),
                MM.deserialize("<gray>Grants infinite status effects.</gray>")
                        .decoration(TextDecoration.ITALIC, false))));

        return crown;
    }

    public static boolean isCrown(ItemStack item) {
        if (item == null || item.getType().isAir()) return false;
        Key model = item.getData(DataComponentTypes.ITEM_MODEL);
        return MODEL_KEY.equals(model);
    }
}
