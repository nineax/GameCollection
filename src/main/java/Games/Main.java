package Games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    //@TODO alerts beim win doppelt(nur beim gewinner)
    //@TODO kein allert bei spieler eins, wenn einer dem Spiel beitritt
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}