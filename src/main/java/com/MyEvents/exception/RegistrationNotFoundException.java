package com.MyEvents.exception;

public class RegistrationNotFoundException extends RuntimeException {
    public RegistrationNotFoundException(Long id) {
        super("There is no such registration of id " + id);
    }
}
