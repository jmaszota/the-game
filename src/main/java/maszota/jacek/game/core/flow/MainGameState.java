package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.util.List;

import static java.lang.Thread.sleep;
import static maszota.jacek.game.core.flow.Game.list_pointer;

public class MainGameState implements GameState {

    protected transient Game game;

    public MainGameState(Game game) {
        this.game = game;
    }

    @Override
    public void displayActions() {

        System.out.println(this.game.getLabel("welcome_game"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("start"), "s"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("restore"), "r"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("save_quite"), "wq"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("quit"), "q"));
    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {

        if ("s".equalsIgnoreCase(action)) {
            travel(this.game.getWorld().getHeroLocation());
        } else if ("q".equalsIgnoreCase(action)) {
            endGame();
        } else if ("r".equalsIgnoreCase(action)) {
            this.game.restore();
        } else if ("wq".equalsIgnoreCase(action)) {
            this.game.backup();
            endGame();
        } else {
            throw new IncorrectCommandException();
        }

    }

    @Override
    public void travel(Location location) {
        System.out.print(String.format(this.game.getLabel("traveling_to"), location.getName()));
        printTravelDots();
        this.game.getWorld().setHeroLocation(location);
        this.game.setState(game.getInCityState());
    }

    private void printTravelDots() {
        int i = 0;
        while (i < 5) {
            try {
                sleep(game.getRelocationTime());
                System.out.print(" *");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println();
    }

    @Override
    public void fight(List<GameCharacter> opponents) {
        System.out.println("No one to fight with yet");
    }

    @Override
    public void endGame() {
        this.game.setState(this.game.getGameOverState());
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
}
