
package controller;

import org.apache.logging.log4j.Logger;

import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logs.Log4j;

public class AnimationHandler extends AnimationTimer {
    
    private MainController mainController;
    private double projectileTime;
    private double time1;
    private double time2;
    private static Logger logger;
    private MediaPlayer mp;
    
    public MediaPlayer getMediaPlayer() {
        return mp;
    }
    
    public AnimationHandler(MainController mainController) {
       
        this.mainController = mainController;
        this.time1 = 0;
        this.time2 = 0;
        this.projectileTime = 0;
        String path = getClass().getResource("/circus.mp3").toString();
        Media media = new Media(path);
        this.mp = new MediaPlayer(media);
    }
    
    public double getProjectileTime() {
        return projectileTime;
    }
    
    @Override
    public void handle(long now) {
        
        time1 += 0.017;
        time2 += 0.017;
        if (time1 >= 0.035) {
            time1 = 0;
            this.mainController.getGameController().handlePlates();
            
        }
        
        if (time2 >= projectileTime) {
            time2 = 0;
            this.mainController.getGameController().dropNewProjectiles();
        }
    }
    
    public void setProjectileTime(double projectileTime) {
        this.projectileTime = projectileTime;
    }
    
}
