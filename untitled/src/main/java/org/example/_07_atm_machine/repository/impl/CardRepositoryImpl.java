package org.example._07_atm_machine.repository.impl;

import org.example._07_atm_machine.domain.Card;
import org.example._07_atm_machine.repository.CardRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CardRepositoryImpl implements CardRepository {

    private Map<String,Card> cardStore = new ConcurrentHashMap<>();

    @Override
    public Card save(Card card) {
        cardStore.put(card.getId(),card);
        return card;
    }

    @Override
    public Optional<Card> findById(String cardId) {
        return Optional.ofNullable(cardStore.get(cardId));
    }

    @Override
    public void updatePinRetries(String cardId, int retriesLeft) {
        Optional<Card> cardOpt = findById(cardId);
        if(cardOpt.isPresent()){
            cardOpt.get().setPinRetriesLeft(retriesLeft);
        }
    }

    @Override
    public void blockCard(String cardId) {
        Optional<Card> cardOpt = findById(cardId);
        if(cardOpt.isPresent()){
            cardOpt.get().setBlocked(true);
        }
    }
}
