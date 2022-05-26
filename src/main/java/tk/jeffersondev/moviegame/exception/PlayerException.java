package tk.jeffersondev.moviegame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerException extends Exception {

    public PlayerException(String username) {
        super(String.format("Player with username %s not found!", username));
    }
}
