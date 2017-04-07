package controlEventHandlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import controlEventHandlers.MultiplePressedKeysEventHandler.MultiKeyEvent;
import controlEventHandlers.MultiplePressedKeysEventHandler.MultiKeyEventHandler;
import controller.MainController;
import javafx.scene.input.KeyCode;
import logs.Log4j;

public class KeyboardHandler implements MultiKeyEventHandler {
    
 
    
    private List<model.entities.Character> characters;
    
    private MainController mainController;
    
    public KeyboardHandler(MainController mainController) {
        
        this.characters = new ArrayList<>();
        this.mainController = mainController;
    }
    
    public void clearCharacters() {
        this.characters.clear();
    }
    
    @Override
    public void handle(MultiKeyEvent event) {
        if (event.isPressed(KeyCode.LEFT)) {
            if (this.mainController.getGameController().canMoveToTheLeft(this.characters.get(0)))
                characters.get(0).moveToTheLeft();
        }
        if (event.isPressed(KeyCode.RIGHT)) {
            if (this.mainController.getGameController().canMoveToTheRight(this.characters.get(0)))
                characters.get(0).moveToTheRight();
        }
        if (this.characters.size() > 1 && event.isPressed(KeyCode.D)) {
            if (this.mainController.getGameController().canMoveToTheRight(this.characters.get(1)))
                characters.get(1).moveToTheRight();
        }
        if (this.characters.size() > 1 && event.isPressed(KeyCode.A)) {
            if (this.mainController.getGameController().canMoveToTheLeft(this.characters.get(1)))
                characters.get(1).moveToTheLeft();
        }
        if (event.isPressed(KeyCode.ESCAPE)) {
            this.mainController.getViewController().getMainMenu().pauseGame();
        }
    }
    
    public void registerCharacter(model.entities.Character character) {
        this.characters.add(character);
    }
    
}