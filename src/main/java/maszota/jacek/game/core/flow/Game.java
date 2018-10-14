package maszota.jacek.game.core.flow;

import maszota.jacek.game.core.factories.GameFactory;
import maszota.jacek.game.core.memory.Memento;
import maszota.jacek.game.core.model.GameCharacter;
import maszota.jacek.game.core.model.Location;
import maszota.jacek.game.core.model.World;

import java.io.*;
import java.util.Map;

public class Game {

    public static final String list_pointer = "=> ";
    private final long relocationTime;

    private GameCharacter hero;
    private World world;
    private Map<String,String> dictionary;
    private boolean isEndGame = false;
    private GameState mainGameState;
    private GameState inCityState;
    private GameState afterFightState;
    private GameState selectDestinationGameSate;
    private GameState gameOverState;
    private GameState gameLostState;
    private GameState gameWonState;
    private GameState state;
    private GameFactory gameFactory;

    public Game(GameFactory gameFactory, long relocationTime) {

        this.mainGameState = new MainGameState(this);
        this.inCityState = new InCityState(this);
        this.selectDestinationGameSate = new SelectDestinationGameState(this);
        this.afterFightState = new AfterFightState(this);
        this.gameOverState = new GameOverState(this);
        this.gameLostState = new GameLostState(this);
        this.gameWonState = new GameWonState(this);

        this.gameFactory = gameFactory;
        this.hero = gameFactory.createHero();
        this.world = gameFactory.createWorld();
        this.dictionary = gameFactory.createDictionary();
        this.setState(mainGameState);
        this.relocationTime = relocationTime;
    }

    public void backup(){

        Memento memento = new Memento();
        memento.setHero(this.getHero());
        memento.setWorld(this.getWorld());
        memento.setGameState(this.getState());
        memento.setGameType(this.getGameFactory().getGameType());
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("memento.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(memento);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restore() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("memento.ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(fis)) {
            Memento result = (Memento) ois.readObject();

            if(!getGameFactory().getGameType().equals(result.getGameType())){
                System.out.println(String.format("Saved game is of different type (%s), will not be restored", result.getGameType()));
                return;
            }

            this.setHero(result.getHero());
            this.setWorld(result.getWorld());
            GameState gameState = result.getGameState();
            gameState.setGame(this);
            this.setState(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void displayActions() {
        this.state.displayActions();
    }

    public void doActions(String actionKey) throws IncorrectCommandException {
        this.state.doActions(actionKey);
    }

    public GameCharacter getHero() {
        return hero;
    }

    public World getWorld() {
        return world;
    }

    public GameState getInCityState() {
        return inCityState;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getAfterFightState() {
        return afterFightState;
    }

    public GameState getState() {
        return state;
    }

    public GameState getSelectDestinationGameSate() {
        return selectDestinationGameSate;
    }

    public boolean isEndGame() {
        return isEndGame;
    }

    public void endGame() {
        isEndGame = true;
    }

    public GameState getGameOverState() {
        return gameOverState;
    }

    public GameState getGameLostState() {
        return gameLostState;
    }

    public GameState getMainGameState() {
        return mainGameState;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public String getLabel(String key) {
        String label = getDictionary().get(key);
        return label==null ? key : label;
    }

    public GameState getGameWonState() {
        return gameWonState;
    }

    public Location getHeroLocation() {
        return getWorld().getHeroLocation();
    }

    public void setHero(GameCharacter hero) {
        this.hero = hero;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public long getRelocationTime() {
        return relocationTime;
    }
}
