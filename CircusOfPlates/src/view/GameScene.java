package view;

import controller.MainController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameScene extends Scene {
    
    private Pane root;
    private IntegerProperty timerIntegerProperty;
    private Label timerLabel;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private Canvas canvas;
    
    public GameScene(MainController mainController) {
        super(new Pane());
        
        this.root = new Pane();
        
        this.timerLabel = new Label();
        this.timerLabel.setTextFill(Color.RED);
        this.timerLabel.setStyle("-fx-font-size: 4em;");
        this.timerLabel.setTranslateX(Util.SCREEN_WIDTH / 2);
        
        this.player1ScoreLabel = new Label();
        this.player1ScoreLabel.setTextFill(Color.RED);
        this.player1ScoreLabel.setStyle("-fx-font-size: 4em;");
        
        this.player2ScoreLabel = new Label();
        this.player2ScoreLabel.setTextFill(Color.RED);
        this.player2ScoreLabel.setStyle("-fx-font-size: 4em;");
        this.player2ScoreLabel.translateXProperty().bind(new SimpleDoubleProperty(Util.SCREEN_WIDTH)
                .subtract(this.player2ScoreLabel.textProperty().length().multiply(32)));
        this.setCursor(Cursor.NONE);
        this.canvas = new Canvas(Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
        this.root.getChildren().addAll(canvas);
        this.root.setBackground(new Background(new BackgroundImage(
                new Image(this.getClass().getClassLoader().getResource("res/Statics/circus1.jpg").toExternalForm()),
                null, null, null,
                new BackgroundSize(Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT, false, false, false, false))));
        this.setRoot(this.root);
    }
    
    public void cleanScene() {
        this.root.getChildren().clear();
        this.root.getChildren().add(this.canvas);
    }
    
    public IntegerProperty getIntegerProperty() {
        return timerIntegerProperty;
    }
    
    public Pane getRootNode() {
        return this.root;
    }
    
    public void setPlayer1ScoreProperty(IntegerProperty in) {
        this.player1ScoreLabel.textProperty().bind(in.asString());
        this.root.getChildren().add(this.player1ScoreLabel);
    }
    
    public void setPlayer2ScoreProperty(IntegerProperty in) {
        this.player2ScoreLabel.textProperty().bind(in.asString());
        this.root.getChildren().add(this.player2ScoreLabel);
    }
    
    public void setTimerProperty(int in) {
        this.timerIntegerProperty = new SimpleIntegerProperty(in);
        this.timerLabel.textProperty().bind(timerIntegerProperty.asString());
        this.root.getChildren().add(this.timerLabel);
    }
    
}