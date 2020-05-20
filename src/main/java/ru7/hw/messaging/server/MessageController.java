package ru7.hw.messaging.server;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru7.hw.messaging.entities.Message;
import ru7.hw.messaging.service.MessageService;

@RestController()
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping(value = "/{chatId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> getMessagesFromChat(@PathVariable String chatId) {
        return messageService.getChatById(chatId);
    }

    @PostMapping("/{chatId}")
    public Mono<ResponseEntity<Message>> sendMessageToChat(@PathVariable String chatId, @RequestParam String message) {
        return messageService
                .sendMessageTo(new Message(null, null, message, chatId, null))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
