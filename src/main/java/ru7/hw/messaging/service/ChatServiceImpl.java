package ru7.hw.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Chat;
import ru7.hw.messaging.repositories.ChatRepository;
import ru7.hw.messaging.repositories.MessageRepository;
import ru7.hw.messaging.utils.UserUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserUtils userUtils;

    @Override
    public Mono<Chat> update(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Mono<Chat> save(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Mono<Chat> getById(String id) {
        return chatRepository.findById(id);
    }

    @Override
    public Flux<Chat> getAll() {
        return chatRepository.findAll().filter(chat -> chat.getUsers().contains(userUtils.getUsername()));
    }

    @Override
    public Mono<Chat> addUsers(String chatId, Set<String> users) {
        return chatRepository.findById(chatId)
                .flatMap(chatFromDb -> {
                    var existingUsers = new HashSet<>(chatFromDb.getUsers());
                    existingUsers.addAll(users);
                    chatFromDb.setUsers(existingUsers);
                    return chatRepository.save(chatFromDb);
                });
    }
}
