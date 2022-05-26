package tk.jeffersondev.moviegame.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.exception.GameException;
import tk.jeffersondev.moviegame.utils.GameUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameServiceTest {

    @MockBean
    private GameService gameService;

    @Test
    void createTest() {
        var game = GameUtils.fakeGame();

        given(gameService.create(any(Game.class))).willReturn(GameUtils.fakeGame());

        var expectedGame = gameService.create(game);

        assertEquals(game, expectedGame);
    }

    @Test
    void findCurrentGameTest() {
        try {
            var game = GameUtils.fakeGame();
            var player = game.getPlayer();
            given(gameService.findCurrentGame(any(String.class))).willReturn(GameUtils.fakeGame());

            var expectedGame = gameService.findCurrentGame(player);

            assertEquals(game, expectedGame);

        }catch (GameException e) {
            assertEquals(true, false);
        }
    }

    @Test
    void newGameTest() {
        var game = GameUtils.fakeGame();
        var player = game.getPlayer();
        given(gameService.newGame(any(String.class))).willReturn(GameUtils.fakeGame());

        var expectedGame = gameService.newGame(player);

        assertEquals(game, expectedGame);
    }

    @Test
    void endGameTest() {
        var game = GameUtils.fakeGame();
        try {
            given(gameService.endGame(any(Long.class))).willReturn(game);

            var expectedGame = gameService.endGame(game.getId());

            assertEquals(game, expectedGame);
        } catch (GameException e) {
            assertEquals(true, false);
        }

    }
}
