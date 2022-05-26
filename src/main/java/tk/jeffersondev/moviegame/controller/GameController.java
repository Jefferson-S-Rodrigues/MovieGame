package tk.jeffersondev.moviegame.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tk.jeffersondev.moviegame.dto.request.PlayDTO;
import tk.jeffersondev.moviegame.dto.response.GameResultDTO;
import tk.jeffersondev.moviegame.dto.response.ResultDTO;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.entity.Shot;
import tk.jeffersondev.moviegame.exception.GameException;
import tk.jeffersondev.moviegame.exception.ShotException;
import tk.jeffersondev.moviegame.service.GameService;
import tk.jeffersondev.moviegame.service.ShotService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/game")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {

    private final GameService gameService;

    private final ShotService shotService;

    @GetMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Shot restart(Authentication authentication) {

        return newGame(authentication.getName());
    }

    @PatchMapping("/finish")
    public ResponseEntity<ResultDTO> finishGame(Authentication authentication) {
        try {
            var currentGame = gameService.findCurrentGame(authentication.getName());
            gameService.endGame(currentGame.getId());

            return new ResponseEntity<>(getResult(currentGame), HttpStatus.OK);
        } catch (GameException e) {
            return new ResponseEntity<>(ResultDTO.builder().build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/play")
    public ResponseEntity<?> play(@RequestBody PlayDTO playDTO, Authentication authentication) {

        try {
            var currentShot = shotService.reply(playDTO.getShotId(), playDTO.isVote1());
            var currentGame = currentShot.getGame();

            if (shotService.results(currentGame)[1] > 2) {
                gameService.endGame(currentGame.getId());

                return new ResponseEntity<>(getResult(currentGame), HttpStatus.ACCEPTED);
            }

            return new ResponseEntity<>(shotService.newShot(currentGame), Boolean.TRUE.equals(currentShot.getCorrect()) ? HttpStatus.OK : HttpStatus.NO_CONTENT);
        } catch (ShotException | GameException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/ranking")
    public List<GameResultDTO> getRanking() {
        return ranking();
    }

    private ResultDTO getResult(Game game) {
        var result = shotService.results(game);

        return ResultDTO.builder()
                .hits(result[0])
                .miss(result[1])
                .result(ResultDTO.calculateResult(result[0], result[1]))
                .build();
    }

    private Shot newGame(String player) {
        try {
            var currentGame = gameService.findCurrentGame(player);
            gameService.endGame(currentGame.getId());
        } catch (GameException ignored) {
        }

        var nextGame = gameService.newGame(player);

        return nextShot(nextGame);
    }

    private Shot nextShot(Game game) {
        return shotService.newShot(game);
    }

    private List<GameResultDTO> ranking() {
        List<Game> games = gameService.getAll();
        List<GameResultDTO> gamesRank = new ArrayList<>();

        games.forEach(game -> {
            int[] r = shotService.results(game);
            gamesRank.add(GameResultDTO.builder()
                    .game(game)
                    .result(ResultDTO.calculateResult(r[0], r[1]))
                    .build());
        });

        Collections.sort(gamesRank, (o1, o2) -> o1.getResult().compareTo(o2.getResult()));
        while (gamesRank.size() > 10) {
            gamesRank.remove(0);
        }

        return gamesRank;

    }
}
