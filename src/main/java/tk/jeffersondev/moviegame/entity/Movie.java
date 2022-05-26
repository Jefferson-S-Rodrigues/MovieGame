package tk.jeffersondev.moviegame.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @EqualsAndHashCode.Include
    private String imdbID;
    private String title;
    private String year;
    private String poster;
    private String imdbRating;
    private String imdbVotes;
    private String type;
}
