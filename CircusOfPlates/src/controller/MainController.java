package controller;

import org.apache.logging.log4j.Logger;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import logs.Log4j;

public class MainController {
    
    
    
    private ViewController viewController;
    private GameController gameController;
    private SavingOriginator gameOriginator;
    private static MainController instance;
    
    public static MainController getInstance(Stage primaryStage) {
        if (instance == null)
            instance = new MainController(primaryStage);
        return instance;
    }
    
    private MainController(Stage primaryStage) {
   
        this.gameController = GameController.getInstance(this);
        this.gameOriginator = SavingOriginator.getInstance(this);
        this.viewController = ViewController.getInstance(this, primaryStage);
        
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        this.viewController.showSplash();
        pause.setOnFinished(event -> {
            this.viewController.hideSplash();
            this.viewController.showMainMenu();
        });
        pause.play();
    }
    
    public GameController getGameController() {
        return gameController;
    }
    
    public SavingOriginator getGameOriginator() {
        return gameOriginator;
    }
    
    public ViewController getViewController() {
        return viewController;
    }
    
    public void newGame() {
        this.viewController.openNewGameWindow(this.viewController.getMainMenu().getPrimaryStage());
    }
}
