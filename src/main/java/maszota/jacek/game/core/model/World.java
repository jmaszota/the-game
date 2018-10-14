package maszota.jacek.game.core.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class World implements Serializable {

    private Location heroLocation;
    private Location finishLocation;

    public abstract Set<Location> getLocations();

    public Location getHeroLocation(){
        return this.heroLocation;
    }

    public Set<Location> getPossibleMoveToLocations(){
        if(this.heroLocation==null) return new HashSet<>();
        return this.heroLocation.getLinkedLocations();
    }

    public Location getFinishLocation() {
        return finishLocation;
    }

    public void setFinishLocation(Location finishLocation) {
        this.finishLocation = finishLocation;
    }

    public void setHeroLocation(final Location newHeroLocation){
        if(!getLocations().stream().anyMatch(p->p.equals(newHeroLocation))) {
            throw new IllegalArgumentException("Unknown heroLocation");
        }
        this.heroLocation=newHeroLocation;
    }

}
