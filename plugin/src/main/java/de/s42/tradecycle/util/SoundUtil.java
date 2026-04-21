package de.s42.tradecycle.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtil {

    private SoundUtil() {}

    public static void play(Sound sound, Player player) {
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }
}
