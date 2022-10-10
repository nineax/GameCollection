package GameCollection.Exception;

public class GameNotFoundException extends Exception{
    public GameNotFoundException(){
        super("No game was found");
    }
}
