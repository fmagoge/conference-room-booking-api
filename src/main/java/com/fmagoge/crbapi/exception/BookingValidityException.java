package com.fmagoge.crbapi.exception;

public class BookingValidityException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookingValidityException() {
        super();
    }

    public BookingValidityException(String message) {
        super(message);
    }
}
