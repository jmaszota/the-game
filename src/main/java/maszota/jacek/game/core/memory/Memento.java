package maszota.jacek.game.core.memory;

import maszota.jacek.game.core.GameType;
import maszota.jacek.game.core.flow.GameState;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.World;

import java.io.Serializable;

public class Memento implements Serializable{

    private GameCharacter hero;
    private World world;
    private GameState gameState;
    private GameType gameType;

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameCharacter getHero() {
        return hero;
    }

    public void setHero(GameCharacter hero) {
        this.hero = hero;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
