package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.Location;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorldImplTest {

    private Location NY = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());

    private Location LA = new CityImpl("Los Angles", "LA", new HashSet<>(), new ArrayList<>());

    @Test
    void getLocations() {
        WorldImpl worldImpl = new WorldImpl.WorldBuilder().addLocation(NY).build();

        assertEquals(1, worldImpl.getLocations().size());
        assertEquals(NY.getName(), worldImpl.getLocations().iterator().next().getName());

    }

    @Test
    void defaultHeroLocation() {
        WorldImpl worldImpl = new WorldImpl.WorldBuilder().addLocation(NY).setLocationOfHero(NY).build();
        assertEquals(NY.getName(), worldImpl.getHeroLocation().getName());
    }

    @Test
    void defaultHeroLocationWhichNotExist() {
        IllegalArgumentException invalidArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new WorldImpl.WorldBuilder().addLocation(NY).setLocationOfHero(LA).build()
        );
        assertEquals("Unknown heroLocation", invalidArgumentException.getMessage());
    }

    @Test
    void changeHeroLocation() {
        WorldImpl worldImpl = new WorldImpl.WorldBuilder().addLocation(NY).addLocation(LA).setLocationOfHero(NY).build();
        assertEquals(NY.getName(), worldImpl.getHeroLocation().getName());
        worldImpl.setHeroLocation(LA);
        assertEquals(LA.getName(), worldImpl.getHeroLocation().getName());
    }
}