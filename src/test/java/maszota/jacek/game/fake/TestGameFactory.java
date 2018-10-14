package maszota.jacek.game.fake;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;
import maszota.jacek.game.core.model.World;
import maszota.jacek.game.plays.model.CityImpl;
import maszota.jacek.game.plays.model.GameCharacterImpl;
import maszota.jacek.game.plays.model.WorldImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestGameFactory extends GameFactory {

    public final GameType gameType;

    public TestGameFactory(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public GameCharacter createHero() {
        return new GameCharacterImpl.CharacterBuilder("Harry Potter").setExperience(5.0f).setFriendly(true).build();
    }

    @Override
    public World createWorld() {

        CityImpl L = new CityImpl("London", "L", new HashSet<>(), new ArrayList<>());
        CityImpl E = new CityImpl("Edinburgh", "E", new HashSet<>(), new ArrayList<>());

        GameCharacterImpl crabbe = new GameCharacterImpl.CharacterBuilder("Vincent Crabbe").setExperience(30.0f).setFriendly(false).build();

        L.getLinkedLocations().add(E);
        L.getInhabitants().add(crabbe);

        Collection<Location> cities = Stream.of(L, E).collect(Collectors.toSet());

        return new WorldImpl.WorldBuilder().addLocation(cities).setLocationOfHero(L).build();

    }

    @Override
    public Map<String, String> createDictionary() {

        Map<String, String> dictionary = new HashMap<>(50);

        dictionary.put("leave", "Fly away");
        dictionary.put("quit", "Leave Magic World");
        dictionary.put("fight", "Fight with your wand");
        dictionary.put("still_left", "There are still %d enemies left");
        dictionary.put("welcome_to", "Welcome to %s");
        dictionary.put("inhabitants", "Wizards & witches: bad guys - %s, friends - %d");
        dictionary.put("fight_began", "The fight has began. Your experience is %.2f. You are fighting against %s with %.2f magic points");
        dictionary.put("fight_won", "It was tough fight, but you won! Congratulations. You have received new magic points. Now you have %.2f");
        dictionary.put("fight_lost", "It was tough fight, and you lose :( You have lost some magic points. Now you have %.2f");
        dictionary.put("traveling_to", "Flying on a broomstick to %s...");
        dictionary.put("welcome_game", "Welcome to the magic world of Harry Potter. Where do you want to fly?");
        dictionary.put("confirm_quit", "Are you sure you want to leave magic and return to muggles? [y/n]");
        dictionary.put("you_are_dead", "He put Avada Kedavra spell on you. You are dead :(");


        return dictionary;
    }

    @Override
    public GameType getGameType() {
        return gameType;
    }
}
