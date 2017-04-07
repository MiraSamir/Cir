package model;

import java.util.List;

import model.entities.Projectile;

public class HandIterator implements Iterator<IHand> {
  private List<Projectile> stackGains;
  private int pointer;

  public HandIterator(IHand hand) {
    this.stackGains = hand.getGains();
    this.pointer = this.stackGains.size();
  }

  @Override
  public boolean hasNext() {
    if (!stackGains.isEmpty() && (pointer - 1) >= 0) {
      return true;
    }
    return false;
  }

  @Override
  public Object next() {
    if (!stackGains.isEmpty() && hasNext()) {
      pointer--;
      Projectile gain = stackGains.get(pointer);
      return gain;
    }
    return null;
  }

  @Override
  public int size() {
    return this.stackGains.size();
  }



}
