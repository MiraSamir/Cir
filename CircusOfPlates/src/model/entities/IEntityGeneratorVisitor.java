package model.entities;

import java.io.File;
import java.io.Serializable;

import model.entities.Entity;


public interface IEntityGeneratorVisitor extends Serializable {


  public File getRandomEntity(String path);

  public void setRandomEntity(Entity entity, String pathTail, Double entityWidth,
      Double entityHeight, String projectileType);
}
