package com.MyEvents.exception;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(long id) {
        super("Location of id " + id + " not found");
    }
}
