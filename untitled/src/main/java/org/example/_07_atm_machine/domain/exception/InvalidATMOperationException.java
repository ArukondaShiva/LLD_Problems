package org.example._07_atm_machine.domain.exception;

public class InvalidATMOperationException extends RuntimeException{

    public InvalidATMOperationException(String message){
        super(message);
    }

}
