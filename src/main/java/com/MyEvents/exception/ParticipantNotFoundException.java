package com.MyEvents.exception;

public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException(Long id) {
        super("Participant of id " + id  + " is not found");
    }
}
