package game;

import org.apache.logging.log4j.Logger;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import logs.Log4j;

public class Main extends Application {
    
  
    public static void main(String[] args) {
     
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController.getInstance(primaryStage);
        
    }
    
}
