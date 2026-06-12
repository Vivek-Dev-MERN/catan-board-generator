package com.infiniteideashub.catanboardgenerator.boards;

import java.util.*;

public class BoardGenerator {

    // Exact coordinate map to print the hexes row by row from top to bottom
    private static final int[][] GRID_COORDINATES = {
            {0,-2}, {1,-2}, {2,-2},                   // Row 1 (3)
            {-1,-1}, {0,-1}, {1,-1}, {2,-1},          // Row 2 (4)
            {-2,0}, {-1,0}, {0,0}, {1,0}, {2,0},      // Row 3 (5)
            {-2,1}, {-1,1}, {0,1}, {1,1},             // Row 4 (4)
            {-2,2}, {-1,2}, {0,2}                     // Row 5 (3)
    };

    public List<Hex> generateValidBoard() {
        BoardValidator validator = new BoardValidator();
        int attempts = 0;

        while (true) {
            attempts++;
            List<Hex> candidateList = buildRandomizedBoard();

            // Map the list for fast neighbor lookups in the validator
            Map<String, Hex> boardMap = new HashMap<>();
            for (Hex hex : candidateList) {
                boardMap.put(hex.getQ() + "," + hex.getR(), hex);
            }

            if (validator.isValidBoard(boardMap)) {
                System.out.println("Valid board found after " + attempts + " attempts.\n");
                return candidateList;
            }
        }
    }

    private List<Hex> buildRandomizedBoard() {
        List<Resource> resourceDeck = new ArrayList<>(Arrays.asList(
                Resource.WOOD, Resource.WOOD, Resource.WOOD, Resource.WOOD,
                Resource.WHEAT, Resource.WHEAT, Resource.WHEAT, Resource.WHEAT,
                Resource.SHEEP, Resource.SHEEP, Resource.SHEEP, Resource.SHEEP,
                Resource.BRICK, Resource.BRICK, Resource.BRICK,
                Resource.ORE, Resource.ORE, Resource.ORE,
                Resource.DESERT
        ));

        List<Integer> tokenDeck = new ArrayList<>(Arrays.asList(
                2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12
        ));

        Collections.shuffle(resourceDeck);
        Collections.shuffle(tokenDeck);

        List<Hex> board = new ArrayList<>();
        int tokenIndex = 0;

        for (int i = 0; i < 19; i++) {
            Resource res = resourceDeck.get(i);
            int q = GRID_COORDINATES[i][0];
            int r = GRID_COORDINATES[i][1];

            if (res == Resource.DESERT) {
                board.add(new Hex(res, 0, q, r));
            } else {
                board.add(new Hex(res, tokenDeck.get(tokenIndex), q, r));
                tokenIndex++;
            }
        }
        return board;
    }

    public void printBoardAsJSON(List<Hex> board) {
        System.out.println("[\n");
        for (int i = 0; i < board.size(); i++) {
            Hex h = board.get(i);
            // Format: {"q": 0, "r": -2, "res": "WOOD", "token": 10}
            System.out.printf("  {\"q\": %d, \"r\": %d, \"res\": \"%s\", \"token\": %d}",
                    h.getQ(), h.getR(), h.getResource().toString(), h.getNumberToken());

            // Add a comma unless it's the last item
            if (i < board.size() - 1) {
                System.out.print(",");
            }
            System.out.println();
        }
        System.out.println("\n]");
    }


}