package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import controlEventHandlers.MouseHandler;
import controlEventHandlers.MultiplePressedKeysEventHandler;
import javafx.geometry.Point2D;
import logs.Log4j;
import model.Clown;
import model.HandIterator;
import model.IPlayer;
import model.ISavingObserver;
import model.Iterator;
import model.Player;
import model.ProjectilesPool;
import model.Shelf;
import model.entities.EntityGeneratorVisitor;
import model.entities.Projectile;
import model.helpers.GameControllerHelper;
import model.states.CaughtState;
import model.states.FlyingState;
import model.states.OnShelfState;
import model.states.UnusedState;
import view.Util;

public class GameController implements ISavingObserver {
    
 
    private static GameController instance;
    
    public static GameController getInstance(MainController mainController) {
        if (instance == null)
            instance = new GameController(mainController);
        return instance;
    }
    
    private MainController mainController;
    
    private ProjectilesPool projectilesPool;
    
    private Iterator<Projectile> iterator;
    
    private GameControllerHelper gameControllerHelper;
    
    private AnimationHandler animationTimer;
    
    private GameController(MainController mainController) {
      
        this.mainController = mainController;
        this.gameControllerHelper = new GameControllerHelper(mainController);
        this.projectilesPool = ProjectilesPool.getInstance(mainController);
        this.animationTimer = new AnimationHandler(mainController);
    }
    
    public boolean canMoveToTheLeft(model.entities.Character character) {
        return character.getCurrentPosition().getX() >= 0;
    }
    
    public boolean canMoveToTheRight(model.entities.Character character) {
        return character.getCurrentPosition().getX() + character.getDimension().getWidth() < Util.SCREEN_WIDTH;
    }
    
    private void checkGains(IPlayer player) {
        
        HandIterator iterator = player.getPlayerCharacter().getLastUpdatedHand().getHandIterator();
        if (isMatchable(iterator)) {
            removeLastThree(player);
            incrementPlayerScore(player);
        }
    }
    
    private void clearAll() {
        this.projectilesPool.releaseAll();
        this.gameControllerHelper.clearPlayers();
        this.gameControllerHelper.clearControlCharacters();
        this.gameControllerHelper.resetGameDuration();
    }
    
    private void deleteProjectile(Projectile projectile) {
        projectile.setProjectileState(new UnusedState());
        projectile.remove(this.mainController.getViewController().getMainMenu().getGameScene().getRootNode());
        this.projectilesPool.releaseObject(projectile);
    }
    
    private void drawOldProjectiles() {
        this.iterator = this.projectilesPool.getIterator();
        for (; iterator.hasNext();) {
            Projectile projectile = (Projectile) iterator.next();
            projectile.draw(this.mainController.getViewController().getMainMenu().getGameScene().getRootNode());
        }
    }
    
    public void dropNewProjectiles() {
       
        for (int i = 0; i < Shelf.values().length; i++) {
            Projectile projectile;
            try {
                projectile = projectilesPool.getObject();
                projectile.setProjectileState(new OnShelfState());
                projectile.setPositions(Shelf.values()[i]);
                projectile.draw(this.mainController.getViewController().getMainMenu().getGameScene().getRootNode());
            } catch (FileNotFoundException e) {
            }
        }
        this.gameControllerHelper.makeItEvenHarder();
    }
    
    public GameControllerHelper getControllerHelper() {
        return gameControllerHelper;
    }
    
    public ProjectilesPool getProjectilesPool() {
        return projectilesPool;
    }
    
    public void handlePlates() {
        this.iterator = this.projectilesPool.getIterator();
        for (; iterator.hasNext();) {
            Projectile projectile = (Projectile) iterator.next();
            if (isEndOfTheWorld(projectile)) {
                this.deleteProjectile(projectile);
            } else if (projectile.getProjectileState().getClass() != CaughtState.class) {
                for (int i = 0; i < this.gameControllerHelper.getPlayers().size(); i++) {
                    if (this.offerProjectileToPlayer(projectile, this.gameControllerHelper.getPlayers().get(i))) {
                        this.checkGains(this.gameControllerHelper.getPlayers().get(i));
                        continue;
                    }
                }
            }
            projectile.ejaculateProjectile();
        }
    }
    
