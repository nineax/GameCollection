package Games;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class Main {
    //TODO Anwendung aus dem Internet aufrufbar machen
    //@TODO spring boot auf den port lauschen lassen, dass die anfragen behandelt werden können

    //@TODO eine DB anbindung mit einbringen, dass der User name nicht mehr eingegeben werden muss

    //@TODO GameIdeen: 4-Gewinnt, Schiff-versenken, Mensch-Ärger-Dich-Nicht, Dame, Sudoku(generator + solver), Galgenmännchen, Errate meine Zahl, Schere-Stein-Papier, Würfelspiel aus Kultur of the lamb, Einarmiger Bandit mit einsatzt usw.
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}