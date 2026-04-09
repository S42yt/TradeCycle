package tr.s42.paper

import tr.s42.core.MessageService
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player

class PaperMessageService : MessageService {

    companion object {
        private val legacyComponentSerializer = LegacyComponentSerializer.legacy('&')
    }

    override fun sendActionBar(player: Player, message: String) {
        player.sendActionBar(legacyComponentSerializer.deserialize(message))
    }
}