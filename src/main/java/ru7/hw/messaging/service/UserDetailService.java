package ru7.hw.messaging.service;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface UserDetailService {
    Mono<UserDetails> getUserByUsername(String username);
}
