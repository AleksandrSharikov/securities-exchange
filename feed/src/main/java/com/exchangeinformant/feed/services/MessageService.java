package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.model.Message;
import com.exchangeinformant.feed.model.Rank;

import java.util.List;

public interface MessageService {
    void receiveMessage(Message message);
    List<Message> unreadMessageList(long userId, Rank rank);
}
