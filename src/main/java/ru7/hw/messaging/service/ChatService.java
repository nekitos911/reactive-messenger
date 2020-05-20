package ru7.hw.messaging.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Chat;

import java.util.Set;

public interface ChatService {
    Mono<Chat> update(Chat chat);
    Mono<Chat> save(Chat chat);
    Mono<Chat> getById(String id);
    Flux<Chat> getAll();
    Mono<Chat> addUsers(String chatId, Set<String> users);
}
