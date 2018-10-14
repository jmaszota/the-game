package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.Location;
import maszota.jacek.game.core.model.World;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class WorldImpl extends World {

    private final Set<Location> locations;

    private WorldImpl(WorldBuilder builder) {
        this.locations = builder.locations;
        this.setFinishLocation(builder.finishLocation);
        if(builder.heroLocation!=null)
        {
            this.setHeroLocation(builder.heroLocation);
        }
    }

    public static class WorldBuilder {
        private Location heroLocation;
        private Location finishLocation;
        private final Set<Location> locations = new HashSet<>();

        public WorldBuilder() {
        }

        public WorldBuilder addLocation(Location location){
            locations.add(location);
            return this;
        }

        public WorldBuilder addLocation(Collection<Location> locations){
            this.locations.addAll(locations);
            return this;
        }

        public WorldBuilder setLocationOfHero(Location location){
            this.heroLocation = location;
            return this;
        }

        public WorldBuilder setFinishLocation(Location location){
            this.finishLocation = location;
            return this;
        }

        public WorldImpl build(){
            return new WorldImpl(this);
        }

    }

    @Override
    public Set<Location> getLocations() {
        return this.locations;
    }
}
