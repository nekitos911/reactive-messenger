package ru7.hw.messaging.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru7.hw.messaging.entities.Message;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
    @Tailable
    Flux<Message> findByChatId(String chatId);
}
