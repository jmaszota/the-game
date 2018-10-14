package maszota.jacek.game.core.flow;

public class GameWonState extends GameOverState {

    public GameWonState(Game game) {
        super(game);
    }

    @Override
    public void displayActions() {
        System.out.println(this.game.getLabel("you_won"));
    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {
        endGame();
    }
}
