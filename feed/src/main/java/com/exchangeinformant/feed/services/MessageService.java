package com.exchangeinformant.feed.services;

import com.exchangeinformant.feed.dto.MessageInDTO;
import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.dto.MessagePreDTO;

import java.util.List;

public interface MessageService {
    void receiveMessage(MessageInDTO messageInDTO);
    List<MessageOutDTO> unreadMessageList(long userId, int rank);
    List<MessageOutDTO> unreadMessageList(long userId);
    List<MessagePreDTO> allMessageList(long userId, int rank, int page);
    List<MessagePreDTO> allMessageList(long userId, int page);
    MessageOutDTO getFullMessage(long messageId);

}
