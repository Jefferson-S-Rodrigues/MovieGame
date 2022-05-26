package tk.jeffersondev.moviegame.utils;

import tk.jeffersondev.moviegame.entity.Shot;

public class ShotUtils {

    private static long ID1 = 1L;
    private static long ID2 = 2L;

    public static Shot fakeShot1() {
        return new Shot(ID1, MovieUtils.fakeMovie1(), MovieUtils.fakeMovie2(), Boolean.TRUE, Boolean.TRUE, GameUtils.fakeGame());
    }

    public static Shot fakeShot2() {
        return new Shot(ID2, MovieUtils.fakeMovie3(), MovieUtils.fakeMovie4(), Boolean.FALSE, Boolean.FALSE, GameUtils.fakeGame());
    }
}
