package de.s42.tradecycle.listener;

import de.s42.tradecycle.event.VillagerCycleTradeEvent;
import de.s42.tradecycle.service.TradeCycleService;
import de.s42.tradecycle.util.SoundUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

@AllArgsConstructor
public class VillagerCycleListener implements Listener {

    private final TradeCycleService tradeCycleService;
    private final BiFunction<Villager, Player, InventoryView> openInventoryViewFunction;
    private final Plugin plugin;

    @EventHandler
    public void onVillagerCycleTradeEvent(@NotNull VillagerCycleTradeEvent event) {
        Villager villager = event.getVillager();
        Player player = event.getPlayer();

        if (!tradeCycleService.hasProfession(villager)) {
            sendFailureMessage(player, "NO_PROFESSION");
            return;
        }

        if (tradeCycleService.isLocked(villager)) {
            sendFailureMessage(player, "VILLAGER_LOCKED");
            return;
        }

        tradeCycleService.cycleTrade(villager);
        Bukkit.getScheduler().runTask(plugin, () ->
                player.openInventory(openInventoryViewFunction.apply(villager, player))
        );

        SoundUtil.play(Sound.UI_BUTTON_CLICK, player);
        tradeCycleService.sendActionBar(player, "CYCLE_SUCCESS");
    }

    private void sendFailureMessage(Player player, String messageKey) {
        SoundUtil.play(Sound.ENTITY_VILLAGER_NO, player);
        tradeCycleService.sendActionBar(player, messageKey);
    }
}
