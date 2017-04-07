package model.states;

import org.apache.logging.log4j.Logger;

import javafx.scene.image.ImageView;
import logs.Log4j;
import view.Util;


public class FlyingState implements ProjectileState, java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 12L;
  private static double angle = 0.0;
  private static double ay = 9.8;
  private static double ax = 0;
  private static double deltaTime = 0.06;
  public static double projectileSpeed;
  private static Logger logger;
  
  static {
	  logger = Log4j.getInstance();
	 
  }
  

  public static void setProjectileSpeed(double projectileSpeed) {
    FlyingState.projectileSpeed = projectileSpeed;
  }

  private double Vx, Vy;



  public FlyingState() {
	  logger.info("Projectile State is Changed to Flying State.");
    this.Vx = projectileSpeed * Math.cos(angle * (Math.PI / 180.0));
    this.Vy = projectileSpeed * Math.sin(angle * (Math.PI / 180.0));
  }


  @Override
  public void move(ImageView image, Double xInitial) {
    if (xInitial < Util.SCREEN_WIDTH / 2) {
      image.setX(image.getX() + Vx * deltaTime);
      image.setY(image.getY() + Vy * deltaTime);
    } else {
      image.setX(image.getX() - Vx * deltaTime);
      image.setY(image.getY() + Vy * deltaTime);
    }
    Vx += ax * deltaTime;
    Vy += ay * deltaTime;
  }

  // public void setProjectileSpeed(double speed) {
  // this.projectileSpeed = speed;
  // }

}
