package ru7.hw.messaging.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru7.hw.messaging.entities.Chat;

@Repository
public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {
}
