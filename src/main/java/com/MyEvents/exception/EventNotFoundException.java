package com.MyEvents.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(Long id) {
        super("Event of id " + id  + " is not found");
    }

    public EventNotFoundException(String name) {
        super("Event of name " + name  + " is not found");
    }
}
