package Games.TicTacToe.Model;

import Games.Abstract.Classes.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends AbstractPlayer {
    private FieldStatus symbol;
}
