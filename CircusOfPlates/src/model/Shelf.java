package model;

import static view.Util.SCREEN_HEIGHT;
import static view.Util.SCREEN_WIDTH;

import javafx.geometry.Point2D;;

public enum Shelf {


  FIRSTLEFT(new Point2D(0 + SCREEN_WIDTH / 12, SCREEN_HEIGHT / 30),
      new Point2D(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 30)), FIRSTRIGHT(
          new Point2D(SCREEN_WIDTH - SCREEN_WIDTH / 12, SCREEN_HEIGHT / 30),
          new Point2D(2 * SCREEN_WIDTH / 3, SCREEN_HEIGHT / 30)), SECONDLEFT(
              new Point2D(0 + SCREEN_WIDTH / 12, SCREEN_HEIGHT / 12),
              new Point2D(2 * SCREEN_WIDTH / 9, SCREEN_HEIGHT / 12)), SECONDRIGHT(
                  new Point2D(SCREEN_WIDTH - SCREEN_WIDTH / 12, SCREEN_HEIGHT / 12),
                  new Point2D(7 * SCREEN_WIDTH / 9, SCREEN_HEIGHT / 12)), THIRDLEFT(
                      new Point2D(0 + SCREEN_WIDTH / 12, SCREEN_HEIGHT / 7.5),
                      new Point2D(SCREEN_WIDTH / 9, SCREEN_HEIGHT / 7.5)), THIRDRIGHT(
                          new Point2D(SCREEN_WIDTH - SCREEN_WIDTH / 12, SCREEN_HEIGHT / 7.5),
                          new Point2D(8 * SCREEN_WIDTH / 9, SCREEN_HEIGHT / 7.5));

  private final Point2D initialPoint;
  private final Point2D ejaculationPoint;

  private Shelf(Point2D initialPoint, Point2D ejaculationPoint) {
    this.initialPoint = initialPoint;
    this.ejaculationPoint = ejaculationPoint;
  }

  public Point2D getEjaculationPoint() {
    return this.ejaculationPoint;
  }

  public Point2D getInitialPoint() {
    return this.initialPoint;
  }
}
