package br.com.kbumwatcher.kbumwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KbumWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(KbumWatcherApplication.class, args);
    }

}
