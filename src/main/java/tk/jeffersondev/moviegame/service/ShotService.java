package tk.jeffersondev.moviegame.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.jeffersondev.moviegame.entity.Game;
import tk.jeffersondev.moviegame.entity.Shot;
import tk.jeffersondev.moviegame.exception.RepeatedMoviesException;
import tk.jeffersondev.moviegame.exception.ShotException;
import tk.jeffersondev.moviegame.repository.ShotRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShotService {

    @Autowired
    private final ShotRepository shotRepository;

    @Autowired
    private final MovieService movieService;

    public boolean combUsed(String movies) {
        var movie = shotRepository.findByMovies(movies);

        return movie.isPresent();
    }

    public Shot newShot(Game game) {

        while (true) {
            try {
                var shot = new Shot(movieService.randomMovie(), movieService.randomMovie(), game);
                if (combUsed(shot.getMovies())) continue;
                return shotRepository.save(shot);
            } catch (RepeatedMoviesException e) {
                e.getStackTrace();
            }
        }
    }

    public Shot reply(Long id, boolean vote1) throws ShotException {
        var shot = shotRepository.findById(id).orElseThrow(() -> new ShotException(id));
        if (shot.getCorrect() != null) throw new ShotException((id));

        shot.setVote1(vote1);
        shot.setCorrect();

        return shotRepository.save(shot);
    }

    public int[] results(Game game) {
        int[] counts = {0, 0};

        var shots = shotRepository.findByGame(game);

        shots.forEach(shot -> {
            if (shot.getCorrect() != null) {
                if (Boolean.TRUE.equals(shot.getCorrect())) {
                    counts[0]++;
                } else {
                    counts[1]++;
                }
            }
        });

        return counts;
    }
}
