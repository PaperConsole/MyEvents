package com.MyEvents.exception;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(Long userId, Long evetId) {
        super("User of id " +userId +" is already registered at the event of" +evetId);
    }
}
