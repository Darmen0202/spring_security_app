package com.example.springsecurity.expception;

    public class RegistrationException extends RuntimeException {
        public RegistrationException(String message) {
            super(message);
        }
    }