    private void incrementPlayerScore(IPlayer player) {
        
        player.incrementScore();
    }
    
    private boolean isEndOfTheWorld(Projectile projectile) {
        if (projectile.getCurrentPosition().getY() >= Util.SCREEN_HEIGHT)
            return true;
        return false;
    }
    
    private boolean isMatchable(HandIterator iterator) {
        
        if (iterator.size() >= 3) {
            List<Projectile> projectiles = new ArrayList<Projectile>();
            for (int i = 0; i < 3 && iterator.hasNext(); i++) {
                projectiles.add((Projectile) iterator.next());
            }
            if ((projectiles.get(0).getColor().equals(projectiles.get(1).getColor()))
                    && (projectiles.get(1).getColor().equals(projectiles.get(2).getColor()))) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    public void loadGame() {
        this.gameControllerHelper.clearControlCharacters();
        for (int i = 0; i < this.gameControllerHelper.getPlayers().size(); i++) {
            this.gameControllerHelper
                    .registerControlCharacter(this.gameControllerHelper.getPlayers().get(i).getPlayerCharacter());
            this.gameControllerHelper.getPlayers().get(i).getPlayerCharacter()
                    .draw(this.mainController.getViewController().getMainMenu().getGameScene().getRootNode());
        }
        this.animationTimer.setProjectileTime(this.gameControllerHelper.getLevel().getNewProjectileTime());
        FlyingState.setProjectileSpeed(this.gameControllerHelper.getLevel().getLowerBoundSpeed());
        this.drawOldProjectiles();
    }
    
    public void newGame() {
        this.clearAll();
   
        this.gameControllerHelper.clearControlCharacters();
        this.registerControlEventHandlers();
        int numberOfPlayers = this.gameControllerHelper.getNoOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player(new EntityGeneratorVisitor());
            this.gameControllerHelper.registerControlCharacter(player.getPlayerCharacter());
            player.getPlayerCharacter().setIntialPosition(new Point2D(
                    (Util.SCREEN_WIDTH) * (i + 1) / (numberOfPlayers + 1), Util.SCREEN_HEIGHT - Clown.CLOWNHEIGHT / 2));
            this.mainController.getGameController().getControllerHelper().addPlayers(player);
            player.getPlayerCharacter()
                    .draw(this.mainController.getViewController().getMainMenu().getGameScene().getRootNode());
        }
        this.animationTimer.setProjectileTime(this.gameControllerHelper.getLevel().getNewProjectileTime());
        FlyingState.setProjectileSpeed(this.gameControllerHelper.getLevel().getLowerBoundSpeed());
        this.dropNewProjectiles();
    }
    
    private boolean offerProjectileToPlayer(Projectile projectile, IPlayer player) {
        return player.getPlayerCharacter().isCatchable(projectile);
    }
    
    public void registerControlEventHandlers() {
        MultiplePressedKeysEventHandler mpkeh = new MultiplePressedKeysEventHandler(
                this.gameControllerHelper.getHandler());
        this.mainController.getViewController().getMainMenu().getGameScene().setOnKeyPressed(mpkeh);
        this.mainController.getViewController().getMainMenu().getGameScene().setOnKeyReleased(mpkeh);
        MouseHandler mouseHandler = new MouseHandler();
        this.mainController.getViewController().getMainMenu().getGameScene().setOnMouseMoved(mouseHandler);
    }
    
    private void removeLastThree(IPlayer player) {
        for (int i = 0; i < 3; i++) {
            deleteProjectile(player.getPlayerCharacter().getLastUpdatedHand().removeGain());
        }
    }
    
    public void startTheGame() {
        
        this.animationTimer.start();
        this.animationTimer.getMediaPlayer().play();
    }
    
    public void stopTheGame() {
      
        this.animationTimer.stop();
        this.animationTimer.getMediaPlayer().pause();
    }
    
    @Override
    public void updateSaving(boolean save) {
        this.gameControllerHelper.handleSavingRequests(save);
    }
    
}