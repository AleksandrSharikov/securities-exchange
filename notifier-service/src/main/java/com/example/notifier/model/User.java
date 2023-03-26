package com.example.notifier.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private long id;
    private String email;
    private String phone;
}
