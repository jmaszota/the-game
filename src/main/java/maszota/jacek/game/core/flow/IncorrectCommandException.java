package maszota.jacek.game.core.flow;

public class IncorrectCommandException extends Exception {
    public IncorrectCommandException(String msg) {
        super(msg);
    }

    public IncorrectCommandException() {
        this("Unknown action selected");
    }
}
