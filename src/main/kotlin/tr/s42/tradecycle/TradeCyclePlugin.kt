package tr.s42.tradecycle

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.route.Route
import org.bukkit.event.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import tr.s42.tradecycle.common.ComparableVersion
import tr.s42.tradecycle.common.VersionCheckTask
import tr.s42.tradecycle.listener.VillagerCycleListener
import tr.s42.tradecycle.service.ConfigService
import tr.s42.tradecycle.service.TradeCycleService
import tr.s42.tradecycle.service.TradeCycleService.Companion.strategies

class TradeCyclePlugin : JavaPlugin() {

    private lateinit var plugin: TradeCyclePlugin

    override fun onLoad() {
        plugin = this
    }

    override fun onEnable() {
        checkForUpdates()

        val config: YamlDocument = ConfigService(dataFolder, classLoader).createConfig("config")
        val service = TradeCycleService(config)
        registerStrategies(service, config)
    }

    @Suppress("UnstableApiUsage")
    private fun checkForUpdates() {
        VersionCheckTask { latestVersion ->
            val currentVersion = ComparableVersion(pluginMeta.version)
            if (currentVersion < latestVersion) {
                logger.warning("A new version of TradeCycle is available: ${latestVersion.rawVersion}. You are using version: ${currentVersion.rawVersion}")
                logger.warning("Download the latest version at: ${VersionCheckTask.RESOURCE}")
                return@VersionCheckTask
            }
        }.runTaskAsynchronously(this)
    }

    private fun registerStrategies(service: TradeCycleService, config: YamlDocument) {
        val strategies: List<Listener> = config.getStringList(Route.from("strategy")).map {
            strategies[it] ?: throw IllegalArgumentException("Invalid strategy: $it")
        }

        val pluginManager: PluginManager = server.pluginManager
        pluginManager.registerEvents(VillagerCycleListener(service, this), plugin)
        strategies.forEach { pluginManager.registerEvents(it, plugin) }
    }

}