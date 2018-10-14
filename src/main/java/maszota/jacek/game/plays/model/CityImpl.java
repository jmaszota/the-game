package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.util.*;

public class CityImpl extends Location {

    private String locationName;
    private String shortName;
    private Set<Location> linkedCities;
    private List<GameCharacter> inhabitants;


    public CityImpl(String locationName, String shortName, Set<Location> linkedCities, List<GameCharacter> inhabitants) {
        this.locationName = locationName;
        this.linkedCities = linkedCities;
        this.inhabitants = inhabitants;
        this.shortName = shortName;
    }

    @Override
    public String getName() {
        return locationName;
    }

    @Override
    public Set<Location> getLinkedLocations() {
        return linkedCities;
    }

    @Override
    public Collection<GameCharacter> getInhabitants() {
        return inhabitants;
    }

    @Override
    public String getShortName() {
        return this.shortName;
    }

    @Override
    public int hashCode() {
        return locationName==null ? 0 : locationName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Location && ((locationName==null && ((Location)obj).getName()==null) ||
                (locationName.equals(((Location)obj).getName())));
    }


}
