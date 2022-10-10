package GameCollection.TicTacToe.Model;

import GameCollection.Abstract.Classes.Target;
import lombok.*;

@Getter
@Setter
public class Turn {
    private String gameID;
    private FieldStatus fieldStatus;
    private Target target;
}
