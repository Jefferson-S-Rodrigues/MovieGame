package tk.jeffersondev.moviegame.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {

    private int hits;

    private int miss;

    private long result;


    public static long calculateResult(int hits, int miss) {
        int total = hits + miss;
        return total * (int)((float)hits / total * 100);
    }
}
