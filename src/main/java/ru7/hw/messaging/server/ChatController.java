package ru7.hw.messaging.server;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Chat;
import ru7.hw.messaging.service.ChatService;

import java.util.Set;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public Mono<ResponseEntity<Chat>> createChat(@RequestBody Chat chat) {
        return chatService
                .save(chat)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<Chat> getAllChats() {
        return chatService.getAll();
    }

    @PutMapping("/{chatId}")
    public Mono<ResponseEntity<Chat>> addUsersToChat(@PathVariable String chatId, @RequestBody Set<String> users) {
        return chatService
                .addUsers(chatId, users)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
