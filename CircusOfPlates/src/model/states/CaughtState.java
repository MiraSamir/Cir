package model.states;


import org.apache.logging.log4j.Logger;

import javafx.scene.image.ImageView;
import logs.Log4j;


public class CaughtState implements ProjectileState, java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 11L;
  
  private static Logger logger;
  
  static {
	  logger = Log4j.getInstance();
	 
  }
  
  public CaughtState() {
	  logger.info("Projectile State is Changed to CaughtState.");
  }
  

  @Override
  public void move(ImageView image, Double xInitial) {
    // TODO Auto-generated method stub

  }

}
