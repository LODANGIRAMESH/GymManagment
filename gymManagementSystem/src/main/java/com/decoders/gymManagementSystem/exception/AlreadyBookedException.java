package com.decoders.gymManagementSystem.exception;

public class AlreadyBookedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AlreadyBookedException() {
        super("You have already booked a slot in this time slot.");
    }
}
