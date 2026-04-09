package tr.s42.paper

import tr.s42.core.Platform
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.MenuType
import tr.s42.core.MessageService
import java.util.function.BiFunction

class PaperPlatform : Platform {

    override val messageService: MessageService = PaperMessageService()

    @Suppress("UnstableApiUsage")
    override val openInventoryViewFunction: BiFunction<Villager, Player, InventoryView> =
        BiFunction { villager, player ->
            MenuType.MERCHANT.builder()
                .merchant(villager)
                .build(player)
        }
}