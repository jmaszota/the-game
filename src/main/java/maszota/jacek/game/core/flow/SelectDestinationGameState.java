package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.Location;

import java.util.Optional;

import static maszota.jacek.game.core.flow.Game.list_pointer;

public class SelectDestinationGameState extends MainGameState {

    public SelectDestinationGameState(Game game) {
        super(game);
    }

    @Override
    public void displayActions() {

        System.out.println("Where do you want to go?");
        for (Location location : this.game.getWorld().getPossibleMoveToLocations()) {
            System.out.println(String.format("%5s%-20s[%s]", list_pointer, location.getName(), location.getShortName()));
        }
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("goto_main_menu"), "q"));
    }

    @Override
    public void doActions(String action) throws IncorrectCommandException {

        if ("q".equalsIgnoreCase(action)) {
            this.game.setState(this.game.getMainGameState());
        } else {

            Optional<Location> optionalLocation = this.game.getWorld().getPossibleMoveToLocations().stream().filter(p -> p.getShortName().toLowerCase().equals(action.toLowerCase())).findFirst();
            if (!optionalLocation.isPresent()) {
                throw new IncorrectCommandException();
            } else {
                travel(optionalLocation.get());
            }
        }
    }
}
