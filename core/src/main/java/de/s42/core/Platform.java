package de.s42.core;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.InventoryView;

import java.util.function.BiFunction;

public interface Platform {

    MessageService getMessageService();

    BiFunction<Villager, Player, InventoryView> getOpenInventoryViewFunction();
}
