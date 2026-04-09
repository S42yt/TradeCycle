package tr.s42.core

import org.bukkit.entity.Player

interface MessageService {

    fun sendActionBar(player: Player, message: String)

}