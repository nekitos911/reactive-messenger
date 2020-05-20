package ru7.hw.messaging.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {
    @Value("${application.username}")
    @Getter
    private String username;
}
