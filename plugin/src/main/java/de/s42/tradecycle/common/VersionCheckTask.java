package de.s42.tradecycle.common;

import lombok.AllArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

@AllArgsConstructor
public class VersionCheckTask extends BukkitRunnable {

    public static final String RESOURCE = "https://api.modrinth.com/v2/project/GpyBM69F/version?version_type=release";
    public static final String DIRECT_DOWNLOAD = "https://modrinth.com/plugin/tradecycle#download";

    private final Consumer<ComparableVersion> comparable;

    @Override
    public void run() {
        try {
            URL url = URI.create(RESOURCE).toURL();
            try (InputStream result = url.openStream()) {
                Scanner scanner = new Scanner(result);
                String json = scanner.useDelimiter("\\A").next();

                int idx = json.indexOf("\"version_number\":");
                if (idx != -1) {
                    int start = json.indexOf("\"", idx + 17) + 1;
                    int end = json.indexOf("\"", start);
                    String latestVersion = json.substring(start, end);
                    comparable.accept(new ComparableVersion(latestVersion));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}