package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.fake.TestGameFactory;
import maszota.jacek.game.core.factories.GameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SelectDestinationGameStateTest {

    Game game;
    GameFactory gameFactory = new TestGameFactory(GameType.PulpFiction);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory,0);
    }

    @Test
    void doActions() {
    }

    @Test
    void doActionsTravelTo() throws IncorrectCommandException {
        GameState gameState = game.getSelectDestinationGameSate();
        gameState.doActions("E");
        assertEquals(game.getInCityState(), game.getState());
        assertEquals("E", game.getHeroLocation().getShortName());
    }

    @Test
    void doActionsUnknownLocation() {
        game.setState(game.getSelectDestinationGameSate());
        IncorrectCommandException ice = assertThrows(IncorrectCommandException.class,
                () -> game.doActions("P")
        );

        assertEquals("Unknown action selected", ice.getMessage());
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
    }
}