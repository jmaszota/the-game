package maszota.jacek.game.plays;

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

public class HarryPotterGameFactory extends GameFactory {

    public final GameType gameType;

    public HarryPotterGameFactory(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public GameCharacter createHero() {
        return new GameCharacterImpl.CharacterBuilder("Harry Potter").setExperience(50.0f).setFriendly(true).build();
    }

    public World createWorld() {

        CityImpl L = new CityImpl("London", "L", new HashSet<>(), new ArrayList<>());
        CityImpl H = new CityImpl("Hogwarts", "H", new HashSet<>(), new ArrayList<>());
        CityImpl M = new CityImpl("Malfoy Manor", "M", new HashSet<>(), new ArrayList<>());

        L.getLinkedLocations().add(H);
        M.getLinkedLocations().add(H);
        M.getLinkedLocations().add(L);
        H.getLinkedLocations().add(L);
        H.getLinkedLocations().add(M);

        GameCharacterImpl draco = new GameCharacterImpl.CharacterBuilder("Draco Malfoy").setFriendly(false).setExperience(60.0f).build();
        GameCharacterImpl lord = new GameCharacterImpl.CharacterBuilder("Lord Voldemort").setExperience(100.0f).setFriendly(false).build();
        GameCharacterImpl crabbe = new GameCharacterImpl.CharacterBuilder("Vincent Crabbe").setExperience(30.0f).setFriendly(false).build();
        GameCharacterImpl ron = new GameCharacterImpl.CharacterBuilder("Ron Weasley").setExperience(50.0f).setFriendly(true).build();
        GameCharacterImpl hermione_granger = new GameCharacterImpl.CharacterBuilder("Hermione Granger").setExperience(50.0f).setFriendly(true).build();

        L.getInhabitants().add(hermione_granger);
        H.getInhabitants().add(crabbe);
        H.getInhabitants().add(ron);
        M.getInhabitants().add(draco);
        M.getInhabitants().add(lord);


        Collection<Location> cities = Stream.of(L, M, H).collect(Collectors.toSet());

        return new WorldImpl.WorldBuilder().addLocation(cities).setLocationOfHero(L).setFinishLocation(M).build();

    }

    @Override
    public Map<String, String> createDictionary() {

        dictionary.put("leave", "Fly away");
        dictionary.put("quit", "Leave Magic World");
        dictionary.put("fight", "Fight with your wand");
        dictionary.put("still_left", "There are still %d enemies left");
        dictionary.put("inhabitants","Wizards & witches: bad guys - %s, friends - %d");
        dictionary.put("fight_began","The fight has began. Your experience is %.2f. You are fighting against %s with %.2f magic points");
        dictionary.put("fight_won","It was tough fight, but you won! Congratulations. You have received new magic points. Now you have %.2f");
        dictionary.put("fight_lost","It was tough fight, and you lose :( You have lost some magic points. Now you have %.2f");
        dictionary.put("traveling_to","Flying on a broomstick to %s...");
        dictionary.put("welcome_game","Harry Potter main menu. What magic do you want to do?");
        dictionary.put("confirm_quit","Are you sure you want to leave magic and return to muggles? [y/n]");
        dictionary.put("you_are_dead","He put Avada Kedavra spell on you. You are dead :(");
        dictionary.put("start","Let's do some magic");

        return dictionary;
    }

    @Override
    public GameType getGameType() {
        return this.gameType;
    }
}
