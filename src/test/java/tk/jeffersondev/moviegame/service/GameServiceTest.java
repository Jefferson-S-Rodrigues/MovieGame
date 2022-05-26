package tk.jeffersondev.moviegame.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.utils.GameUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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

}
