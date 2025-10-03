package org.example._06_pubsubsystem.repository.impl;

import org.example._06_pubsubsystem.domain.Message;
import org.example._06_pubsubsystem.repository.MessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessageRepositoryImpl implements MessageRepository {

    private Map<String,Message> messages = new HashMap<>();

    @Override
    public Message save(Message message) {
        messages.put(message.getId(),message);
        return message;
    }

    @Override
    public Optional<Message> findById(String messageId) {
        return Optional.ofNullable(messages.get(messageId));
    }

    @Override
    public void deleteById(String messageId) {
        messages.remove(messageId);
    }
}
