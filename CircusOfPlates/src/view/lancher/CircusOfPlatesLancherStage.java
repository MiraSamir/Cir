package view.lancher;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CircusOfPlatesLancherStage extends Stage {

  private CircusOfPlatesLancherScene circusOfPlatesLancherScene;


  public CircusOfPlatesLancherStage() {
    super();
    final Rectangle2D bounds = Screen.getScreens().get(0).getBounds();

    this.circusOfPlatesLancherScene = new CircusOfPlatesLancherScene();
    this.initStyle(StageStyle.TRANSPARENT);
    this.initOwner(null);
    this.setScene(this.circusOfPlatesLancherScene);
    this.setX(
        bounds.getMinX() + bounds.getWidth() / 2 - CircusOfPlatesLancherScene.SPLASH_WIDTH / 2);
    this.setY(
        bounds.getMinY() + bounds.getHeight() / 2 - CircusOfPlatesLancherScene.SPLASH_HEIGHT / 2);

  }

  public void closeLancher() {
    this.close();
  }

  public void showLancher() {
    this.show();
    this.requestFocus();
  }


}
