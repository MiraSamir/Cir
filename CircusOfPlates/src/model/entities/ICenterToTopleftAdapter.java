package model.entities;

import javafx.geometry.Point2D;
import model.entities.Projectile;

public interface ICenterToTopleftAdapter extends java.io.Serializable {

  public void setCurrentPosition(Point2D currentPositionCentered);

  public void setEjaculationPoint(Projectile projectile, Point2D ejaculationPointCentered);

  public void setHeight(Double charheight);

  public void setInitialPosition(Point2D initialPositionCentered);



  public void setWidth(Double charwidth);
}
