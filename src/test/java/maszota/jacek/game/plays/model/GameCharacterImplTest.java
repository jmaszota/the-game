package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.Race;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterImplTest {

    String johny_punker = "Johny Punker";

    @Test
    public void getNameDefault() {

        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        assertEquals(johny_punker, gameCharacterImpl.getName());
    }

    @Test
    public void getExperienceDefault() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        assertEquals(Float.valueOf(1.0f), Float.valueOf(gameCharacterImpl.getExperience()));
    }

    @Test
    public void getExperienceSetWithBuilder() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();
        assertEquals(Float.valueOf(10.0f), Float.valueOf(gameCharacterImpl.getExperience()));
    }

    @Test
    public void getRace() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        assertEquals(Race.Punk, gameCharacterImpl.getRace());
    }

    @Test
    public void isAlive() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        assertTrue(gameCharacterImpl.isAlive());
        gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(0.0f).build();
        assertFalse(gameCharacterImpl.isAlive());
        gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();
        assertTrue(gameCharacterImpl.isAlive());
    }

    @Test
    public void isNotAlive() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(-0.1f).build();
        assertFalse(gameCharacterImpl.isAlive());
    }

    @Test
    public void isFriendly() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        assertTrue(gameCharacterImpl.isFriendly());
    }

    @Test
    public void moveTo() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();

        //TODO

        //      punkHero.moveTo(world, nextLocation);
    }

    @Test
    public void incrementExperience() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        gameCharacterImpl.incrementExperience();
        assertEquals(Float.valueOf(11.0f), Float.valueOf(gameCharacterImpl.getExperience()));
    }

    @Test
    public void decrementExperience() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder(johny_punker).build();
        gameCharacterImpl.decrementExperience();
        assertEquals(Float.valueOf(0.0f), Float.valueOf(gameCharacterImpl.getExperience()));
    }

    @Test
    public void fightWithWeaker() {
        GameCharacterImpl gameCharacterImplStronger = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(20.0f).build();
        GameCharacterImpl gameCharacterImplWeaker = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();

        assertTrue(gameCharacterImplStronger.fightWith(gameCharacterImplWeaker));
        assertEquals(Float.valueOf(30.0f), Float.valueOf(gameCharacterImplStronger.getExperience()));
        assertEquals(Float.valueOf(5.0f), Float.valueOf(gameCharacterImplWeaker.getExperience()));

    }

    @Test
    public void fightWithStronger() {
        GameCharacterImpl gameCharacterImplStronger = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(20.0f).build();
        GameCharacterImpl gameCharacterImplWeaker = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();

        assertFalse(gameCharacterImplWeaker.fightWith(gameCharacterImplStronger));
        assertEquals(Float.valueOf(30.0f), Float.valueOf(gameCharacterImplStronger.getExperience()));
        assertEquals(Float.valueOf(5.0f), Float.valueOf(gameCharacterImplWeaker.getExperience()));

    }

    @Test
    public void fightWithEqual() {
        GameCharacterImpl gameCharacterImplA = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();
        GameCharacterImpl gameCharacterImplB = new GameCharacterImpl.CharacterBuilder(johny_punker).setExperience(10.0f).build();

        assertTrue(gameCharacterImplA.fightWith(gameCharacterImplB));
        assertEquals(Float.valueOf(20.0f), Float.valueOf(gameCharacterImplA.getExperience()));
        assertEquals(Float.valueOf(5.0f), Float.valueOf(gameCharacterImplB.getExperience()));

    }


}