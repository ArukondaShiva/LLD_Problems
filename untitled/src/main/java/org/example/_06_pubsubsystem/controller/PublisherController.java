package org.example._06_pubsubsystem.controller;

import org.example._06_pubsubsystem.domain.Message;
import org.example._06_pubsubsystem.service.PublisherService;

public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public Message publishMessage(String topicId, String content) {
        return publisherService.publishMessage(topicId, content);
    }

}
