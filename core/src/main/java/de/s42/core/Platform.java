package de.s42.core;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.Plugin;

import java.util.function.BiFunction;

public interface Platform {

    MessageService getMessageService();

    BiFunction<Villager, Player, InventoryView> getOpenInventoryViewFunction();

    void runAsync(Plugin plugin, Runnable task);

    void runOnEntity(Plugin plugin, Entity entity, Runnable task);
}
