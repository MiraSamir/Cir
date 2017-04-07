package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import logs.Log4j;
import model.IPlayer;
import model.ISavingObservable;
import model.ISavingObserver;
import model.SavingMemento;
import model.entities.Projectile;
import model.levels.Level;

public class SavingOriginator implements ISavingObservable {
    
    private static Logger logger;
    
    
    private static SavingOriginator instance;
    
    public static SavingOriginator getInstance(MainController mainController) {
        if (instance == null)
            instance = new SavingOriginator(mainController);
        return instance;
    }
    
    private List<String> selectedProjectiles;
    
    private MainController mainController;
    
    private List<ISavingObserver> observers;
    
    private IFileHandler fileHandler;
    private List<IPlayer> playerList;
    private Level level;
    private int gameDuration;
    private List<Projectile> projectileList;
    
    private SavingOriginator(MainController mainController) {
      
        this.mainController = mainController;
        this.observers = new ArrayList<ISavingObserver>();
        this.addObserver(this.mainController.getGameController());
        this.fileHandler = new FileHandling();
    }
    
    @Override
    public void addObserver(ISavingObserver o) {
        this.observers.add(o);
    }
    
    public int getGameDuration() {
        return gameDuration;
    }
    
    public Level getLevel() {
        return level;
    }
    
    public List<IPlayer> getPlayerList() {
        return playerList;
    }
    
    public List<Projectile> getProjectileList() {
        return projectileList;
    }
    
    public List<String> getSelectedProjectiles() {
        return selectedProjectiles;
    }
    
    public void Load() {
      
        SavingMemento memento = this.fileHandler.load();
        if (memento != null) {
            this.setPlayerList(memento.getPlayerList());
            this.setProjectileList(memento.getProjectileList());
            this.gameDuration = memento.getGameDuration();
            this.level = memento.getLevel();
            this.selectedProjectiles = memento.getSelectedProjectiles();
            this.notifyObservers(false);
        }
    }
    
    private void notifyObservers(boolean save) {
        for (ISavingObserver obs : this.observers) {
            obs.updateSaving(save);
        }
    }
    
    @Override
    public void removeObserver(ISavingObserver o) {
        this.observers.remove(o);
    }
    
    public void Save() {
     
        this.notifyObservers(true);
        SavingMemento memento = new SavingMemento(this.getPlayerList(), this.getProjectileList(), this.getLevel(),
                this.getGameDuration(), this.getSelectedProjectiles());
        this.fileHandler.save(memento);
    }
    
    public void setGameDuration(int gameDuration) {
        this.gameDuration = gameDuration;
    }
    
    public void setLevel(Level level) {
        this.level = level;
    }
    
    public void setPlayerList(List<IPlayer> playerList) {
        this.playerList = playerList;
    }
    
    public void setProjectileList(List<Projectile> projectileList) {
        this.projectileList = projectileList;
    }
    
    public void setSelectedProjectiles(List<String> selectedProjectiles) {
        this.selectedProjectiles = selectedProjectiles;
    }
}