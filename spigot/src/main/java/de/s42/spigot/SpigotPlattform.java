package de.s42.spigot;

import de.s42.core.MessageService;
import de.s42.core.Platform;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.MenuType;

import java.util.function.BiFunction;

@Getter
public class SpigotPlattform implements Platform {

    private final MessageService messageService = new SpigotMessageService();

    @SuppressWarnings("UnstableApiUsage")
    private final BiFunction<Villager, Player, InventoryView> openInventoryViewFunction = (villager, player) ->
            MenuType.MERCHANT.builder()
                    .title(villager.getProfession().getKey().toString())
                    .merchant(villager)
                    .build(player);
}
