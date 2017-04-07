package controlEventHandlers;

import org.apache.logging.log4j.Logger;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import logs.Log4j;

public class MouseHandler implements EventHandler<MouseEvent> {
 
    
    private model.entities.Character character;
    
    public MouseHandler() {
       
    }
    
    @Override
    public void handle(MouseEvent event) {
        if (this.character != null)
            this.character.moveTo(event.getX());
    }
    
    public void setCharacter(model.entities.Character character) {
        this.character = character;
    }
}