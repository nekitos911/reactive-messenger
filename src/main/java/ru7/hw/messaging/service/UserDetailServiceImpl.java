package ru7.hw.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.User;
import ru7.hw.messaging.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(User::toUserDetails);
    }
}
