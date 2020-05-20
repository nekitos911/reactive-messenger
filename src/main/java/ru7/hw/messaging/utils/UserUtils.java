package ru7.hw.messaging.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserUtils {
    @Getter
    @Setter
    private String username;

}
