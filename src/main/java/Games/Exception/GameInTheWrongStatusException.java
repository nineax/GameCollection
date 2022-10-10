package Games.Exception;

public class GameInTheWrongStatusException extends Exception{
    public GameInTheWrongStatusException(){
        super("This game is in the wrong status.");
    }
}
