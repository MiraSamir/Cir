package controlEventHandlers;

import controller.MainController;
import javafx.event.EventHandler;
import model.entities.Character;


public abstract class ControlEventHandler {
  protected Character character;
  protected MainController mainController;

  public ControlEventHandler(MainController mainController) {
    this.mainController = mainController;
  }


  public Character getCharacter() {
    return character;
  }

  public abstract EventHandler<?> handle();

  public void setCharacter(Character character) {
    this.character = character;
  }

}
