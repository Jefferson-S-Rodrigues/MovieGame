package tk.jeffersondev.moviegame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RepeatedMoviesException extends Exception {
    public RepeatedMoviesException() {
        super("invalid combination");
    }

    public RepeatedMoviesException(String message) {
        super(message);
    }
}
