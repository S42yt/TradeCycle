package de.s42.spigot;

import de.s42.core.MessageService;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpigotMessageService implements MessageService {

    @Override
    public void sendActionBar(Player player, String message) {
        String coloredMessage = ChatColor.translateAlternateColorCodes('&', message);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(coloredMessage));
    }
}
