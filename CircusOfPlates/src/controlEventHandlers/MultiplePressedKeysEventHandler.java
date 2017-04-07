package controlEventHandlers;

import java.util.EnumSet;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MultiplePressedKeysEventHandler implements EventHandler<KeyEvent> {
    
    public class MultiKeyEvent {
        public boolean isPressed(final KeyCode key) {
            return buffer.contains(key);
        }
    }
    
    public interface MultiKeyEventHandler {
        void handle(final MultiKeyEvent event);
    }
    
    private final Set<KeyCode> buffer = EnumSet.noneOf(KeyCode.class);
    
    private final MultiKeyEvent multiKeyEvent = new MultiKeyEvent();
    
    private final MultiKeyEventHandler multiKeyEventHandler;
    
    public MultiplePressedKeysEventHandler(final MultiKeyEventHandler handler) {
        this.multiKeyEventHandler = handler;
    }
    
    @Override
    public void handle(final KeyEvent event) {
        final KeyCode code = event.getCode();
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            buffer.add(code);
            multiKeyEventHandler.handle(multiKeyEvent);
            if (code == KeyCode.ESCAPE)
                buffer.remove(code);
        } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            buffer.remove(code);
        }
        event.consume();
    }
}