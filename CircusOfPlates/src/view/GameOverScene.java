package view;

import java.io.InputStream;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverScene extends Scene {
    
    private static class MenuBox extends StackPane {
        
        private VBox vbox;
        
        private Text text;
        
        private Line hSep;
        
        public MenuBox(String title, MenuItem... items) {
            Rectangle bg = new Rectangle(300, 600);
            bg.setOpacity(0.2);
            
            DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
            shadow.setSpread(0.8);
            
            bg.setEffect(shadow);
            
            text = new Text(title + "   ");
            text.setFont(font);
            text.setFill(Color.WHITE);
            
            hSep = new Line();
            hSep.setEndX(250);
            hSep.setStroke(Color.DARKGREEN);
            hSep.setOpacity(0.4);
            
            Line vSep = new Line();
            vSep.setStartX(300);
            vSep.setEndX(300);
            vSep.setEndY(600);
            vSep.setStroke(Color.DARKGREEN);
            vSep.setOpacity(0.4);
            
            vbox = new VBox();
            vbox.setAlignment(Pos.TOP_RIGHT);
            vbox.setPadding(new Insets(60, 0, 0, 0));
            vbox.getChildren().addAll(text, hSep);
            vbox.getChildren().addAll(items);
            
            setAlignment(Pos.TOP_RIGHT);
            getChildren().addAll(bg, vSep, vbox);
        }
        
        public void addItem(MenuItem item) {
            Node node = vbox.getChildren().remove(vbox.getChildren().size() - 1);
            vbox.getChildren().addAll(item, node);
        }
        
        public void clearItems() {
            Node node = vbox.getChildren().remove(vbox.getChildren().size() - 1);
            vbox.getChildren().clear();
            vbox.getChildren().addAll(text, hSep, node);
        }
        
    }
    
    private static Font font;
    private Pane root;
    private MenuBox menu;
    
    public GameOverScene(MainController mainController) {
        super(new Pane());
        
        this.root = new Pane();
        root.setPrefSize(Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
        
        InputStream fontStream = this.getClass().getClassLoader().getResourceAsStream("res/fonts/cod_font.ttf");
        ImageView img = new ImageView(new Image(this.getClass().getClassLoader().getResource("res/Statics/circus1.jpg")
                
                .toExternalForm()));
        img.setFitWidth(Util.SCREEN_WIDTH);
        img.setFitHeight(Util.SCREEN_HEIGHT);
        
        root.getChildren().add(img);
        
        font = Font.loadFont(fontStream, 30);
        
        MenuItem itemQuit = new MenuItem("MAIN MENU");
        itemQuit.setOnMouseClicked(event -> {
            mainController.getViewController().getMainMenu().initMainMenu(true);
            this.clearItems();
        });
        
        menu = new MenuBox("GAME OVER!", itemQuit);
        
        root.getChildren().add(menu);
        
        this.setRoot(this.root);
    }
    
    public void addItem(MenuItem item) {
        this.menu.addItem(item);
    }
    
    public void clearItems() {
        this.menu.clearItems();
    }
    
}