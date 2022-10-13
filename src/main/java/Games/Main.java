package Games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    //@TODO Homepage einbauen, bei der man die Spiele auswählen kann (homepage -> page tic tac toe)
    //@TODO design der buttons von Tic Tac Toe anpassen

    //@TODO kein alert bei spieler eins, wenn einer dem Spiel beitritt

    //@TODO Refector
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}