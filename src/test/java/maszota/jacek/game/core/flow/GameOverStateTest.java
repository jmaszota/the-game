package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.fake.TestWinGameFactory;
import maszota.jacek.game.core.factories.GameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOverStateTest {

    Game game;
    GameFactory gameFactory = new TestWinGameFactory(GameType.PulpFiction);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory,0);
        game.setState(game.getGameOverState());
    }

    @Test
    void doActionsConfirm() throws IncorrectCommandException {
        GameState gameOverState = game.getGameOverState();
        gameOverState.doActions("Y");
        assertTrue(game.isEndGame());
        assertEquals(game.getGameOverState(), game.getState());
    }

    @Test
    void doActionsDecline() throws IncorrectCommandException {
        GameOverState gameOverState = new GameOverState(game);
        gameOverState.doActions("n");
        assertEquals(game.getAfterFightState(), game.getState());
        assertFalse(game.isEndGame());
    }

    @Test()
    void doActionsUnknown() throws IncorrectCommandException {
        GameOverState gameOverState = new GameOverState(game);
        gameOverState.doActions("s");
        assertEquals(game.getAfterFightState(), game.getState());
        assertFalse(game.isEndGame());
    }

    @Test
    void endGame() {
        GameState gameOverState = game.getGameOverState();
        gameOverState.endGame();
        assertTrue(game.isEndGame());
        assertEquals(game.getGameOverState(), game.getState());
    }
}