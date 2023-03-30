package com.example.notifier.sender;

import com.example.notifier.model.Message;

public interface Sender {

    void sendMessage(Message message);
}
