package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Race;

public class GameCharacterImpl extends GameCharacter {

    private Race race;
    private float experience;
    private boolean isFriendly;
    private String name;

    private GameCharacterImpl(CharacterBuilder builder) {
        this.race = Race.Punk;
        this.experience = builder.experience;
        this.isFriendly = builder.isFriendly;
        this.name = builder.name;
    }

    public static class CharacterBuilder {
        private float experience = 1.0f;
        private boolean isFriendly = true;
        private final String name;

        public CharacterBuilder(String name) {
            this.name = name;
        }

        public CharacterBuilder setExperience(float experience) {
            this.experience = experience;
            return this;
        }

        public CharacterBuilder setFriendly(boolean friendly) {
            this.isFriendly = friendly;
            return this;
        }

        public GameCharacterImpl build() {
            return new GameCharacterImpl(this);
        }
    }

    public String getName() {
        return name;
    }

    public float getExperience() {
        return experience;
    }

    public Race getRace() {
        return race;
    }

    public boolean isAlive() {
        return this.experience>0;
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    @Override
    public float incrementExperience() {
        return this.experience+=10;
    }

    @Override
    public float decrementExperience() {
        if (this.experience >= 5) {
            this.experience -= 5;
        } else {
            this.experience = 0;
        }
        return this.experience;
    }

    @Override
    public void setExperience(float experience) {
        this.experience = experience;
    }

    @Override
    public Boolean fightWith(GameCharacter opponent) { //Use strategy

        if(!opponent.isAlive()) return true;

        if (opponent.getExperience() > this.experience) {
            opponent.incrementExperience();
            this.decrementExperience();
            return false;
        } else if (opponent.getExperience() == this.experience) {
            opponent.decrementExperience();
            this.incrementExperience();
            return true;
        }
        opponent.decrementExperience();
        this.incrementExperience();
        return true;
    }
}
