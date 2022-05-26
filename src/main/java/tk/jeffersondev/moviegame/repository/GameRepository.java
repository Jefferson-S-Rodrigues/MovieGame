package tk.jeffersondev.moviegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.enums.Status;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByPlayerAndStatus(String player, Status status);
}
