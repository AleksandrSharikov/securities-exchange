package com.example.notifier.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private String platform;

    public Message(String title, String text, String platform) {
        this.title = title;
        this.text = text;
        this.platform = platform;
    }
}
