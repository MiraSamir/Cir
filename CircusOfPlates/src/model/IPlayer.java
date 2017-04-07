package model;

import model.entities.Character;

public interface IPlayer extends java.io.Serializable {

  public Character getPlayerCharacter();

  public Score getPlayerScore();

  public void incrementScore();


}
