package com.MyEvents.exception;

public class RegistrationFullException extends RuntimeException {
    public RegistrationFullException(Long eventId) {
        super("There is no place left at the event of id " + eventId);
    }
}
