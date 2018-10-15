package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;

import java.util.List;
import java.util.stream.Collectors;

import static maszota.jacek.game.core.flow.Game.list_pointer;

public class InCityState implements GameState {

    protected transient Game game;

    public InCityState(Game game) {
        this.game = game;
    }

    @Override
    public void displayActions() {

        Location heroLocation = this.game.getWorld().getHeroLocation();
        System.out.println(String.format(this.game.getLabel("welcome_to"), heroLocation.getName()));

        long enemiesCount = heroLocation.getInhabitants().stream().filter(ALIVE_ENEMIES).count();
        long friendsCount = heroLocation.getInhabitants().stream().filter(ALIVE_FRIENDS).count();

        System.out.println(String.format(this.game.getLabel("inhabitants"), enemiesCount, friendsCount));
        System.out.println("Possible moves:");
        if (enemiesCount > 0) {
            System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("fight"), "f"));
        }
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("leave"), "w"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("goto_main_menu"), "q"));

    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {

        if ("r".equalsIgnoreCase(action)) {
            this.game.restore();
            return;
        } else if ("q".equalsIgnoreCase(action)) {
            this.game.setState(this.game.getMainGameState());
        } else if ("w".equalsIgnoreCase(action)) {
            travel(null);
        } else if ("f".equalsIgnoreCase(action)) {
            fight(this.game.getWorld().getHeroLocation().getInhabitants().stream().filter(ALIVE_ENEMIES).collect(Collectors.toList()));
        } else {
            throw new IncorrectCommandException();
        }
    }

    @Override
    public void travel(Location location) {
        this.game.setState(this.game.getSelectDestinationGameSate());
    }

    @Override
    public void fight(List<GameCharacter> opponents) {
        for (GameCharacter opponent : opponents) {
            System.out.println(String.format(this.game.getLabel("fight_began"), this.game.getHero().getExperience(), opponent.getName(), opponent.getExperience()));
            Boolean heroWins = this.game.getHero().fightWith(opponent);
            if (heroWins) {
                System.out.println(String.format(this.game.getLabel("fight_won"), this.game.getHero().getExperience()));
                //check if anyone is alive
                if (isWinningFight()) {
                    this.game.setState(this.game.getGameWonState());
                    return;
                }
            } else {
                System.out.println(String.format(this.game.getLabel("fight_lost"), this.game.getHero().getExperience()));
                if (!this.game.getHero().isAlive()) {
                    this.game.setState(this.game.getGameLostState());
                    return;
                }
            }
        }
        this.game.setState(this.game.getAfterFightState());
    }

    @Override
    public void endGame() {
        this.game.setState(this.game.getGameOverState());
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    private boolean isWinningFight() {
        return this.game.getWorld().getHeroLocation().getInhabitants().stream().noneMatch(ALIVE_ENEMIES) && this.game.getHeroLocation().equals(this.game.getWorld().getFinishLocation());
    }
}
