package maszota.jacek.game.fake;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.plays.model.GameCharacterImpl;

public class TestWinGameFactory extends TestGameFactory {

    public TestWinGameFactory(GameType gameType) {
        super(gameType);
    }

    @Override
    public GameCharacter createHero() {
        return new GameCharacterImpl.CharacterBuilder("Harry Potter").setExperience(50.0f).setFriendly(true).build();
    }
}

