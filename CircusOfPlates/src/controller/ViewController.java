package controller;

import org.apache.logging.log4j.Logger;

import javafx.stage.Stage;
import logs.Log4j;
import model.helpers.ViewHelper;
import view.lancher.CircusOfPlatesLancherStage;
import view.mainMenu.MainMenu;

public class ViewController {
    
  
    private static ViewController instance;
    
    public static ViewController getInstance(MainController mainController, Stage primaryStage) {
        if (instance == null)
            instance = new ViewController(mainController, primaryStage);
        return instance;
    }
    
    private ViewHelper viewHelper;
    private MainMenu mainMenu;
    private CircusOfPlatesLancherStage circusOfPlatesLancherStage;
    
    private ViewController(MainController mainController, Stage primaryStage) {
       
        this.viewHelper = new ViewHelper(mainController);
        this.mainMenu = new MainMenu(mainController, primaryStage);
        this.circusOfPlatesLancherStage = new CircusOfPlatesLancherStage();
    }
    
    public void closeMainMenu() {
     
        this.mainMenu.closeMainMenu();
    }
    
    public MainMenu getMainMenu() {
        return mainMenu;
    }
    
    public ViewHelper getViewHelper() {
        return this.viewHelper;
    }
    
    public void hideSplash() {
        
        this.circusOfPlatesLancherStage.closeLancher();
    }
    
    public void openNewGameWindow(Stage parentStage) {
        
    }
    
    public void showMainMenu() {
        
        this.mainMenu.showStage();
    }
    
    public void showSplash() {
   
        this.circusOfPlatesLancherStage.showLancher();
    }
    
}
