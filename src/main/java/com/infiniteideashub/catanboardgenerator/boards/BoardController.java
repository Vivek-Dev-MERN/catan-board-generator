
package com.infiniteideashub.catanboardgenerator.boards;

import com.infiniteideashub.catanboardgenerator.boards.BoardGenerator;
import com.infiniteideashub.catanboardgenerator.boards.Hex;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @CrossOrigin(origins = "*") // Allows your HTML file to fetch data without security blocks
    public class BoardController {

        @GetMapping("/api/board")
        public List<Hex> getGeneratedBoard() {
            System.out.println("Received request for a new board. Generating...");

            BoardGenerator generator = new BoardGenerator();

            // Spring Boot automatically converts this Java List into JSON!
            return generator.generateValidBoard();
        }
    }

