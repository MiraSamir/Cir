package view.mainMenu;

import java.util.List;

import controller.MainController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.levels.HighLevel;
import model.levels.LowLevel;
import model.levels.MediumLevel;
import view.Util;

public class MainMenuScene extends Scene {
    
    private class GameMenu extends Parent {
        
        private VBox menu0;
        private VBox menu1;
        private VBox menu2;
        private VBox menu3;
        private VBox menu4;
        private final int offset = 400;
        private MenuButton btnResume;
        private MenuButton btnNewGame;
        private MenuButton btnLoadGame;
        private MenuButton btnSaveGame;
        private MenuButton btnOptions;
        private MenuButton btnExit;
        private MenuButton btnBack0;
        private MenuButton btnAboutGame;
        
        public GameMenu() {
            menu0 = new VBox(10);
            menu1 = new VBox(10);
            menu2 = new VBox(10);
            menu3 = new VBox(10);
            menu4 = new VBox(10);
            
            menu0.setTranslateX(100);
            menu0.setTranslateY(200);
            
            menu1.setTranslateX(100);
            menu1.setTranslateY(200);
            
            menu2.setTranslateX(100);
            menu2.setTranslateY(200);
            
            menu3.setTranslateX(100);
            menu3.setTranslateY(200);
            
            menu4.setTranslateX(100);
            menu4.setTranslateY(200);
            
            menu1.setTranslateX(offset);
            menu2.setTranslateX(offset);
            menu3.setTranslateX(offset);
            menu4.setTranslateX(offset);
            
            btnResume = new MenuButton("RESUME");
            btnResume.setOnMouseClicked(event -> {
                mainController.getViewController().getMainMenu().resumeGame();
            });
            
            btnNewGame = new MenuButton("NEW GAME");
            btnNewGame.setOnMouseClicked(event -> {
                getChildren().add(menu2);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu0.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });
            
            btnSaveGame = new MenuButton("SAVE GAME");
            btnSaveGame.setOnMouseClicked(event -> {
                mainController.getGameOriginator().Save();
            });
            
            btnLoadGame = new MenuButton("Load GAME");
            btnLoadGame.setOnMouseClicked(event -> {
                mainController.getGameOriginator().Load();
                if (mainController.getGameController().getControllerHelper().getPlayers().size() > 0)
                    mainController.getViewController().getMainMenu().loadGame();
                
            });
            btnAboutGame = new MenuButton("ABOUT");
            btnAboutGame.setOnMouseClicked(event -> {
                
            });
            
            btnOptions = new MenuButton("OPTIONS");
            btnOptions.setOnMouseClicked(event -> {
                getChildren().add(menu1);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu0.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });
            MenuButton btnEasy = new MenuButton("EASY");
            btnEasy.setOnMouseClicked(event -> {
                mainController.getGameController().getControllerHelper().setLevel(new LowLevel());
                fromMenu2ToMenu3();
            });
            MenuButton btnMedium = new MenuButton("MEDIUM");
            btnMedium.setOnMouseClicked(event -> {
                mainController.getGameController().getControllerHelper().setLevel(new MediumLevel());
                fromMenu2ToMenu3();
            });
            MenuButton btnHard = new MenuButton("HARD");
            btnHard.setOnMouseClicked(event -> {
                mainController.getGameController().getControllerHelper().setLevel(new HighLevel());
                fromMenu2ToMenu3();
            });
            MenuButton btn1Player = new MenuButton("1 PLAYER");
            btn1Player.setOnMouseClicked(event -> {
                mainController.getGameController().getControllerHelper().register1Player();
                mainController.getViewController().getMainMenu().newGame();
                fromMenu3ToMenu0();
            });
            MenuButton btn2Player = new MenuButton("2 PLAYERS");
            btn2Player.setOnMouseClicked(event -> {
                mainController.getGameController().getControllerHelper().register2Players();
                mainController.getViewController().getMainMenu().newGame();
                fromMenu3ToMenu0();
            });
            
            btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event -> {
                System.exit(0);
            });
            
            btnBack0 = new MenuButton("BACK");
            btnBack0.setOnMouseClicked(event -> {
                getChildren().add(menu0);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() + offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu1.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });
            
