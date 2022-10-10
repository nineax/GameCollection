package GameCollection.TicTacToe.Model;

import GameCollection.Abstract.Classes.AbstractBoard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board extends AbstractBoard {
    private Integer with = 3;
    private Integer height = 3;
    private FieldStatus[][] playField;

    public Board(){
        playField = new FieldStatus[with][height];
        for (int row = 0; row < height; row++){
            for(int column = 0; column < with; column++){
                playField[row][column] = FieldStatus.empty;
            }
        }
    }
}
