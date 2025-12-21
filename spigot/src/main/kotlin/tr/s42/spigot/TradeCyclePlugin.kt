package tr.s42.spigot

import tr.s42.core.Platform
import tr.s42.core.PlatformProvider
import tr.s42.core.service.IMessageService
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.InventoryView
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.function.BiFunction

class TradeCyclePlugin : JavaPlugin(), PlatformProvider {

    override val plugin: Plugin = this
    override val messageService: IMessageService = MessageService()

    override fun onEnable() {
        Platform(this).onEnable()
    }

    override val openInventoryViewFunction: BiFunction<Villager, Player, InventoryView> = BiFunction { villager, player ->
        player.openMerchant(villager, true) as InventoryView
    }
}