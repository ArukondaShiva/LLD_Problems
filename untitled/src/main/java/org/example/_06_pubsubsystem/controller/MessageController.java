package org.example._06_pubsubsystem.controller;

import org.example._06_pubsubsystem.service.MessageService;

public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public void acknowledgeMessage(String messageId, String subscriberId) {
        messageService.acknowledgeMessage(messageId, subscriberId);
    }
}
