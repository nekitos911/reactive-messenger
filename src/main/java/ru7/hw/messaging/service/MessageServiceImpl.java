package ru7.hw.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Message;
import ru7.hw.messaging.repositories.ChatRepository;
import ru7.hw.messaging.repositories.MessageRepository;
import ru7.hw.messaging.utils.UserUtils;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserUtils userUtils;

    @Override
    public Flux<Message> getChatById(String chatId) {
        return chatRepository
                .findById(chatId)
                .filter(chat -> chat.getUsers().contains(userUtils.getUsername()))
                .flatMapMany(chat -> messageRepository.findByChatId(chatId));
    }

    @Override
    public Mono<Message> sendMessageTo(Message message) {
        return chatRepository
                .findById(message.getChatId())
                .filter(chat -> chat.getUsers().contains(userUtils.getUsername()))
                .flatMap(chat -> messageRepository.save(
                        new Message(null, userUtils.getUsername(), message.getText() ,message.getChatId(), new Date())
                ));
    }
}
