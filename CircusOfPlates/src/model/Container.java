package model;

import model.entities.Projectile;

public interface Container {
    public Iterator<Projectile> getIterator();
}
