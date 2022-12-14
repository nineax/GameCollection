package Games.Contoller;

import Games.Contoller.dto.*;
import Games.Exception.*;
import Games.TicTacToe.Model.*;
import Games.TicTacToe.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {

    //@TODO GetMapping for Homepage and TicTacToe page

    private final GameService ticTacToeGameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/tic-tac-toe/create")
    public ResponseEntity<Game> createTicTacToeGame(@RequestBody Player player){
        log.info(player.getName() + " created a tic tac toe game.");
        return ResponseEntity.ok(ticTacToeGameService.createGame(player));
    }

    @PostMapping("/tic-tac-toe/connect")
    public ResponseEntity<Game> connectToTicTacToeGame(@RequestBody RequestPlayerGameID requestPlayerGameID) throws GameAlreadyRunningException, GameNotFoundException {
        log.info(requestPlayerGameID.getPlayer().getName() + " connected to a tic tac toe game. ID: " + requestPlayerGameID.getGameID());
        return ResponseEntity.ok(ticTacToeGameService.connectToGame(requestPlayerGameID.getPlayer(), requestPlayerGameID.getGameID()));
    }

    @PostMapping("/tic-tac-toe/random")
    public ResponseEntity<Game> connectToTicTacToeGameRandom(@RequestBody Player player) throws GameNotFoundException, GameAlreadyRunningException {
        log.info(player.getName() + " connected to a random tic tac toe game.");
        return ResponseEntity.ok(ticTacToeGameService.connectToRandomGame(player));
    }

    @PostMapping("/tic-tac-toe/turn")
    public ResponseEntity<Game> ticTacToeTurn(@RequestBody Turn turn) throws GameInTheWrongStatusException, GameNotFoundException, NotYourTurnException {
        log.info("---turn---");
        log.info(turn.getGameID());
        log.info("---turn---");
        log.info("In game " +  turn.getGameID() + " was made a turn");
        Game game = ticTacToeGameService.turn(turn);
        simpMessagingTemplate.convertAndSend("/topic/tic-tac-toe-game-progress/"+game.getGameID(), game);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/tic-tac-toe/reset")
    public ResponseEntity<Game> ticTacToeReset(@RequestBody JsonGetGameID jsonGetGameID) throws GameInTheWrongStatusException, GameNotFoundException {
        log.info("---reset---");
        log.info(jsonGetGameID.getGameID());
        log.info("---reset---");
        Game game = ticTacToeGameService.reset(jsonGetGameID.getGameID());
        simpMessagingTemplate.convertAndSend("/topic/tic-tac-toe-game-progress/"+game.getGameID(), game);
        return ResponseEntity.ok(game);
    }
}
