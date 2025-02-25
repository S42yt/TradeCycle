package tr.s42.tradecycler.listener

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.MenuType
import tr.s42.tradecycler.event.VillagerCycleTradeEvent
import tr.s42.tradecycler.service.TradeCycleService
import tr.s42.tradecycler.util.SoundUtil.play

class VillagerCycleListener(private val tradeCycleService: TradeCycleService) : Listener {

    @EventHandler
    @Suppress("UnstableApiUsage")
    fun onVillagerCycleTradeEvent(event: VillagerCycleTradeEvent) {
        val villager: Villager = event.villager
        val player: Player = event.player
        if (!tradeCycleService.hasProfession(villager)) {
            sendFailureMessage(player, "NO_PROFESSION")
            return
        }

        if (tradeCycleService.isLocked(villager)) {
            sendFailureMessage(player, "VILLAGER_LOCKED")
            return
        }

        tradeCycleService.cycleTrade(villager)
        val inventoryView = MenuType.MERCHANT.builder().merchant(villager).build(player)
        player.openInventory(inventoryView)

        Sound.UI_BUTTON_CLICK.play(player)
        tradeCycleService.sendActionBar(player, "CYCLE_SUCCESS")
    }

    private fun sendFailureMessage(player: Player, messageKey: String) {
        Sound.ENTITY_VILLAGER_NO.play(player)
        tradeCycleService.sendActionBar(player, messageKey)
    }

}