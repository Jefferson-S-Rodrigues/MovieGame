package tk.jeffersondev.moviegame.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.exception.ShotException;
import tk.jeffersondev.moviegame.utils.GameUtils;
import tk.jeffersondev.moviegame.utils.ShotUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShotServiceTest {

    @MockBean
    private ShotService shotService;

    @Test
    void newShot() {
        var shot = ShotUtils.fakeShot1();
        var game = GameUtils.fakeGame();

        given(shotService.newShot(any(Game.class))).willReturn(ShotUtils.fakeShot1());

        var expectedShot = shotService.newShot(game);

        assertEquals(shot.getId(), expectedShot.getId());
        assertEquals(shot.getGame().getId(), expectedShot.getGame().getId());
    }

    @Test
    void replyTest() {
        try {
            given(shotService.reply(any(Long.class), any(Boolean.class))).willReturn(ShotUtils.fakeShot2());
            var nextShot = shotService.reply(1L, Boolean.TRUE);
        } catch (ShotException e) {
            assertTrue(false);
        }
    }
}
