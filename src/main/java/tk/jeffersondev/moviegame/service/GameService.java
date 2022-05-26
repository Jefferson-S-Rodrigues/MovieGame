package tk.jeffersondev.moviegame.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.enums.Status;
import tk.jeffersondev.moviegame.exception.GameException;
import tk.jeffersondev.moviegame.repository.GameRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameService {

    private final GameRepository gameRepository;

    public Game create(Game game) {
        return gameRepository.save(game);
    }

    public Game findById(Long id) throws GameException {
        return gameRepository.findById(id).orElseThrow(() -> new GameException(id));
    }

    public Game findCurrentGame(String player) throws GameException {
        var games = gameRepository.findByPlayerAndStatus(player, Status.PLAYING);
        if (games.isEmpty()) throw new GameException("Player without games");
        return games.get(0);
    }

    public Game newGame(String player) {
        var games = gameRepository.findByPlayerAndStatus(player, Status.PLAYING);
        if (!games.isEmpty()) {
            games.forEach(game -> {
                try {
                    endGame(game.getId());
                } catch (GameException e) {
                    throw new RuntimeException(e.getMessage());
                }
            });
        }
        return create(Game.builder()
                .player(player)
                .status(Status.PLAYING)
                .build());
    }

    public List<Game> listAll() {
        return gameRepository.findAll();
    }

    public Game endGame(Long id) throws GameException {
        var updatedGame = gameRepository.findById(id)
                .orElseThrow(() -> new GameException(id));

        updatedGame.setStatus(Status.ENDED);

        return gameRepository.save(updatedGame);
    }

    public List<Game> getAll(){
        return gameRepository.findAll();
    }

}
