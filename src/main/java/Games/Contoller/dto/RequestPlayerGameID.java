package Games.Contoller.dto;

import Games.TicTacToe.Model.Player;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPlayerGameID {
    private Player player;
    private String gameID;
}
