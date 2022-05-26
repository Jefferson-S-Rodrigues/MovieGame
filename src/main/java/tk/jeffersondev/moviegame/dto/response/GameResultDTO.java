package tk.jeffersondev.moviegame.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.jeffersondev.moviegame.entity.Game;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResultDTO {

    private Game game;
    private Long result;
}
