package com.infiniteideashub.catanboardgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatanBoardGeneratorApplication {

    public static void main(String[] args) {
        // Starts the server and LISTENS. That is it.
        SpringApplication.run(CatanBoardGeneratorApplication.class, args);
        System.out.println("Server is running. Waiting for browser requests...");
    }
}