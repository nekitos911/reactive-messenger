package ru7.hw.messaging.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    private String id;
    private String author;
    private String text;
    private String chatId;
    private Date date;
}
