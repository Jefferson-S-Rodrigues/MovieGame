package tk.jeffersondev.moviegame.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayDTO {

    private Long shotId;

    private boolean vote1;
}
