package de.s42.paper;

import de.s42.core.MessageService;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;

public class PaperMessageService implements MessageService {
    private static final LegacyComponentSerializer legacyComponentSerializer = LegacyComponentSerializer.legacy('&');

    @Override
    public void sendActionBar(Player player, String message) {
        player.sendActionBar(legacyComponentSerializer.deserialize(message));
    }
}
