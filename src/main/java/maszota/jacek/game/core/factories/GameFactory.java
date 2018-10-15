package maszota.jacek.game.core.factories;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.World;

import java.util.HashMap;
import java.util.Map;

public abstract class GameFactory {


    protected Map<String, String> dictionary = getDictionary();

    private Map<String,String> getDictionary() {
        Map<String, String> dictionary = new HashMap<>(50);

        dictionary.put("welcome_to","Welcome to %s");
        dictionary.put("confirm_quit","Are you sure you want to quit? [y/n]");
        dictionary.put("restore","Restore saved game");
        dictionary.put("save_quite","Save and leave");
        dictionary.put("goto_main_menu","Go to main menu");
        dictionary.put("you_won","**** Congratulations, you won the game ****");

        return dictionary;
    }

    public abstract GameCharacter createHero();
    public abstract World createWorld();
    public abstract Map<String,String> createDictionary();
    public abstract GameType getGameType();

}
