package org.example._06_pubsubsystem.repository.impl;

import org.example._06_pubsubsystem.domain.Topic;
import org.example._06_pubsubsystem.repository.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TopicRepositoryImpl implements TopicRepository {

    private Map<String,Topic> topics = new ConcurrentHashMap<>();

    @Override
    public Topic save(Topic topic) {
        topics.put(topic.getId(),topic);
        return topic;
    }

    @Override
    public List<Topic> findAll() {
        return new ArrayList<>(topics.values());
    }

    @Override
    public Optional<Topic> findById(String topicId) {
        return Optional.ofNullable(topics.get(topicId));
    }

    @Override
    public void deleteById(String topicId) {
        topics.remove(topicId);
    }

}
