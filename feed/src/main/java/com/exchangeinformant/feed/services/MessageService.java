package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.DTO.MessageDTO;
import com.exchangeinformant.feed.model.Message;

import java.util.List;

public interface MessageService {
    void receiveMessage(MessageDTO messageDTO);
    List<Message> unreadMessageList(long userId, int rank);
    List<Message> unreadMessageList(long userId);

}
