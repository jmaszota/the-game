package maszota.jacek.game.core.flow;

import maszota.jacek.game.fake.TestGameFactory;
import maszota.jacek.game.fake.TestWinGameFactory;
import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameBuilder;
import maszota.jacek.game.core.factories.GameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InCityStateTest {

    Game game;
    GameFactory gameFactory = GameBuilder.getGameFactory(GameType.HarryPotter);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory,0);
    }

    @Test
    public void doActionsQuit() throws IncorrectCommandException {

        GameState gameState = new InCityState(game);
        gameState.doActions("q");

        assertEquals(game.getMainGameState(), game.getState());
        assertFalse(game.isEndGame());
    }

    @Test
    public void doActionsWalkAway() throws IncorrectCommandException {

        GameState gameState = new InCityState(game);
        gameState.doActions("w");

        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        assertFalse(game.isEndGame());

    }

    @Test
    public void doActionsFight() throws IncorrectCommandException {

        GameState gameState = new InCityState(game);
        gameState.doActions("F");

        assertEquals(game.getAfterFightState(), game.getState());
        assertFalse(game.isEndGame());

    }

    @Test
    public void doActionsFightHeroDies() throws IncorrectCommandException {

        Game game = new Game(new TestGameFactory(GameType.PulpFiction),0);
        GameState gameState = new InCityState(game);
        gameState.doActions("F");

        assertEquals(game.getGameLostState(), game.getState());
        assertFalse(game.isEndGame());

    }

    @Test
    public void travel() {
        GameState gameState = new InCityState(game);
        gameState.travel(null);

        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        assertFalse(game.isEndGame());
    }

    @Test
    public void fightLose() {
        Game game = new Game(new TestGameFactory(GameType.PulpFiction),0);
        GameState gameState = new InCityState(game);
        gameState.fight(game.getWorld().getHeroLocation().getInhabitants().stream().filter(p -> !p.isFriendly() && p.isAlive()).collect(Collectors.toList()));

        assertEquals(0, game.getHero().getExperience());
    }

    @Test
    public void fightWin() {
        Game game = new Game(new TestWinGameFactory(GameType.PulpFiction),0);
        GameState gameState = new InCityState(game);
        gameState.fight(game.getWorld().getHeroLocation().getInhabitants().stream().filter(p -> !p.isFriendly() && p.isAlive()).collect(Collectors.toList()));

        assertEquals(60, game.getHero().getExperience());
        assertEquals(game.getAfterFightState(), game.getState());
        assertFalse(game.isEndGame());
    }

    @Test
    public void endGame() {
        GameState gameState = new InCityState(game);
        gameState.endGame();

        assertEquals(game.getGameOverState(), game.getState());
        assertFalse(game.isEndGame());
    }
}