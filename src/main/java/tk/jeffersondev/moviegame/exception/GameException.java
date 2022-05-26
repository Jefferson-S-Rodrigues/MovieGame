package tk.jeffersondev.moviegame.exception;

public class GameException extends Exception {
    public GameException() {
        super("Game does not exist");
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(Long id) {
        super(String.format("Game with ID %s not found!", id.toString()));
    }
}
