package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.dto.MessageOutDTO;

import java.util.List;

public interface MessageService {
    void receiveMessage(MessageInDTO messageInDTO);
    List<MessageOutDTO> unreadMessageList(long userId, int rank);
    List<MessageOutDTO> unreadMessageList(long userId);

}
