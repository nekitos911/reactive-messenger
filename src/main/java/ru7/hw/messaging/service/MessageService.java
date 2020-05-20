package ru7.hw.messaging.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Message;

public interface MessageService {
    Flux<Message> getChatById(String username);
    Mono<Message> sendMessageTo(Message message);
}
