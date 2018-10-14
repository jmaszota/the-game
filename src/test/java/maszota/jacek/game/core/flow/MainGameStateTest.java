package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.fake.TestWinGameFactory;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainGameStateTest {

    Game game;
    GameFactory gameFactory = new TestWinGameFactory(GameType.PulpFiction);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory,0);
    }

    @Test
    void doActionsQuit() throws IncorrectCommandException {
        game.doActions("q");
        assertFalse(game.isEndGame());
        assertEquals(game.getGameOverState(), game.getState());
    }

    @Test
    void doActionsUnknownCommand() {

        IncorrectCommandException ice = assertThrows(IncorrectCommandException.class,
                () -> game.doActions("P")
        );

        assertEquals("Unknown action selected", ice.getMessage());
        assertEquals(game.getMainGameState(), game.getState());
    }


    @Test
    void travel() {
        Location nextLocation = game.getHeroLocation().getLinkedLocations().iterator().next();
        game.getState().travel(nextLocation);
        assertEquals(nextLocation, game.getHeroLocation());
        assertEquals(game.getInCityState(), game.getState());
    }

    @Test
    void endGame() {
        game.getState().endGame();
        assertFalse(game.isEndGame());
        assertEquals(game.getGameOverState(), game.getState());
    }
}