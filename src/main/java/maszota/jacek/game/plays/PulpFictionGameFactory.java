package maszota.jacek.game.plays;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;
import maszota.jacek.game.core.model.World;
import maszota.jacek.game.plays.model.GameCharacterImpl;
import maszota.jacek.game.plays.model.CityImpl;
import maszota.jacek.game.plays.model.WorldImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PulpFictionGameFactory extends GameFactory {

    public final GameType gameType;

    public PulpFictionGameFactory(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public GameCharacter createHero() {
        return new GameCharacterImpl.CharacterBuilder("Vincent Vega").setExperience(50.0f).setFriendly(true).build();
    }

    public World createWorld() {

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

        GameCharacterImpl marcellus_wallace = new GameCharacterImpl.CharacterBuilder("Marcellus Wallace").setFriendly(false).setExperience(60.0f).build();
        GameCharacterImpl butch_coolidge = new GameCharacterImpl.CharacterBuilder("Butch Coolidge").setExperience(100.0f).setFriendly(false).build();
        GameCharacterImpl ringo = new GameCharacterImpl.CharacterBuilder("Ringo").setExperience(30.0f).setFriendly(false).build();
        GameCharacterImpl yolanda = new GameCharacterImpl.CharacterBuilder("Yolanda").setExperience(20.0f).setFriendly(false).build();
        GameCharacterImpl mia = new GameCharacterImpl.CharacterBuilder("Mia Wallace").setExperience(40.0f).setFriendly(false).build();
        GameCharacterImpl cop1 = new GameCharacterImpl.CharacterBuilder("Bad Cop#1").setExperience(50.0f).setFriendly(false).build();
        GameCharacterImpl cop2 = new GameCharacterImpl.CharacterBuilder("Bad Cop#1").setExperience(50.0f).setFriendly(false).build();
        GameCharacterImpl cop3 = new GameCharacterImpl.CharacterBuilder("Bad Cop#3").setExperience(60.0f).setFriendly(false).build();
        GameCharacterImpl jules_winnfield = new GameCharacterImpl.CharacterBuilder("Jules Winnfield").setExperience(50.0f).setFriendly(true).build();

        SF.getInhabitants().add(cop1);
        LA.getInhabitants().add(ringo);
        LA.getInhabitants().add(yolanda);
        HO.getInhabitants().add(mia);
        CH.getInhabitants().add(jules_winnfield);
        CH.getInhabitants().add(marcellus_wallace);
        MA.getInhabitants().add(cop3);
        NY.getInhabitants().add(cop2);
        NY.getInhabitants().add(butch_coolidge);


        Collection<Location> cities = Stream.of(SA, SF, LA, HO, CH, MA, NY).collect(Collectors.toSet());

        return new WorldImpl.WorldBuilder().addLocation(cities).setLocationOfHero(SA).setFinishLocation(NY).build();

    }

    @Override
    public Map<String, String> createDictionary() {

        dictionary.put("leave", "Drive away");
        dictionary.put("quit", "Leave Pulp Fiction");
        dictionary.put("fight", "Blow his head off");
        dictionary.put("still_left", "There are still %d enemies left");
        dictionary.put("welcome_to","==|| Welcome to %s ||==");
        dictionary.put("inhabitants","Citizens: bad guys - %s, friends - %d");
        dictionary.put("fight_began","The fight has began. Your experience is %.2f. You are fighting against %s with experience %.2f");
        dictionary.put("fight_won","It was tough fight, but you won! Congratulations. You have received new experience points. Now you have %.2f");
        dictionary.put("fight_lost","It was tough fight, and you lose :( You have lost some experience points. Now you have %.2f");
        dictionary.put("traveling_to","Driving to %s");
        dictionary.put("welcome_game","Pulp Fiction main menu. What job do you want to do?");
        dictionary.put("confirm_quit","Are you sure you want to leave? [y/n]");
        dictionary.put("start","Let's drive");

        return dictionary;
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }
}
