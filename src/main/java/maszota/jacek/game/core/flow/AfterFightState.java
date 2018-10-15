package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.model.Location;

import static maszota.jacek.game.core.flow.Game.list_pointer;

public class AfterFightState extends InCityState {

    public AfterFightState(Game game) {
        super(game);
    }

    @Override
    public void displayActions() {

        Location heroLocation = this.game.getWorld().getHeroLocation();

        long enemiesCount = heroLocation.getInhabitants().stream().filter(ALIVE_ENEMIES).count();

        if (enemiesCount > 0) {
            System.out.println(String.format(this.game.getLabel("still_left"), enemiesCount
            ));
        }

        System.out.println("Possible moves:");
        if (enemiesCount > 0) System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("fight"), "f"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("leave"), "w"));
        System.out.println(String.format("%5s%-20s[%s]", list_pointer, this.game.getLabel("goto_main_menu"), "q"));


    }

}
