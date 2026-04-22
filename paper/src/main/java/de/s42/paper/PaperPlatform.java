package de.s42.paper;

import de.s42.core.MessageService;
import de.s42.core.Platform;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;

import java.util.function.BiFunction;

@Getter
public class PaperPlatform implements Platform {

    private final MessageService messageService = new PaperMessageService();

    @SuppressWarnings("UnstableApiUsage")
    private final BiFunction<Villager, Player, InventoryView> openInventoryViewFunction = (villager, player) ->
            MenuType.MERCHANT.builder()
                    .merchant(villager)
                    .build(player);
}
