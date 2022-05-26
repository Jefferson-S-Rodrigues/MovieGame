package tk.jeffersondev.moviegame.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.jeffersondev.moviegame.entity.Movie;
import tk.jeffersondev.moviegame.util.JsonBodyHandler;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Random;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final String urlBase = "http://www.omdbapi.com/";
    private final String apikey = System.getenv("APIKEY");
    private final Random rand = new Random(System.currentTimeMillis());

    protected Movie randomMovie() {


        while (true) {
            var strId = movieId();

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(String.format("%s?i=tt%s&apikey=%s", urlBase, strId, apikey)))
                        .build();

                var movie = HttpClient.newHttpClient()
                        .send(request, new JsonBodyHandler<>(Movie.class))
                        .body()
                        .get();

                if (movie.getType().equals("movie")) {
                    return movie;
                }
            } catch (NullPointerException | IOException | UncheckedIOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }


        }
    }

    private String movieId() {
        var strId = String.valueOf(rand.nextInt(2000000));
        while (strId.length() < 7) {
            strId = String.format("0%s", strId);
        }
        return strId;
    }

}
