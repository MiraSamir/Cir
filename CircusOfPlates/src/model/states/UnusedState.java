package model.states;

import org.apache.logging.log4j.Logger;

import javafx.scene.image.ImageView;
import logs.Log4j;


public class UnusedState implements ProjectileState, java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 14L;

  private static Logger logger;
  
  static {
	  logger = Log4j.getInstance();
  }
  
  public UnusedState(){
	  logger.info("Projectile State is Changed to Unused State");
  }
  @Override
  public void move(ImageView image, Double xInitial) {
    // TODO Auto-generated method stub

  }

}
