package maszota.jacek.game.core.model;

import java.io.Serializable;

public abstract class GameCharacter implements Fighting, Serializable {


    public abstract String getName();
    public abstract float getExperience();
    public abstract Race getRace();
    public abstract boolean isAlive();
    public abstract boolean isFriendly();
    public abstract float incrementExperience();
    public abstract float decrementExperience();
    public abstract void setExperience(float experience);

    @Override
    public String toString() {
        return String.format("%s with experience %.2f", getName(), getExperience());
    }


}
