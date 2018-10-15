package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

public interface GameState extends Serializable{

    public static final Predicate<GameCharacter> ALIVE_ENEMIES = p -> !p.isFriendly() && p.isAlive();
    public static final Predicate<GameCharacter> ALIVE_FRIENDS = p -> p.isFriendly() && p.isAlive();

    void displayActions();
    void doActions(String action) throws IncorrectCommandException;
    void travel(Location location);
    void fight(List<GameCharacter> opponents);
    void endGame();
    void setGame(Game game);

}