            MenuButton btnBack1 = new MenuButton("BACK");
            btnBack1.setOnMouseClicked(event -> {
                getChildren().add(menu0);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
                tt.setToX(menu2.getTranslateX() + offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                tt1.setToX(menu2.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu2);
                });
            });
            MenuButton btnBack2 = new MenuButton("BACK");
            btnBack2.setOnMouseClicked(event -> {
                getChildren().add(menu2);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
                tt.setToX(menu3.getTranslateX() + offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu2);
                tt1.setToX(menu3.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu3);
                });
            });
            
            MenuButton btnPlate = new MenuButton("PLATE\t\t\t\t");
            MenuButton btnBall = new MenuButton("BALL\t\t\t\t");
            btnBall.setOnMouseClicked(event -> {
                this.reverseMark(btnBall);
            });
            MenuButton btnCup = new MenuButton("CUP\t\t\t\t\t");
            btnCup.setOnMouseClicked(event -> {
                this.reverseMark(btnCup);
            });
            MenuButton btnTeddyBear = new MenuButton("TEDDY BEAR\t\t\t");
            btnTeddyBear.setOnMouseClicked(event -> {
                this.reverseMark(btnTeddyBear);
            });
            MenuButton btnSound = new MenuButton("SHAPES");
            btnSound.setOnMouseClicked(event -> {
                List<String> list = mainController.getViewController().getViewHelper().getSelectedProjectiles();
                for (int i = 0; i < list.size(); i++) {
                    String s = list.get(i);
                    if (s.equals("Plate") && !btnPlate.text.getText().contains("\u2713")) {
                        btnPlate.text.setText(btnPlate.text.getText() + "\u2713");
                    } else if (s.equals("Cup") && !btnCup.text.getText().contains("\u2713")) {
                        btnCup.text.setText(btnPlate.text.getText() + "\u2713");
                    } else if (s.equals("TeddyBear") && !btnTeddyBear.text.getText().contains("\u2713")) {
                        btnTeddyBear.text.setText(btnPlate.text.getText() + "\u2713");
                    } else if (s.equals("Ball") && !btnBall.text.getText().contains("\u2713")) {
                        btnBall.text.setText(btnPlate.text.getText() + "\u2713");
                    }
                }
                getChildren().add(menu4);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
                tt.setToX(menu1.getTranslateX() - offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu4);
                tt1.setToX(menu1.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu1);
                });
            });
            MenuButton btnBack3 = new MenuButton("BACK");
            btnBack3.setOnMouseClicked(event -> {
                mainController.getViewController().getViewHelper().clearProjectiles();
                if (btnPlate.text.getText().contains("\u2713"))
                    mainController.getViewController().getViewHelper().registerProjectile("Plate");
                if (btnCup.text.getText().contains("\u2713"))
                    mainController.getViewController().getViewHelper().registerProjectile("Cup");
                if (btnTeddyBear.text.getText().contains("\u2713"))
                    mainController.getViewController().getViewHelper().registerProjectile("TeddyBear");
                if (btnBall.text.getText().contains("\u2713"))
                    mainController.getViewController().getViewHelper().registerProjectile("Ball");
                getChildren().add(menu1);
                
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu4);
                tt.setToX(menu4.getTranslateX() + offset);
                
                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
                tt1.setToX(menu4.getTranslateX());
                
                tt.play();
                tt1.play();
                
                tt.setOnFinished(evt -> {
                    getChildren().remove(menu4);
                });
            });
            
            menu0.getChildren().addAll(btnNewGame, btnLoadGame, btnOptions, btnAboutGame, btnExit);
            menu1.getChildren().addAll(btnBack0, btnSound);
            menu2.getChildren().addAll(btnBack1, btnEasy, btnMedium, btnHard);
            menu3.getChildren().addAll(btnBack2, btn1Player, btn2Player);
            menu4.getChildren().addAll(btnBack3, btnPlate, btnBall, btnCup, btnTeddyBear);
            
            Rectangle bg = new Rectangle(Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.4);
            
            getChildren().addAll(bg, menu0);
        }
        
        public void defaultFormat() {
            this.menu0.getChildren().clear();
            this.menu0.getChildren().addAll(btnNewGame, btnLoadGame, btnOptions, btnAboutGame, btnExit);
        }
        
        private void fromMenu2ToMenu3() {
            getChildren().add(menu3);
            
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt.setToX(menu2.getTranslateX() - offset);
            
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu3);
            tt1.setToX(menu2.getTranslateX());
            
            tt.play();
            tt1.play();
            
            tt.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
        }
        
        private void fromMenu3ToMenu0() {
            getChildren().add(menu0);
            
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu3);
            tt.setToX(menu3.getTranslateX() - offset);
            
            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu3.getTranslateX());
            
            tt.play();
            tt1.play();
            
            tt.setOnFinished(evt -> {
                getChildren().remove(menu3);
            });
        }
        
        public void resumeFormat() {
            this.menu0.getChildren().clear();
            this.menu0.getChildren().addAll(btnResume, btnNewGame, btnSaveGame, btnLoadGame, btnOptions, btnExit);
        }
        
        private void reverseMark(MenuButton button) {
            if (button.text.getText().contains("\u2713"))
                button.text.setText(button.text.getText().replaceAll("\u2713", ""));
            else
                button.text.setText(button.text.getText() + "\u2713");
        }
    }
    
    private static class MenuButton extends StackPane {
        private Text text;
        
        public MenuButton(String name) {
            text = new Text(name);
            text.getFont();
            text.setFont(Font.font(20));
            text.setFill(Color.WHITE);
            
            Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));
            
            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);
            
            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            
            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            
            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());
            
            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }
    
    private MainController mainController;
    
    private GameMenu gameMenu;
    
    public MainMenuScene(MainController mainController) {
        super(new Pane());
        this.mainController = mainController;
        Pane root = new Pane();
        root.setPrefSize(Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
        
        Image img = new Image(this.getClass().getResourceAsStream("/res/Statics/menu.png"));
        
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(Util.SCREEN_WIDTH);
        imgView.setFitHeight(Util.SCREEN_HEIGHT);
        
        gameMenu = new GameMenu();
        gameMenu.setVisible(false);
        
        root.getChildren().addAll(imgView, gameMenu);
        
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    
                    gameMenu.setVisible(true);
                    ft.play();
                } else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });
        this.setRoot(root);
    }
    
    public void defaultFormat() {
        this.gameMenu.defaultFormat();
    }
    
    public void resumeFormat() {
        this.gameMenu.resumeFormat();
    }
    
}