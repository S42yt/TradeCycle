package de.s42.tradecycle.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComparableVersion implements Comparable<ComparableVersion> {

    private final String rawVersion;
    private final List<Integer> parts;

    public ComparableVersion(String rawVersion) {
        this.rawVersion = rawVersion;
        this.parts = Arrays.stream(rawVersion.split("\\."))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .collect(Collectors.toList());
    }

    public String getRawVersion() {
        return rawVersion;
    }

    @Override
    public int compareTo(ComparableVersion other) {
        int maxLength = Math.max(parts.size(), other.parts.size());
        for (int i = 0; i < maxLength; i++) {
            int thisPart = i < parts.size() ? parts.get(i) : 0;
            int otherPart = i < other.parts.size() ? other.parts.get(i) : 0;
            if (thisPart != otherPart) {
                return thisPart - otherPart;
            }
        }
        return 0;
    }
}
