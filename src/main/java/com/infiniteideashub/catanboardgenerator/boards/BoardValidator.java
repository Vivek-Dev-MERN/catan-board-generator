package com.infiniteideashub.catanboardgenerator.boards;

import java.util.List;
import java.util.Map;

public class BoardValidator {

    public boolean isValidBoard(Map<String, Hex> boardMap) {
        for (Hex hex : boardMap.values()) {

            // 1. The Core Desert
            if (hex.getResource() == Resource.DESERT && Frame.isCoastal(hex.getQ(), hex.getR())) {
                return false;
            }

            // 2. The Ore Ban
            if (hex.getResource() == Resource.ORE) {
                int t = hex.getNumberToken();
                if (t == 5 || t == 6 || t == 8 || t == 9 || t == 10) return false;
            }

            // 3. Coastal Penalty (6s and 8s must be coastal)
            if (hex.getNumberToken() == 6 || hex.getNumberToken() == 8) {
                if (!Frame.isCoastal(hex.getQ(), hex.getR())) return false;
            }

            // 4. Port Starvation Rule
            Port port = Frame.getPortAt(hex.getQ(), hex.getR());
            if (resourceMatchesPort(hex.getResource(), port)) return false;

            // --- NEIGHBOR CHECKS ---
            int highYieldNeighborCount = 0;

            for (int[] coord : hex.getNeighborCoordinates()) {
                Hex neighbor = boardMap.get(coord[0] + "," + coord[1]);
                if (neighbor == null) continue;

                // 5. Anti-Synergy Rule (Ore cannot touch Wheat)
                if (hex.getResource() == Resource.ORE && neighbor.getResource() == Resource.WHEAT) {
                    return false;
                }

                // Count high-yield neighbors for the new rule
                if (isHighYield(neighbor.getNumberToken())) {
                    highYieldNeighborCount++;
                }
            }

            // 6. The "Maximum Pair" Rule (Your new rule)
            // If THIS hex is high-yield, it is allowed a MAXIMUM of 1 high-yield neighbor.
            // If it touches 2 or more, it forms a cluster of 3, which is illegal.
            if (isHighYield(hex.getNumberToken()) && highYieldNeighborCount > 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isHighYield(int token) {
        return token == 5 || token == 6 || token == 8 || token == 9;
    }

    private boolean resourceMatchesPort(Resource res, Port port) {
        if (res == Resource.WOOD && port == Port.WOOD) return true;
        if (res == Resource.BRICK && port == Port.BRICK) return true;
        if (res == Resource.SHEEP && port == Port.SHEEP) return true;
        if (res == Resource.WHEAT && port == Port.WHEAT) return true;
        if (res == Resource.ORE && port == Port.ORE) return true;
        return false;
    }
}