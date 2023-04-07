package com.exchangeinformant.feed.controllers;

import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.dto.MessagePreDTO;
import com.exchangeinformant.feed.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for getting all messages
 */
@Slf4j
@RestController
@RequestMapping("/feed")
@Tag(name = "Контроллер ленты", description = "Получение всех сообщений")
public class AllMessageController {
    private final MessageService messageService;
    @Autowired
    public AllMessageController(MessageService messageService ) {
        this.messageService = messageService;
    }

    /**
     * Controller provides a list of messages for certain user
     * @param id - Users id
     * @param rank - The rank of messages importance
     * @param page - number of required page
     * @return The page of 5 unread messages of certain user, ordered from new to old
     */
    @Operation(summary = "Получение полного списка")
    @GetMapping(value = {"/{id}/{rank}","/{id}"})
    public List<MessagePreDTO> getUserAll(@PathVariable long id, @PathVariable(required = false) Integer rank,  @RequestParam int page)
    {
        log.info("requested list for user {} ", id);
        return rank == null ? messageService.allMessageList(id,page) : messageService.allMessageList(id,rank,page);
    }

    /**
     * Controller for getting one full message. Here used MessageOutTDO but could be changed in future
     * @param messageId - Id of required full message
     * @return  message
     */
    @Operation(summary = "Получение конкретного сообщения из списка")
    @GetMapping("full/{messageId}/")
    public MessageOutDTO fullMessage(@PathVariable long messageId) {
        return messageService.getFullMessage(messageId);
    }

}
