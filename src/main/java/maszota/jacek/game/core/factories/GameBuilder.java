package maszota.jacek.game.core.factories;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.plays.HarryPotterGameFactory;
import maszota.jacek.game.plays.PulpFictionGameFactory;

public class GameBuilder {
    public static GameFactory getGameFactory(GameType gameType) {

        GameFactory gameFactory = null;

        switch (gameType) {
            case HarryPotter:
                gameFactory = new HarryPotterGameFactory(gameType);
                break;
            case PulpFiction:
            default:
                gameFactory = new PulpFictionGameFactory(gameType);
                break;
        }


        return gameFactory;
    }
}
