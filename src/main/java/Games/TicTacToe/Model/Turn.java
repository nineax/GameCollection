package Games.TicTacToe.Model;

import Games.Abstract.Classes.*;
import lombok.*;

@Getter
@Setter
public class Turn {
    private String gameID;
    private FieldStatus fieldStatus;
    private Target target;
}
