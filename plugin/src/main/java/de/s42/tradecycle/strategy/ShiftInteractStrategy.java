package de.s42.tradecycle.strategy;

import de.s42.tradecycle.event.VillagerCycleTradeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

public class ShiftInteractStrategy implements Listener {

    @EventHandler
    public void onPlayerInteractEntityEvent(@NotNull PlayerInteractEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Entity entity = event.getRightClicked();
        if (!(entity instanceof Villager villager)) {
            return;
        }

        Player player = event.getPlayer();
        if (!player.isSneaking()) {
            return;
        }

        Bukkit.getPluginManager().callEvent(new VillagerCycleTradeEvent(player, villager));
    }
}
