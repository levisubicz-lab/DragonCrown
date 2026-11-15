package com.example.dragoncrown;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public final class CrownProtectionListener implements Listener {

    private static final MiniMessage MM = MiniMessage.miniMessage();

    @EventHandler
    public void onPlayerItemEnchant(EnchantItemEvent event) {
        if (CrownItem.isCrown(event.getItem())) {
            event.setCancelled(true);
            event.getEnchanter().sendMessage(MM.deserialize("<red>You cannot enchant the Dragon Crown!</red>"));
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack first = event.getInventory().getItem(0);
        if (CrownItem.isCrown(first)) {
            event.setResult(new ItemStack(Material.AIR));
        }
    }
}
