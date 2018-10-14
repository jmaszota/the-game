package maszota.jacek.game.core.flow;

public class GameLostState extends GameOverState {

    public GameLostState(Game game) {
        super(game);
    }

    @Override
    public void displayActions() {
        System.out.println(this.game.getLabel("you_are_dead"));
    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {
        endGame();
    }
}
