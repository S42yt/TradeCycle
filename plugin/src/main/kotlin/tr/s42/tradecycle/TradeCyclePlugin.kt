package tr.s42.tradecycle

import tr.s42.core.Platform
import tr.s42.tradecycle.common.ComparableVersion
import tr.s42.tradecycle.common.VersionCheckTask
import tr.s42.tradecycle.listener.VillagerCycleListener
import tr.s42.tradecycle.service.TradeCycleService
import tr.s42.tradecycle.service.TradeCycleService.Companion.strategies
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import tr.s42.paper.PaperPlatform
import tr.s42.spigot.SpigotPlatform

class TradeCyclePlugin  : JavaPlugin() {

    override fun onEnable() {
        checkForUpdates()
        saveDefaultConfig()

        val platform: Platform = getPlatform()
        val service = TradeCycleService(platform.messageService, config)
        registerStrategies(platform, service, config)
    }

    private fun checkForUpdates() {
        VersionCheckTask { latestVersion ->
            val currentVersion = ComparableVersion(description.version)
            if (currentVersion < latestVersion) {
                logger.warning("A new version of TradeCycle is available: ${latestVersion.rawVersion}. You are using version: ${currentVersion.rawVersion}")
                logger.warning("Download the latest version at: ${VersionCheckTask.RESOURCE}")
                return@VersionCheckTask
            }
        }.runTaskAsynchronously(this)
    }

    private fun getPlatform(): Platform {
        try {
            Class.forName("com.destroystokyo.paper.PaperConfig")
            return PaperPlatform()
        } catch (e: ClassNotFoundException) {
            return SpigotPlatform()
        }
    }

    private fun registerStrategies(platform: Platform, service: TradeCycleService, config: FileConfiguration) {
        val strategies: List<Listener> = config.getStringList("strategy").map {
            strategies[it] ?: throw IllegalArgumentException("Invalid strategy: $it")
        }

        val pluginManager: PluginManager = server.pluginManager
        pluginManager.registerEvents(VillagerCycleListener(service, platform.openInventoryViewFunction, this), this)
        strategies.forEach { pluginManager.registerEvents(it, this) }
    }
}