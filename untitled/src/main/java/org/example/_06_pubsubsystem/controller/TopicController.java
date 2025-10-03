package org.example._06_pubsubsystem.controller;

import org.example._06_pubsubsystem.domain.Topic;
import org.example._06_pubsubsystem.service.TopicService;

import java.util.List;

public class TopicController {

    private TopicService topicService;

    public TopicController(TopicService topicService){
        this.topicService = topicService;
    }

    public Topic createTopic(String name) {
        // Validate
        return topicService.createTopic(name);
    }

    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    public void deactivateTopic(String topicId) {
        topicService.deactivateTopic(topicId);
    }

}
