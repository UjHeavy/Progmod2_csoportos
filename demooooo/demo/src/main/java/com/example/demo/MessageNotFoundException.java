package com.example.demo;

class MessageNotFoundException extends RuntimeException {

    MessageNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
