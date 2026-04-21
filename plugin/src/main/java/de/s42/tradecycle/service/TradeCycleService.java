package de.s42.tradecycle.service;

import de.s42.core.MessageService;
import de.s42.tradecycle.strategy.KeyboardStrategy;
import de.s42.tradecycle.strategy.ShiftInteractStrategy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Listener;

import java.util.Map;

public class TradeCycleService {

    public static final Map<String, Listener> strategies = Map.of(
            "KEYBOARD", new KeyboardStrategy(),
            "SHIFT_INTERACT", new ShiftInteractStrategy()
    );

    private final MessageService messageService;
    private final FileConfiguration config;

    public TradeCycleService(MessageService messageService, FileConfiguration config) {
        this.messageService = messageService;
        this.config = config;
    }

    public void sendActionBar(Player player, String messageKey) {
        String message = config.getString("messages." + messageKey);
        if (message == null) {
            throw new IllegalArgumentException("Message key '" + messageKey + "' not found in config");
        }
        messageService.sendActionBar(player, message);
    }

    public void cycleTrade(Villager villager) {
        Villager.Profession profession = villager.getProfession();
        villager.setProfession(Villager.Profession.NONE);
        villager.setProfession(profession);
    }

    public boolean isLocked(Villager villager) {
        return villager.getVillagerExperience() > 0;
    }

    public boolean hasProfession(Villager villager) {
        return villager.getProfession() != Villager.Profession.NONE;
    }
}
