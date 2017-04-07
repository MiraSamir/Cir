 package model.states;

import org.apache.logging.log4j.Logger;

import javafx.scene.image.ImageView;
import logs.Log4j;
import view.Util;


public class OnShelfState implements ProjectileState, java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 13L;
  private static Logger logger;
  
  static {
	  logger = Log4j.getInstance();
  }
  
  public OnShelfState() {
	  logger.info("Projectile State is Changed to OnShelf State.");
  }
  @Override
  public void move(ImageView image, Double xInitial) {
    if (xInitial < Util.SCREEN_WIDTH / 2) {
      image.setX(image.getX() + 5);
    } else {
      image.setX(image.getX() - 5);
    }

  }

}
