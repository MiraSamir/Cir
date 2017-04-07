package model.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import controlEventHandlers.KeyboardHandler;
import controller.MainController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import logs.Log4j;
import model.IPlayer;
import model.entities.Character;
import model.levels.Level;
import model.levels.MediumLevel;
import model.states.FlyingState;

public class GameControllerHelper {
    
    private static Logger logger;
    static {
        logger = Log4j.getInstance();
    }
    private MainController mainController;
    private List<IPlayer> players;
    private Level level;
    private int gameDurationInSeconds;
    private IntegerProperty timer;
    
    private boolean ascending;
    
    private int noOfPlayers;
    
    private KeyboardHandler handler;
    
    public GameControllerHelper(MainController mainController) {
        logger.info("Initializing Game Controller Hepler...");
        this.players = new ArrayList<IPlayer>();
        this.mainController = mainController;
        this.level = new MediumLevel(); // change Difficulty.
        this.ascending = true;
        this.handler = new KeyboardHandler(mainController);
        this.gameDurationInSeconds = 120;
        this.timer = new SimpleIntegerProperty(120);
        this.timer.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gameDurationInSeconds = newValue.intValue();
            }
        });
    }
    
    public void addPlayers(IPlayer player) {
        logger.info("Adding Players to the Game...");
        this.players.add(player);
    }
    
    public void clearControlCharacters() {
        this.handler.clearCharacters();
    }
    
    public void clearPlayers() {
        this.players.clear();
    }
    
    private void fillMemento() {
        this.mainController.getGameOriginator().setPlayerList(this.getPlayers());
        this.mainController.getGameOriginator()
                .setProjectileList(this.mainController.getGameController().getProjectilesPool().getInUse());
        this.mainController.getGameOriginator().setGameDuration(this.gameDurationInSeconds);
        this.mainController.getGameOriginator().setLevel(this.level);
    }
    
    private void getFromMemento() {
        this.setPlayers(this.mainController.getGameOriginator().getPlayerList());
        this.mainController.getGameController().getProjectilesPool()
                .setInUse(this.mainController.getGameOriginator().getProjectileList());
        this.gameDurationInSeconds = this.mainController.getGameOriginator().getGameDuration();
        this.level = this.mainController.getGameOriginator().getLevel();
    }
    
    public int getGameDurationInSeconds() {
        return gameDurationInSeconds;
    }
    
    public KeyboardHandler getHandler() {
        return handler;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public int getNoOfPlayers() {
        return noOfPlayers;
    }
    
    public List<IPlayer> getPlayers() {
        return players;
    }
    
    public IntegerProperty getTimer() {
        return timer;
    }
    
    public void handleSavingRequests(boolean save) {
        if (save) {
            this.fillMemento();
        } else {
            this.getFromMemento();
        }
    }
    
    public void makeItEvenHarder() {
        FlyingState
                .setProjectileSpeed(this.ascending ? FlyingState.projectileSpeed + 1 : FlyingState.projectileSpeed - 1);
        this.ascending = this.ascending && FlyingState.projectileSpeed == this.level.getUpperBoundSpeed() ? false
                : !this.ascending && FlyingState.projectileSpeed == this.level.getLowerBoundSpeed() ? true
                        : this.ascending;
    }
    
    public void register1Player() {
        logger.info("Registering One Player to The Game.");
        this.noOfPlayers = 1;
    }
    
    public void register2Players() {
        logger.info("Registering Two Players to The Game.");
        this.noOfPlayers = 2;
    }
    
    public void registerControlCharacter(Character character) {
        this.handler.registerCharacter(character);
    }
    
    public void resetGameDuration() {
        this.gameDurationInSeconds = 120;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    
    public void setPlayers(List<IPlayer> players) {
        this.players = players;
    }
}
