package tk.jeffersondev.moviegame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShotException extends Exception{

    public ShotException(Long id) { super(String.format("Game with ID %s not found.", String.valueOf(id))); }
}
