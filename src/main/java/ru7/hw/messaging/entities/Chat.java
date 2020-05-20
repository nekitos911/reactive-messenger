package ru7.hw.messaging.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document("chats")
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    private String id;
    private Set<String> users;
}
