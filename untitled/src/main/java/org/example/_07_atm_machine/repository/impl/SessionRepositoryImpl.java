package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.Session;
import org.example._07_atm_machine.repository.SessionRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRepositoryImpl implements SessionRepository {

    private Map<String, Session> sessionStore = new ConcurrentHashMap<>();


    @Override
    public Session save(Session session) {
        sessionStore.put(session.getId(),session);
        return session;
    }

    @Override
    public Optional<Session> findById(String sessionId) {
        return Optional.ofNullable(sessionStore.get(sessionId));
    }

    @Override
    public Optional<Session> findActiveByATM(String atmId) {
        return sessionStore.values().stream()
                .filter(session -> atmId.equals(session.getAtmId()) && session.isActive())
                .findFirst();
    }

    @Override
    public void endSession(String sessionId) {
        Optional<Session> sessionOpt = findById(sessionId);
        if(sessionOpt.isPresent()){
            sessionOpt.get().endSession();
        }
    }
}
