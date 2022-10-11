package Games.TicTacToe.Service;

import Games.Abstract.Enums.GameStatus;
import Games.Abstract.Records.WinConditions;
import Games.Exception.GameAlreadyRunningException;
import Games.Exception.GameInTheWrongStatusException;
import Games.Exception.GameNotFoundException;
import Games.TicTacToe.Model.Game;
import Games.TicTacToe.Model.Player;
import Games.TicTacToe.Model.Turn;
import Games.TicTacToe.Storage.GameStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private WinConditions winConditions;

    public Game createGame(Player player1){
        Game game = new Game();
        game.createGame(player1);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameID) throws GameNotFoundException, GameAlreadyRunningException {
        if(!GameStorage.getInstance().getGames().containsKey(gameID))
            throw new GameNotFoundException();

        Game game = GameStorage.getInstance().getGames().get(gameID);

        if(game.getGameStatus().equals(GameStatus.running))
            throw new GameAlreadyRunningException(gameID);

        game.connectToGame(player2);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToRandomGame(Player player2) throws GameNotFoundException, GameAlreadyRunningException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(ins->ins.getGameStatus().equals(GameStatus.wantingForPlayer))
                .findFirst().orElseThrow(()->new GameNotFoundException());

        game.connectToGame(player2);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game turn(Turn turn) throws GameNotFoundException, GameInTheWrongStatusException {
        if(!GameStorage.getInstance().getGames().containsKey(turn.getGameID()))
            throw new GameNotFoundException();

        Game game = GameStorage.getInstance().getGames().get(turn.getGameID());

        if(!game.getGameStatus().equals(GameStatus.running))
            throw new GameInTheWrongStatusException();

        game.setBoard((game.setField(turn)));

        game.setWinConditions(game.isWon(turn.getFieldStatus(), game.getBoard()));

        GameStorage.getInstance().setGame(game);

        return game;
    }
}
