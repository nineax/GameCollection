package Games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    //@TODO Homepage einbauen, bei der man die Spiele auswählen kann

    //TODO Anwendung aus dem Internet aufrufbar machen

    //@TODO eine DB anbindung mit einbringen, dass der User name nicht mehr eingegeben werden muss, Schere-Stein-Papier, Würfelspiel aus Kultur of the lamb

    //@TODO GameIdeen: 4-Gewinnt, Schiff-versenken, Mensch-Ärger-Dich-Nicht, Dame, Sudoku(generator + solver), Galgenmännchen, Errate meine Zahl
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}