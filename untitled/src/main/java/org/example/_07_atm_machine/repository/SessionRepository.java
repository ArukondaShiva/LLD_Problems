package org.example._07_atm_machine.repository;

import org.example._07_atm_machine.domain.Session;

import java.util.Optional;

public interface SessionRepository {

    Session save(Session session);
    Optional<Session> findById(String sessionId);
    Optional<Session> findActiveByATM(String atmId);
    void endSession(String sessionId);

}
