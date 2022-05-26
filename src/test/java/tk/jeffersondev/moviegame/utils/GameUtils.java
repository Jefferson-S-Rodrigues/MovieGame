package tk.jeffersondev.moviegame.utils;

import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.enums.Status;

public class GameUtils {

    private static final Long ID = 1L;
    private static final String PLAYER = "Tester";

    public static Game fakeGame() {
        return Game.builder()
                .id(ID)
                .player(PLAYER)
                .status(Status.PLAYING)
                .build();
    }
}
