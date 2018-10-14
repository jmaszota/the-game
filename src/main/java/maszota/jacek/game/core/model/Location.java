package maszota.jacek.game.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public abstract class Location implements Serializable{
    public abstract String getName();
    public abstract Set<Location> getLinkedLocations();
    public abstract Collection<GameCharacter> getInhabitants();

    @Override
    public String toString() {
        return getName();
    }

    public abstract String getShortName();
}
