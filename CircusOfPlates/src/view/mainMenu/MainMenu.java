package view.mainMenu;

import org.apache.logging.log4j.Logger;

import controller.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;
import logs.Log4j;
import view.GameOverScene;
import view.GameScene;
import view.MenuItem;

public class MainMenu {
    
    private static Logger logger;
    static {
        logger = Log4j.getInstance();
    }
    private MainController mainController;
    private Stage primaryStage;
    private MainMenuScene mainMenuScene;
    private GameScene gameScene;
    
    private Timeline timeline;
    
    private GameOverScene gameOverScene;
    
    public MainMenu(MainController mainController, Stage primaryStage) {
        this.mainController = mainController;
        this.primaryStage = primaryStage;
        this.mainMenuScene = new MainMenuScene(mainController);
        this.gameScene = new GameScene(mainController);
        this.gameOverScene = new GameOverScene(mainController);
        this.timeline = new Timeline();
        this.initMainMenu(false);
        this.primaryStage.setOnCloseRequest(e -> {
            e.consume();
            System.exit(0);
        });
    }
    
    public void closeMainMenu() {
        logger.info("Closing Main Menu...");
        this.primaryStage.close();
    }
    
    public void gameOver() {
        logger.info("Game Over!");
        this.printScore();
        this.primaryStage.setScene(this.gameOverScene);
        this.showStage();
        this.gameScene.setCursor(Cursor.NONE);
    }
    
    public GameScene getGameScene() {
        return gameScene;
    }
    
    public MainMenuScene getMainMenuScene() {
        return this.mainMenuScene;
    }
    
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }
    
    private void initGame() {
        logger.info("Initializing Game Scene...");
        this.gameScene.setTimerProperty(
                this.mainController.getGameController().getControllerHelper().getGameDurationInSeconds());
        this.setScoreView();
        this.timeline.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(
                        this.mainController.getGameController().getControllerHelper().getGameDurationInSeconds() + 1),
                        new KeyValue(this.gameScene.getIntegerProperty(), 0),
                        new KeyValue(this.mainController.getGameController().getControllerHelper().getTimer(), 0)));
        this.timeline.setOnFinished(e -> {
            this.mainController.getGameController().stopTheGame();
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                this.gameOver();
            });
            pause.play();
        });
        this.primaryStage.setScene(gameScene);
        this.showStage();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            this.play();
        });
        pause.play();
    }
    
    public void initMainMenu(boolean showStage) {
        logger.info("Initializing Main Menu...");
        this.mainMenuScene.defaultFormat();
        this.primaryStage.setScene(this.mainMenuScene);
        if (showStage)
            this.showStage();
    }
    
    public void loadGame() {
        this.gameScene.cleanScene();
        this.mainController.getGameController().loadGame();
        this.initGame();
    }
    
    public void newGame() {
        this.gameScene.cleanScene();
        this.mainController.getGameController().newGame();
        this.initGame();
    }
    
    public void pauseGame() {
        logger.info("Game is Paused.");
        this.mainMenuScene.setCursor(Cursor.HAND);
        
        this.mainController.getGameController().stopTheGame();
        this.timeline.pause();
        this.mainMenuScene.resumeFormat();
        this.primaryStage.setScene(this.mainMenuScene);
        this.showStage();
        this.mainMenuScene.getRoot().setCursor(Cursor.HAND);
    }
    
    private void play() {
        this.timeline.playFromStart();
        this.mainController.getGameController().startTheGame();
    }
    
    private void printScore() {
        for (int i = 0; i < this.mainController.getGameController().getControllerHelper().getPlayers().size(); i++) {
            this.gameOverScene.addItem(new MenuItem("PLAYER" + (i + 1) + "   " + this.mainController.getGameController()
                    .getControllerHelper().getPlayers().get(i).getPlayerScore().getScore()));
        }
    }
    
    public void resumeGame() {
        logger.info("Game is Resumed.");
        this.gameScene.setCursor(Cursor.DEFAULT);
        
        this.primaryStage.setScene(this.gameScene);
        this.showStage();
        this.gameScene.setCursor(Cursor.DEFAULT);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            this.mainController.getGameController().startTheGame();
            this.timeline.play();
        });
        pause.play();
    }
    
    private void setScoreView() {
        logger.info("Setting Score On View...");
        this.gameScene.setPlayer1ScoreProperty(this.mainController.getGameController().getControllerHelper()
                .getPlayers().get(0).getPlayerScore().getScoreProperty());
        if (this.mainController.getGameController().getControllerHelper().getPlayers().size() > 1)
            this.gameScene.setPlayer2ScoreProperty(this.mainController.getGameController().getControllerHelper()
                    .getPlayers().get(1).getPlayerScore().getScoreProperty());
    }
    
    public void showStage() {
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.show();
        this.primaryStage.requestFocus();
    }
    
}