package ua.hudyma.exceptions;

public class SpellNotFoundException extends RuntimeException {
    public SpellNotFoundException(ClassNotFoundException message) {
        super(message);
    }
}
