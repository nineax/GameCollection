package GameCollection.TicTacToe.Model;

import GameCollection.Abstract.Enums.CheckDirection;
import GameCollection.Abstract.Enums.GameStatus;
import GameCollection.Abstract.Records.WinConditions;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Game {
    private String gameID;

    private Player player1;
    private Player player2;
    private Player winner;

    private GameStatus gameStatus;

    private final Board board = new Board();

    private WinConditions winConditions;


    public void createGame(Player player){
        gameStatus = GameStatus.creating;
        gameID = UUID.randomUUID().toString();
        player1 = player;
        gameStatus = GameStatus.wantingForPlayer;
    }

    public void connectToGame(Player player){
        player2 = player;
        gameStatus = GameStatus.running;
    }

    public void setField(Turn turn){
        board.getPlayField()[turn.getTarget().getRow()][turn.getTarget().getColumn()] = turn.getFieldStatus();
    }

    public WinConditions isWon(FieldStatus player){
        for(int row = 0; row < board.getHeight(); row++){
            for(int column = 0; row < board.getWith(); column++){
                if(board.getPlayField()[row][column].equals(player)) {
                    CheckDirection direction = checkWin(player, row, column, null, 1);
                    if (direction != null)
                        return new WinConditions(row, column, direction);
                }
            }
        }
        return null;
    }

    private CheckDirection checkWin(FieldStatus player, Integer row, Integer column, CheckDirection direction, Integer length){
        if(direction == null) {
            if (player == getStatusOnCoordinate(row, column, CheckDirection.horizontal)) {
                CheckDirection checkDirection = checkWin(player, row, column + 1, CheckDirection.horizontal, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.vertical)) {
                CheckDirection checkDirection = checkWin(player, row + 1, column, CheckDirection.vertical, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.diagonalRight)) {
                CheckDirection checkDirection = checkWin(player, row + 1, column + 1, CheckDirection.diagonalRight, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.diagonalLeft)) {
                CheckDirection checkDirection = checkWin(player, row + 1, column - 1, CheckDirection.diagonalLeft, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            return null;
        }else {
            if(length == 3)
                return direction;

            Integer checkRow = direction == CheckDirection.diagonalLeft || direction == CheckDirection.diagonalRight || direction == CheckDirection.horizontal ? row + 1 : row;
            Integer checkColumn = direction == CheckDirection.horizontal || direction == CheckDirection.diagonalRight ? column + 1 : (direction == CheckDirection.diagonalLeft ? column - 1 : column);
            return checkWin(player, checkRow, checkColumn, direction, length +1);
        }
    }

    private FieldStatus getStatusOnCoordinate(Integer row, Integer column, CheckDirection direction){
        Integer checkRow = direction == CheckDirection.diagonalLeft || direction == CheckDirection.diagonalRight || direction == CheckDirection.horizontal ? row + 1 : row;
        Integer checkColumn = direction == CheckDirection.horizontal || direction == CheckDirection.diagonalRight ? column + 1 : (direction == CheckDirection.diagonalLeft ? column - 1 : column);

        if(row >= board.getHeight() || row < 0 || column >= board.getWith() || column < 0 || checkRow >= board.getHeight() || checkRow < 0 || checkColumn >= board.getWith() || checkColumn < 0)
            return null;

        return board.getPlayField()[checkRow][checkColumn];
    }
}
