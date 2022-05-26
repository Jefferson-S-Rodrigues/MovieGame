package tk.jeffersondev.moviegame.utils;

import tk.jeffersondev.moviegame.entity.Movie;

public class MovieUtils {

    private static final String IMDB_ID1 = "tt80923456";
    private static final String IMDB_RATING1=  "7.4";
    private static final String IMDB_VOTES1 = "9,909";
    private static final String POSTER1 = "N/A";
    private static final String TITLE1 = "Test movie 1";
    private static final String TYPE = "movie";
    private static final String YEAR1 = "1987";

    private static final String IMDB_ID2 = "tt80923436";
    private static final String IMDB_RATING2=  "4.7";
    private static final String IMDB_VOTES2 = "9,908";
    private static final String POSTER2 = "N/A";
    private static final String TITLE2 = "Test movie 2";
    private static final String YEAR2 = "1986";

    private static final String IMDB_ID3 = "tt30923436";
    private static final String IMDB_RATING3=  "3.7";
    private static final String IMDB_VOTES3 = "9,308";
    private static final String POSTER3 = "N/A";
    private static final String TITLE3 = "Test movie 3";
    private static final String YEAR3 = "1983";

    private static final String IMDB_ID4 = "tt80423436";
    private static final String IMDB_RATING4=  "4.4";
    private static final String IMDB_VOTES4 = "9,408";
    private static final String POSTER4 = "N/A";
    private static final String TITLE4 = "Test movie 4";
    private static final String YEAR4 = "1984";

    public static Movie fakeMovie1() {
        return Movie.builder()
                .imdbID(IMDB_ID1)
                .imdbRating(IMDB_RATING1)
                .imdbVotes(IMDB_VOTES1)
                .poster(POSTER1)
                .title(TITLE1)
                .type(TYPE)
                .year(YEAR1)
                .build();
    }

    public static Movie fakeMovie2() {
        return Movie.builder()
                .imdbID(IMDB_ID2)
                .imdbRating(IMDB_RATING2)
                .imdbVotes(IMDB_VOTES2)
                .poster(POSTER2)
                .title(TITLE2)
                .type(TYPE)
                .year(YEAR2)
                .build();
    }

    public static Movie fakeMovie3() {
        return Movie.builder()
                .imdbID(IMDB_ID3)
                .imdbRating(IMDB_RATING3)
                .imdbVotes(IMDB_VOTES3)
                .poster(POSTER3)
                .title(TITLE3)
                .type(TYPE)
                .year(YEAR3)
                .build();
    }

    public static Movie fakeMovie4() {
        return Movie.builder()
                .imdbID(IMDB_ID4)
                .imdbRating(IMDB_RATING4)
                .imdbVotes(IMDB_VOTES4)
                .poster(POSTER4)
                .title(TITLE4)
                .type(TYPE)
                .year(YEAR4)
                .build();
    }
}
