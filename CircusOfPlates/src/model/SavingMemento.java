package model;

import java.util.List;

import model.entities.Projectile;
import model.levels.Level;

public class SavingMemento implements java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 9L;
    private List<IPlayer> playerList;
    private List<Projectile> projectileList;
    private Level level;
    private List<String> selectedProjectiles;
    
    private int gameDuration;
    
    public SavingMemento(List<IPlayer> playerList, List<Projectile> projectileList, Level level, int gameDuration,
            List<String> selectedProjectiles) {
        this.playerList = playerList;
        this.projectileList = projectileList;
        this.level = level;
        this.gameDuration = gameDuration;
        this.selectedProjectiles = selectedProjectiles;
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
    
    public void setPlayerList(List<IPlayer> playerList) {
        this.playerList = playerList;
    }
    
    public void setProjectileList(List<Projectile> projectileList) {
        this.projectileList = projectileList;
    }
    
}