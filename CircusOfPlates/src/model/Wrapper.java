
package model;

import javafx.scene.image.Image;

public class Wrapper implements IWrapper {

  @Override
  public Image wrap(String imgPath, Double requiredWidth, Double requiredHeight) {
    return new Image(imgPath, requiredWidth, requiredHeight, true, true);
  }

}
