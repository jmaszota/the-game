package maszota.jacek.game;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameBuilder;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.flow.Game;
import maszota.jacek.game.core.flow.IncorrectCommandException;

import java.util.Scanner;

public class ApplicationStarter {

    public static void main(String[] args) {

        if (args.length != 1) {
            printHelp();
            System.exit(1);
        } else {

        }

        GameType gameType = GameType.valueOf(args[0]);

        if (gameType == null) {
            printHelp();
            System.exit(1);
        }

        GameFactory gameFactory = GameBuilder.getGameFactory(gameType);

        Game theGame = new Game(gameFactory,500);
        Scanner in = new Scanner(System.in);

        do {
            theGame.displayActions();
            String keyRead = in.nextLine();

            try {

                theGame.doActions(keyRead);

            } catch (IncorrectCommandException e) {
                System.out.println(String.format("%s. Try one more time", e.getMessage()));
            }
        } while (!theGame.isEndGame());

    }

    private static void printHelp() {
        System.out.println("Usage: java -jar game.jar <gametype>");
        System.out.println("Available game types:");
        for (GameType gameType : GameType.values()) {
            System.out.println(String.format("%15s", gameType.name()));
        }
    }
}
