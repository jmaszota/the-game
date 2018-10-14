package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.io.Serializable;
import java.util.List;

public interface GameState extends Serializable{
    void displayActions();
    void doActions(String action) throws IncorrectCommandException;
    void travel(Location location);
    void fight(List<GameCharacter> opponents);
    void endGame();
    void setGame(Game game);

}
