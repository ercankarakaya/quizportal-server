package com.ercan;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class QuizportalServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuizportalServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Started Project...");
    }
}
