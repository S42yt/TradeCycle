package tr.s42.core

import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.inventory.InventoryView
import java.util.function.BiFunction

interface Platform {

    val messageService: MessageService
    val openInventoryViewFunction: BiFunction<Villager, Player, InventoryView>

}