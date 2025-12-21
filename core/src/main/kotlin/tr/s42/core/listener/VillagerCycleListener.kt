package tr.s42.core.listener

import tr.s42.core.event.VillagerCycleTradeEvent
import tr.s42.core.service.TradeCycleService
import tr.s42.core.util.SoundUtil.play
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class VillagerCycleListener(
    private val tradeCycleService: TradeCycleService,
    private val plugin: Plugin
) : Listener {

    @EventHandler
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
        Bukkit.getScheduler().runTask(plugin, Runnable {
            player.closeInventory()
            player.openMerchant(villager, true)
        })

        Sound.UI_BUTTON_CLICK.play(player)
        tradeCycleService.sendActionBar(player, "CYCLE_SUCCESS")
    }

    private fun sendFailureMessage(player: Player, messageKey: String) {
        Sound.ENTITY_VILLAGER_NO.play(player)
        tradeCycleService.sendActionBar(player, messageKey)
    }
}