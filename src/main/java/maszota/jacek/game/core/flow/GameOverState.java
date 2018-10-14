package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.util.List;

public class GameOverState implements GameState {

    protected transient Game game;

    public GameOverState(Game game) {
        this.game = game;
    }

    @Override
    public void displayActions() {
        System.out.println(this.game.getLabel("confirm_quit"));
    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {
        if("y".equalsIgnoreCase(action)){
            endGame();
        }else{
            this.game.setState(this.game.getAfterFightState());
        }
    }

    @Override
    public void travel(Location location) {
        System.out.println(String.format("The game is over. You cannot travel to %s", location.getName()));
    }

    @Override
    public void fight(List<GameCharacter> opponents) {
        System.out.println("The game is over. You cannot fight anymore");
    }

    @Override
    public void endGame() {
        this.game.endGame();
        System.out.println("Good bye. See you soon");
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

}
