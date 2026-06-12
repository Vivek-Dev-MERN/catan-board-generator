package com.infiniteideashub.catanboardgenerator.boards;

import java.util.Map;
import static java.util.Map.entry;

public class Frame {
    // Immutable map binding a coordinate "q,r" to a specific Port.
    private static final Map<String, Port> PORT_LOCATIONS = Map.ofEntries(
            entry("0,-2", Port.NONE),
            entry("1,-2", Port.SHEEP),
            entry("2,-2", Port.THREE_TO_ONE),
            entry("2,-1", Port.NONE),
            entry("2,0", Port.THREE_TO_ONE),
            entry("1,1", Port.BRICK),
            entry("0,2", Port.NONE),
            entry("-1,2", Port.WOOD),
            entry("-2,2", Port.THREE_TO_ONE),
            entry("-2,1", Port.WHEAT),
            entry("-2,0", Port.ORE),
            entry("-1,-1", Port.THREE_TO_ONE)
    );

    public static Port getPortAt(int q, int r) {
        return PORT_LOCATIONS.getOrDefault(q + "," + r, Port.NONE);
    }

    public static boolean isCoastal(int q, int r) {
        return PORT_LOCATIONS.containsKey(q + "," + r);
    }
}