package Games.Exception;

public class GameAlreadyRunningException extends Exception{
    public GameAlreadyRunningException(String gameID){
        super("The game with the id " + gameID + " is already running.");
    }
}
