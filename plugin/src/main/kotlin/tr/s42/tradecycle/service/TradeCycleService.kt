package tr.s42.tradecycle.service

import tr.s42.tradecycle.strategy.KeyboardStrategy
import tr.s42.tradecycle.strategy.ShiftInteractStrategy
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.entity.Villager
import org.bukkit.event.Listener
import tr.s42.core.MessageService

class TradeCycleService(
    private val messageService: MessageService,
    private val config: FileConfiguration
) {

    companion object {
        val strategies: Map<String, Listener> = mapOf(
            "KEYBOARD" to KeyboardStrategy(),
            "SHIFT_INTERACT" to ShiftInteractStrategy()
        )
    }

    fun sendActionBar(player: Player, messageKey: String) {
        val message: String = config.getString("messages.$messageKey")
            ?: throw IllegalArgumentException("Message key '$messageKey' not found in config")
        messageService.sendActionBar(player, message)
    }

    fun cycleTrade(villager: Villager) {
        val profession = villager.profession
        villager.profession = Villager.Profession.NONE
        villager.profession = profession
    }

    fun isLocked(villager: Villager): Boolean {
        return villager.villagerExperience > 0
    }

    fun hasProfession(villager: Villager): Boolean {
        return villager.profession != Villager.Profession.NONE
    }

}