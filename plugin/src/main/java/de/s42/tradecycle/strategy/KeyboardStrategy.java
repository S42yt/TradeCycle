package de.s42.tradecycle.strategy;

import de.s42.tradecycle.event.VillagerCycleTradeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantInventory;

public class KeyboardStrategy implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (event.getClick() != ClickType.SWAP_OFFHAND) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory topInventory = player.getOpenInventory().getTopInventory();
        if (!(topInventory instanceof MerchantInventory merchantInventory)) {
            return;
        }

        if (!(merchantInventory.getHolder() instanceof Villager villager)) {
            return;
        }

        event.setCancelled(true);
        Bukkit.getPluginManager().callEvent(new VillagerCycleTradeEvent(player, villager));
    }
}
