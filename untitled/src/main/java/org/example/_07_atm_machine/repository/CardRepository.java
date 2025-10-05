package org.example._07_atm_machine.repository;

import org.example._07_atm_machine.domain.Card;

import java.util.Optional;

public interface CardRepository {

    Card save(Card card);
    Optional<Card> findById(String cardId);
    void updatePinRetries(String cardId,int retriesLeft);
    void blockCard(String cardId);
}
