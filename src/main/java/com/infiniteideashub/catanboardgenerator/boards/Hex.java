package com.infiniteideashub.catanboardgenerator.boards;

public class Hex {
    private final Resource resource;
    private final int numberToken;
    private final int q;
    private final int r;

    public Hex(Resource resource, int numberToken, int q, int r) {
        this.resource = resource;
        this.numberToken = numberToken;
        this.q = q;
        this.r = r;
    }

    public Resource getResource() { return resource; }
    public int getNumberToken() { return numberToken; }
    public int getQ() { return q; }
    public int getR() { return r; }

    public int[][] getNeighborCoordinates() {
        return new int[][] {
                {q + 1, r}, {q + 1, r - 1}, {q, r - 1},
                {q - 1, r}, {q - 1, r + 1}, {q, r + 1}
        };
    }

    @Override
    public String toString() {
        if (resource == Resource.DESERT) {
            return "[ DESERT ]";
        }
        return String.format("[ %-6s : %2d ]", resource, numberToken);
    }
}