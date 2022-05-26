package tk.jeffersondev.moviegame.entity;

import lombok.Getter;
import tk.jeffersondev.moviegame.exception.RepeatedMoviesException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Table(name = "SHOT")
public class Shot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String movies;
    @ManyToOne
    private Game game;
    @Column
    private Boolean vote1;
    @Column
    private Boolean correct;
    @Column
    private int answer;

    @Transient
    private Movie movie1;
    @Transient
    private Movie movie2;

    public Shot() {
    }

    public Shot(Movie movie1, Movie movie2, Game game) throws RepeatedMoviesException {
        this.game = game;
        insertMovies(movie1, movie2);
        setAnswer();
    }


    public Shot(Long id, Movie movie1, Movie movie2, Boolean vote1, Boolean okay, Game game) {
        this.id = id;
        this.movie1 = movie1;
        this.movie2 = movie2;
        this.game = game;
        this.vote1 = vote1;
        this.correct = okay;
    }

    public void setVote1(Boolean vote1) {
        this.vote1 = vote1;
    }

    public boolean setCorrect() {

        if (this.answer == 0) {
            this.correct = true;
        } else {
            var first = this.answer > 0;

            this.correct = first == this.vote1;
        }

        return this.correct;
    }

    private void setAnswer() {
        float rating1 = this.movie1.getImdbRating().equals("N/A") ? 1 : Float.parseFloat(this.movie1.getImdbRating());
        float rating2 = this.movie2.getImdbRating().equals("N/A") ? 1 : Float.parseFloat(this.movie2.getImdbRating());
        float votes1 = this.movie1.getImdbVotes().equals("N/A") ? 1 : Integer.parseInt(this.movie1.getImdbVotes());
        float votes2 = this.movie2.getImdbVotes().equals("N/A") ? 1 : Integer.parseInt(this.movie2.getImdbVotes());

        if ((rating1 * votes1) == (rating2 * votes2)) {
            this.answer = 0;
        } else if ((rating1 * votes1) > (rating2 * votes2)) {
            this.answer = 1;
        } else {
            this.answer = -1;
        }

    }

    private void insertMovies(Movie movie1, Movie movie2) throws RepeatedMoviesException {
        var compare = movie1.getImdbID().compareTo(movie2.getImdbID());
        if (compare == 0) {
            throw new RepeatedMoviesException("Repeated Movie");
        }
        if (compare > 0) {
            this.movie1 = movie1;
            this.movie2 = movie2;
        } else {
            this.movie1 = movie2;
            this.movie2 = movie1;
        }
        this.movies = String.format("%s%s", this.movie1.getImdbID(), this.movie2.getImdbID());
    }
}
