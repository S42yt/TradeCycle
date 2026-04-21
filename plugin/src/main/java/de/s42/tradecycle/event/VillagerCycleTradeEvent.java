package de.s42.tradecycle.event;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class VillagerCycleTradeEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Villager villager;

    public VillagerCycleTradeEvent(Player player, Villager villager) {
        this.player = player;
        this.villager = villager;
    }

    public Player getPlayer() {
        return player;
    }

    public Villager getVillager() {
        return villager;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
