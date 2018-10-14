package maszota.jacek.game;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.flow.Game;
import maszota.jacek.game.core.flow.IncorrectCommandException;
import maszota.jacek.game.plays.PulpFictionGameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PulpFictionTestGame {


    Game game;
    GameFactory gameFactory = new PulpFictionGameFactory(GameType.PulpFiction);

    @BeforeEach
    public void setup() {
        game = new Game(gameFactory,0);
    }

    @Test
    public void winningGame() throws IncorrectCommandException {

        game.doActions("S");
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to SF and Fight
        game.doActions("SF");
        assertEquals("SF", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("F");
        assertEquals(Float.valueOf(60f), Float.valueOf(game.getHero().getExperience()));
        assertEquals(game.getAfterFightState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to LA and Fight
        game.doActions("LA");
        assertEquals("LA", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("F");
        assertEquals(Float.valueOf(80f), Float.valueOf(game.getHero().getExperience()));
        assertEquals(game.getAfterFightState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to CH and Fight
        game.doActions("CH");
        assertEquals("CH", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("F");
        assertEquals(Float.valueOf(90f), Float.valueOf(game.getHero().getExperience()));
        assertEquals(game.getAfterFightState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to NY and Fight
        game.doActions("NY");
        assertEquals("NY", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("F");
        assertEquals(Float.valueOf(110f), Float.valueOf(game.getHero().getExperience()));
        assertEquals(game.getAfterFightState(), game.getState());

        //fight till death of last opponent
        for (int i = 0; i < 19; i++) {
            game.doActions("F");
            System.out.println(i);
            if (i < 18) {
                assertEquals(game.getAfterFightState(), game.getState());
            }
        }
        assertEquals(game.getGameWonState(), game.getState());
    }

    @Test
    public void losingGame() throws IncorrectCommandException {
        game.doActions("S");
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to SF and Fight
        game.doActions("SF");
        assertEquals("SF", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to LA
        game.doActions("LA");
        assertEquals("LA", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());
        game.doActions("W");
        assertEquals(game.getSelectDestinationGameSate(), game.getState());
        //go to CH and Fight
        game.doActions("CH");
        assertEquals("CH", game.getHeroLocation().getShortName());
        assertEquals(game.getInCityState(), game.getState());

        //fight till death of last opponent
        for (int i = 0; i < 10; i++) {
            game.doActions("F");
            System.out.println(i);
            assertEquals(Float.valueOf(45f - (i * 5f)), Float.valueOf(this.game.getHero().getExperience()));
            if (i < 9) {
                assertEquals(game.getAfterFightState(), game.getState());
            }
        }
        assertEquals(Float.valueOf(0f), Float.valueOf(this.game.getHero().getExperience()));
        assertEquals(game.getGameLostState(), game.getState());
    }


}
