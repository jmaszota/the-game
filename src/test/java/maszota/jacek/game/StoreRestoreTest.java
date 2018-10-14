package maszota.jacek.game;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.flow.Game;
import maszota.jacek.game.core.flow.GameState;
import maszota.jacek.game.core.flow.IncorrectCommandException;
import maszota.jacek.game.core.model.Location;
import maszota.jacek.game.fake.TestGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StoreRestoreTest {
    Game game;
    GameFactory gameFactory = new TestGameFactory(GameType.PulpFiction);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory, 0);
    }

    @Test
    public void restoreGameTest() throws IncorrectCommandException {


        Location saveHeroLocation = game.getWorld().getLocations().stream().filter(p -> p.getShortName().equalsIgnoreCase("E")).findFirst().get();
        GameState savedState = game.getAfterFightState();
        Float savedExperience = 890f;

        game.getWorld().setHeroLocation(saveHeroLocation);
        game.setState(savedState);
        game.getHero().setExperience(890f);

        game.backup();

        game = new Game(gameFactory, 0);

        game.restore();

        assertEquals(Float.valueOf(890f), Float.valueOf(game.getHero().getExperience()));
        assertEquals(savedState.getClass().getName(), game.getState().getClass().getName());
        assertEquals(saveHeroLocation.getName(), game.getHeroLocation().getName());
    }

    @Test
    public void restoreGameTest_whenGameTypeChanged() throws IncorrectCommandException {


        Location saveHeroLocation = game.getWorld().getLocations().stream().filter(p -> p.getShortName().equalsIgnoreCase("E")).findFirst().get();
        GameState savedState = game.getAfterFightState();

        game.getWorld().setHeroLocation(saveHeroLocation);
        game.setState(savedState);
        game.getHero().setExperience(890f);

        game.backup();

        GameFactory gameFactory = new TestGameFactory(GameType.HarryPotter);
        game = new Game(gameFactory, 0);

        game.restore();

        assertNotEquals(Float.valueOf(890f), Float.valueOf(game.getHero().getExperience()));
        assertNotEquals(savedState.getClass().getName(), game.getState().getClass().getName());
        assertNotEquals(saveHeroLocation.getName(), game.getHeroLocation().getName());
    }
}
