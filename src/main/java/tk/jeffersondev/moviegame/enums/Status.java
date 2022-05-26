package tk.jeffersondev.moviegame.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    PLAYING("playing"),
    ENDED("ended");

    private final String description;
}
