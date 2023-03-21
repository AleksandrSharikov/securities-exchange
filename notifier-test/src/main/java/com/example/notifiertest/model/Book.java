package com.example.notifiertest.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Book {
    private long id;
    private String name;

    @Builder
    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
