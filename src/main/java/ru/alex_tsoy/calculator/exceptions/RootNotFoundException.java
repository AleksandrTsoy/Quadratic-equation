package ru.alex_tsoy.calculator.exceptions;

public class RootNotFoundException extends RuntimeException {

    public static final String ROOT_NOT_FOUND = "Root equation is not found.";

    public RootNotFoundException() {
        super(ROOT_NOT_FOUND);
    }
}
