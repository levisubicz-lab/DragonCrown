package com.example.dragoncrown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonCrownPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CrownProtectionListener(), this);
        new CrownEffectTask(this).start();

        // Recept: "d d" / "geg"  (d = gyémántblokk, g = aranyblokk, e = sárkánytojás)
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "dragon_crown_recipe"), CrownItem.create());
        recipe.shape("d d", "geg");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('g', Material.GOLD_BLOCK);
        recipe.setIngredient('e', Material.DRAGON_EGG);
        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        Bukkit.removeRecipe(new NamespacedKey(this, "dragon_crown_recipe"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("dragoncrown.admin")) {
            sender.sendMessage("You don't have permission.");
            return true;
        }
        Player target = null;
        if (args.length >= 2 && args[0].equalsIgnoreCase("give")) {
            target = Bukkit.getPlayerExact(args[1]);
        } else if (args.length == 1 && args[0].equalsIgnoreCase("give") && sender instanceof Player p) {
            target = p;
        } else {
            sender.sendMessage("Usage: /dragoncrown give [player]");
            return true;
        }
        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }
        target.getInventory().addItem(CrownItem.create());
        sender.sendMessage("Gave the Dragon Crown to " + target.getName() + ".");
        return true;
    }
}
