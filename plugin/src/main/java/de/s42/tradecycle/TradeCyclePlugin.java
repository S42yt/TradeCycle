package de.s42.tradecycle;

import de.s42.core.Platform;
import de.s42.paper.PaperPlatform;
import de.s42.spigot.SpigotPlatform;
import de.s42.tradecycle.common.ComparableVersion;
import de.s42.tradecycle.common.VersionCheckTask;
import de.s42.tradecycle.listener.VillagerCycleListener;
import de.s42.tradecycle.service.TradeCycleService;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TradeCyclePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        checkForUpdates();
        saveDefaultConfig();

        Platform platform = getPlatform();
        TradeCycleService service = new TradeCycleService(platform.getMessageService(), getConfig());
        registerStrategies(platform, service, getConfig());
    }

    private void checkForUpdates() {
        new VersionCheckTask(latestVersion -> {
            ComparableVersion currentVersion = new ComparableVersion(getDescription().getVersion());
            if (currentVersion.compareTo(latestVersion) < 0) {
                getLogger().warning("A new version of TradeCycle is available: " + latestVersion.getRawVersion() + ". You are using version: " + currentVersion.getRawVersion());
                getLogger().warning("Download the latest version at: " + VersionCheckTask.DIRECT_DOWNLOAD);
            }
        }).runTaskAsynchronously(this);
    }

    private Platform getPlatform() {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            return new PaperPlatform();
        } catch (ClassNotFoundException e) {
            return new SpigotPlatform();
        }
    }

    private void registerStrategies(Platform platform, TradeCycleService service, FileConfiguration config) {
        List<Listener> strategies = config.getStringList("strategy").stream()
                .map(s -> {
                    Listener l = TradeCycleService.strategies.get(s);
                    if (l == null) throw new IllegalArgumentException("Invalid strategy: " + s);
                    return l;
                })
                .toList();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new VillagerCycleListener(service, platform.getOpenInventoryViewFunction(), this), this);
        strategies.forEach(s -> pluginManager.registerEvents(s, this));
    }
}
