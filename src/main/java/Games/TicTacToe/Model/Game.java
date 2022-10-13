package Games.TicTacToe.Model;

import Games.Abstract.Enums.*;
import Games.Abstract.Records.*;
import Games.Exception.NotYourTurnException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class Game {

    private static Integer counter = 0;
    private String gameID;

    private Player currentPlayer;

    private Player player1;
    private Player player2;
    private Player winner;

    private GameStatus gameStatus;

    private Board board;

    private WinConditions winConditions;


    public void createGame(Player player){
        gameStatus = GameStatus.creating;
        gameID = UUID.randomUUID().toString();
        player1 = player;
        player1.setSymbol(FieldStatus.X);
        currentPlayer = player1;
        board = new Board();
        gameStatus = GameStatus.wantingForPlayer;
    }

    public void connectToGame(Player player){
        player2 = player;
        player2.setSymbol(FieldStatus.O);
        gameStatus = GameStatus.running;
    }

    public Board setField(Turn turn) throws NotYourTurnException {
        if(!turn.getFieldStatus().equals(currentPlayer.getSymbol()))
            throw new NotYourTurnException();
        board.getPlayField()[turn.getTarget().getRow()][turn.getTarget().getColumn()] = turn.getFieldStatus();
        return board;
    }

    public WinConditions isWon(FieldStatus symbol, Board board){
        for(int row = 0; row < board.getHeight(); row++){
            for(int column = 0; column < board.getWith(); column++){
                if(board.getPlayField()[row][column].equals(symbol)) {
                    CheckDirection direction = checkWin(symbol, row, column, null, 1);
                    if (direction != null) {
                        winner = currentPlayer;
                        return new WinConditions(row, column, direction, 3);
                    }
                }
            }
        }
        return null;
    }
    private CheckDirection checkWin(FieldStatus player, Integer row, Integer column, CheckDirection direction, Integer length){
        if(direction == null) {
            if (player == getStatusOnCoordinate(row, column, CheckDirection.horizontal)) {
                log.info("a");
                CheckDirection checkDirection = checkWin(player, row, column + 1, CheckDirection.horizontal, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.vertical)) {
                log.info("---bbb---");
                CheckDirection checkDirection = checkWin(player, row + 1, column, CheckDirection.vertical, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.diagonalRight)) {
                log.info("c");
                CheckDirection checkDirection = checkWin(player, row + 1, column + 1, CheckDirection.diagonalRight, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            if (player == getStatusOnCoordinate(row, column, CheckDirection.diagonalLeft)) {
                log.info("d");
                CheckDirection checkDirection = checkWin(player, row + 1, column - 1, CheckDirection.diagonalLeft, length + 1);
                if(checkDirection != null)
                    return checkDirection;
            }
            return null;
        }else {
            if(length == 3)
                return direction;

            if(player != getStatusOnCoordinate(row, column, direction))
                return null;

            Integer checkRow = direction == CheckDirection.diagonalLeft || direction == CheckDirection.diagonalRight || direction == CheckDirection.vertical ? row + 1 : row;
            Integer checkColumn = direction == CheckDirection.horizontal || direction == CheckDirection.diagonalRight ? column + 1 : (direction == CheckDirection.diagonalLeft ? column - 1 : column);
            return checkWin(player, checkRow, checkColumn, direction, length + 1);
        }
    }

    private FieldStatus getStatusOnCoordinate(Integer row, Integer column, CheckDirection direction){
        Integer checkRow = direction == CheckDirection.diagonalLeft || direction == CheckDirection.diagonalRight || direction == CheckDirection.vertical ? row + 1 : row;
        Integer checkColumn = direction == CheckDirection.horizontal || direction == CheckDirection.diagonalRight ? column + 1 : (direction == CheckDirection.diagonalLeft ? column - 1 : column);

        if(row >= board.getHeight() || row < 0 || column >= board.getWith() || column < 0 || checkRow >= board.getHeight() || checkRow < 0 || checkColumn >= board.getWith() || checkColumn < 0)
            return null;

        return board.getPlayField()[checkRow][checkColumn];
    }

    public void switchCurrentPlayer(){
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }
}
