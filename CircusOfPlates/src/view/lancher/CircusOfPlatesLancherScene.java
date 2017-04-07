package view.lancher;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CircusOfPlatesLancherScene extends Scene {

  public static double SPLASH_WIDTH;
  public static double SPLASH_HEIGHT;

  public CircusOfPlatesLancherScene() {
    super(new Pane(), Color.TRANSPARENT);
    Pane root = new Pane();
    Image image = new Image(getClass().getResourceAsStream(
        "/res" + File.separator + "Statics" + File.separator + "imageedit_1_8389931205.png"));
    SPLASH_WIDTH = image.getWidth();
    SPLASH_HEIGHT = image.getHeight();
    root.setPrefSize(image.getWidth(), image.getHeight());
    root.setBackground(null);
    root.getChildren().add(new ImageView(image));
    this.setRoot(root);
  }

}
