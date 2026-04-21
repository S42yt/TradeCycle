package de.s42.tradecycle.common;

import org.bukkit.scheduler.BukkitRunnable;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class VersionCheckTask extends BukkitRunnable {

    public static final String RESOURCE = "https://api.spigotmc.org/legacy/update.php?resource=122805";

    private final Consumer<ComparableVersion> comparable;

    public VersionCheckTask(Consumer<ComparableVersion> comparable) {
        this.comparable = comparable;
    }

    @Override
    public void run() {
        try {
            URL url = URI.create(RESOURCE).toURL();
            try (InputStream result = url.openStream()) {
                Scanner scanner = new Scanner(result);
                comparable.accept(new ComparableVersion(scanner.next()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
