package com.example.dragoncrown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public final class CrownEffectTask extends BukkitRunnable {

    private static final int EFFECT_DURATION_TICKS = 40;
    private static final int REMOVAL_THRESHOLD_TICKS = 60;
    private static final List<PotionEffectType> CROWN_EFFECTS = List.of(
            PotionEffectType.SPEED,
            PotionEffectType.STRENGTH,
            PotionEffectType.FIRE_RESISTANCE);

    private final Plugin plugin;

    public CrownEffectTask(Plugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        runTaskTimer(plugin, 0L, 10L);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (isWearingCrown(player)) {
                applyCrownEffects(player);
            } else {
                removeExpiringCrownEffects(player);
            }
        }
    }

    private boolean isWearingCrown(Player player) {
        return CrownItem.isCrown(player.getInventory().getHelmet());
    }

    private void applyCrownEffects(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, EFFECT_DURATION_TICKS, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, EFFECT_DURATION_TICKS, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, EFFECT_DURATION_TICKS, 0));
    }

    private void removeExpiringCrownEffects(Player player) {
        for (PotionEffectType type : CROWN_EFFECTS) {
            PotionEffect effect = player.getPotionEffect(type);
            if (effect != null && effect.getDuration() < REMOVAL_THRESHOLD_TICKS) {
                player.removePotionEffect(type);
            }
        }
    }
}
