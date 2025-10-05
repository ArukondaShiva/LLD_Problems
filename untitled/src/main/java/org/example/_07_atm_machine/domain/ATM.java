package org.example._07_atm_machine.domain;

import org.example._07_atm_machine.domain.state.ATMState;
import org.example._07_atm_machine.domain.state.IdleState;
import org.example._07_atm_machine.domain.state.SupportsNotes;
import org.example._07_atm_machine.service.CardService;
import org.example._07_atm_machine.service.SessionService;
import org.example._07_atm_machine.service.TransactionService;

import java.util.Map;

public class ATM {

    private String id;
    private String location;
    private boolean isOnline;
    private ATMState currentState;
    private CashDrawer cashDrawer;
    private Session currentSession;
    private CardService cardService;
    private SessionService sessionService;
    private TransactionService transactionService;
    private Transaction lastTransaction;


    public ATM(String id,String location){
        this.id = id;
        this.location = location;
        this.isOnline = true;
        this.currentState = new IdleState();
        this.cashDrawer = new CashDrawer(id);
    }

    public void attachServices(CardService cardService,SessionService sessionService,TransactionService transactionService){
        this.cardService = cardService;
        this.sessionService = sessionService;
        this.transactionService = transactionService;
    }


    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean online) { isOnline = online; }

    public ATMState getCurrentState() { return currentState; }
    public void setCurrentState(ATMState currentState) { this.currentState = currentState; }

    public CashDrawer getCashDrawer() { return cashDrawer; }
    public void setCashDrawer(CashDrawer cashDrawer) { this.cashDrawer = cashDrawer; }

    public Session getCurrentSession() { return currentSession; }
    public void setCurrentSession(Session currentSession) { this.currentSession = currentSession; }
    public CardService getCardService() { return cardService; }
    public SessionService getSessionService() { return sessionService; }
    public TransactionService getTransactionService() { return transactionService; }
    public Transaction getLastTransaction() { return lastTransaction; }
    public void setLastTransaction(Transaction lastTransaction) { this.lastTransaction = lastTransaction; }

    // State delegation methods (exception-first, auto next)
    public void insertCard(String cardId) {
        currentState.insertCard(this, cardId);
        autoNext();
    }

    public void ejectCard() {
        currentState.ejectCard(this);
        autoNext();
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
        autoNext();
    }

    public void selectTransaction(TransactionType type) {
        currentState.selectTransaction(this, type);
        autoNext();
    }

    public void processTransaction(long amount) {
        currentState.processTransaction(this, amount);
        autoNext();
    }

    public void processTransaction(long amount,Map<Denomination, Integer> notes) {
        // Optional overload for deposit to pass notes
        if (currentState instanceof SupportsNotes) {
            ((SupportsNotes) currentState).processTransaction(this, amount, notes);
        } else {
            currentState.processTransaction(this, amount);
        }
        autoNext();
    }

    public void endSession() {
        currentState.endSession(this);
        autoNext();
    }

    private void autoNext() {
        ATMState next = currentState.next(this);
        if (next != null && next != currentState) {
            System.out.println("[STATE] " + currentState.getClass().getSimpleName() + " → " + next.getClass().getSimpleName());
            setCurrentState(next);
        }
    }



}
