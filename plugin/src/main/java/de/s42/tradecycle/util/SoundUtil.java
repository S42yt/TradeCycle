package de.s42.tradecycle.util;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SoundUtil {

    private SoundUtil() {}

    public static void play(Sound sound, @NotNull Player player) {
        player.playSound(player.getLocation(), sound, 1f, 1f);
    }
}
