package tr.s42.tradecycle.util

import org.bukkit.Sound
import org.bukkit.entity.Player

object SoundUtil {

    fun Sound.play(player: Player) {
        player.playSound(player.location, this, 1f, 1f)
    }

}