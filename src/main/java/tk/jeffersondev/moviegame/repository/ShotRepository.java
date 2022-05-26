package tk.jeffersondev.moviegame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.entity.Shot;

import java.util.List;
import java.util.Optional;

public interface ShotRepository extends JpaRepository<Shot, Long> {

    Optional<Shot> findByMovies(String movies);

    List<Shot> findByGame(Game game);
}
