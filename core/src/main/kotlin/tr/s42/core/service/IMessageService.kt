package tr.s42.core.service

import org.bukkit.entity.Player

interface IMessageService {

    fun sendActionBar(player: Player, message: String)

}