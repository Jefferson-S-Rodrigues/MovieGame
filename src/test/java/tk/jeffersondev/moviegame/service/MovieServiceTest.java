package tk.jeffersondev.moviegame.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tk.jeffersondev.moviegame.utils.MovieUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieServiceTest {

    @MockBean
    private MovieService movieService;

    @Test
    void randomMovieTest() {

        given(movieService.randomMovie()).willReturn(MovieUtils.fakeMovie1());

        var movie = movieService.randomMovie();

        assertEquals("movie", movie.getType());
        assertEquals(10, movie.getImdbID().length());
        assertFalse(movie.getTitle().isEmpty());
    }
}
