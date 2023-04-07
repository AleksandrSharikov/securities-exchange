package com.exchangeinformant.feed.controllers;



import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Конотроллер непрочитанных сообщений
 */
@Slf4j
@RestController
@RequestMapping("/feed")
@Tag(name = "Контроллер ленты", description = "Получение непрочитанных сообщений")
public class UnreadMessageController {
    private final MessageService messageService;
    @Autowired
    public UnreadMessageController(MessageService messageService ) {
        this.messageService = messageService;
    }

    /**
     * Controller provides a list of messages for certain user
     * @param id - Users id
     * @param rank - The rank of messages importance
     * @return The page of 5 unread messages of certain user, ordered from new to old
     */
    @Operation(summary = "Получение ленты для пользователя с определённым id и минимальным rank")
    @GetMapping(value = {"unread/{id}/{rank}","unread/{id}"})
    public List<MessageOutDTO> getUsersFeed(@PathVariable long id, @PathVariable(required = false) Integer rank)
    {
        log.info("requested list for user {} ", id);
        return rank == null ? messageService.unreadMessageList(id) : messageService.unreadMessageList(id,rank);
    }


}
