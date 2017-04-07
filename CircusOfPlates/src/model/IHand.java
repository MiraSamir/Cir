package model;

import java.util.Stack;

import javafx.geometry.Point2D;
import model.entities.Projectile;

public interface IHand extends Movable {

  public void addGain(Projectile projectile);

  public Stack<Projectile> getGains();

  public HandIterator getHandIterator();

  public Point2D getPointerOnStack();

  public Double getStackWidth();

  public void move(Double deltaChange);

  public Projectile removeGain();

  public void removeGain(Projectile projectile);

}
