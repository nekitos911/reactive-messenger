package ru7.hw.messaging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.hw.md5.MD5;
import ru7.hw.messaging.entities.User;
import ru7.hw.messaging.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }

    @Bean
    @Profile("initUsers")
    public CommandLineRunner start(UserRepository userRepository){
        return args -> {
            List<User> users = Arrays.asList(
                    new User(null, "test2", MD5.getHash("test".getBytes())),
                    new User(null, "test1", MD5.getHash("test".getBytes()))
            );
            userRepository.saveAll(users).subscribe();
        };
    }

}
