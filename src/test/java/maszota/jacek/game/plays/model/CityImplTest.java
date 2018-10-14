package maszota.jacek.game.plays.model;

import maszota.jacek.game.core.model.GameCharacter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CityImplTest {

    @Test
    public void getName() {
        CityImpl cityImpl = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());
        assertEquals("New York", cityImpl.getName());
    }

    @Test
    public void getLinkedLocations() {

        CityImpl SA = new CityImpl("Seattle", "SA", new HashSet<>(), new ArrayList<>());
        CityImpl SF = new CityImpl("San Francisco", "SF", new HashSet<>(), new ArrayList<>());
        CityImpl LA = new CityImpl("Los Angeles", "LA", new HashSet<>(), new ArrayList<>());
        CityImpl HO = new CityImpl("Houston", "HO", new HashSet<>(), new ArrayList<>());
        CityImpl CH = new CityImpl("Chicago", "CH", new HashSet<>(), new ArrayList<>());
        CityImpl MA = new CityImpl("Miami", "MA", new HashSet<>(), new ArrayList<>());
        CityImpl NY = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());

        SA.getLinkedLocations().add(SF);
        SF.getLinkedLocations().add(SA);
        SF.getLinkedLocations().add(LA);
        LA.getLinkedLocations().add(SF);
        LA.getLinkedLocations().add(HO);
        LA.getLinkedLocations().add(CH);
        HO.getLinkedLocations().add(CH);
        HO.getLinkedLocations().add(MA);
        HO.getLinkedLocations().add(LA);
        CH.getLinkedLocations().add(HO);
        CH.getLinkedLocations().add(LA);
        CH.getLinkedLocations().add(NY);
        MA.getLinkedLocations().add(HO);
        NY.getLinkedLocations().add(CH);


        assertEquals(1, SA.getLinkedLocations().size());
        assertEquals(2, SF.getLinkedLocations().size());
        assertEquals(3, LA.getLinkedLocations().size());
        assertEquals(3, HO.getLinkedLocations().size());
        assertEquals(3, CH.getLinkedLocations().size());
        assertEquals(1, MA.getLinkedLocations().size());
        assertEquals(1, NY.getLinkedLocations().size());
    }

    @Test
    public void getInhabitants() {
        GameCharacterImpl gameCharacterImpl = new GameCharacterImpl.CharacterBuilder("Adam Punker").build();
        GameCharacterImpl gameCharacterImpl2 = new GameCharacterImpl.CharacterBuilder("Adam Punker#2").build();
        GameCharacterImpl gameCharacterImpl3 = new GameCharacterImpl.CharacterBuilder("Adam Punker").build();

        ArrayList<GameCharacter> inhabitants = new ArrayList<>();

        inhabitants.add(gameCharacterImpl);
        inhabitants.add(gameCharacterImpl2);
        inhabitants.add(gameCharacterImpl3);

        CityImpl SA = new CityImpl("Seattle", "SA", new HashSet<>(), inhabitants);

        assertEquals(3, SA.getInhabitants().size());
    }

    @Test
    public void equals() {

        CityImpl cityImplA = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());
        CityImpl cityImplB = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());

        assertEquals(cityImplA, cityImplB);
        assertEquals(cityImplA.hashCode(), cityImplB.hashCode());
    }

    @Test
    public void equalsNulls() {

        CityImpl cityImplA = new CityImpl(null, null, null, null);
        CityImpl cityImplB = new CityImpl(null, null, null, null);

        assertEquals(cityImplA, cityImplB);
        assertEquals(cityImplA.hashCode(), cityImplB.hashCode());
    }

    @Test
    public void notEquals() {
        CityImpl cityImplA = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());
        CityImpl cityImplB = new CityImpl("Los angeles", "LA", new HashSet<>(), new ArrayList<>());

        assertNotEquals(cityImplA, cityImplB);
        assertNotEquals(cityImplA.hashCode(), cityImplB.hashCode());
    }

    @Test
    public void notEqualsDiffTypes() {
        CityImpl cityImplA = new CityImpl("New York", "NY", new HashSet<>(), new ArrayList<>());

        Object actual = new Object();

        assertNotEquals(cityImplA, actual);
        assertNotEquals(cityImplA.hashCode(), actual.hashCode());
    }
}