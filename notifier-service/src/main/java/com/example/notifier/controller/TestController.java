//package com.example.notifier.controller;
//
//import com.example.notifier.util.MessageBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TestController {
//
//    private final MessageInService messageInService;
//
//    public TestController(MessageInService messageInService) {
//        this.messageInService = messageInService;
//    }
//
//    @GetMapping("/api")
//    public ResponseEntity<MessageBuilder> test() {
////        messageService.saveMessage(new Message("title-", "text-", "platform-"));
////        System.out.println("====================================================================");
////        Message message = Message.builder()
////                .id(1L)
////                .title("t-title")
////                .text("t-text")
////                .platform("SMS")
////                .build();
////        System.out.println(message);
//        messageInService.testTimer();
//        return ResponseEntity.ok().build();
//    }
//}
