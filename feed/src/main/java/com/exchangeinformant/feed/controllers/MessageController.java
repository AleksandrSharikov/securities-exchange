package com.exchangeinformant.feed.controllers;



import com.exchangeinformant.feed.dto.MessageOutDTO;
import com.exchangeinformant.feed.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Конотроллеры
 */
@Slf4j
@RestController
@RequestMapping("/feed")
@Tag(name = "Контроллер ленты", description = "Получение и передача сообщений ленты")
public class MessageController {


    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService ) {
        this.messageService = messageService;
    }

    /**
     * Controller provides a list of messages for certain user
     * @param id - Users id
     * @param rank - The rank of messages importance
     * @return The page of 5 unread messages of certain user, ordered from new to old
     */
    @Operation(summary = "Получение ленты для пользователя с определённым id и минимальным rank")
    @GetMapping(value = {"/{id}/{rank}","/{id}"})
    public List<MessageOutDTO> getUsersFeed(@PathVariable long id, @PathVariable(required = false) Integer rank)
    {
        log.info("requested list for user {} ", id);
        return rank == null ? messageService.unreadMessageList(id) : messageService.unreadMessageList(id,rank);
    }

}
