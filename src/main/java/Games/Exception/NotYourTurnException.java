package Games.Exception;

public class NotYourTurnException extends Exception{
    public NotYourTurnException(){
        super("Please wait for opponent.");
    }
}
