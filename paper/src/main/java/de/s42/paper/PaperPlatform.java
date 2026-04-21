package de.s42.paper;

import de.s42.core.MessageService;
import de.s42.core.Platform;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;

import java.util.function.BiFunction;

public class PaperPlatform implements Platform {

    private final MessageService messageService = new PaperMessageService();

    @SuppressWarnings("UnstableApiUsage")
    private final BiFunction<Villager, Player, InventoryView> openInventoryViewFunction = (villager, player) ->
            MenuType.MERCHANT.builder()
                    .merchant(villager)
                    .build(player);

    @Override
    public MessageService getMessageService() {
        return messageService;
    }

    @Override
    public BiFunction<Villager, Player, InventoryView> getOpenInventoryViewFunction() {
        return openInventoryViewFunction;
    }
}
