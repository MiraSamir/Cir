package model.helpers;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import model.ISavingObserver;

public class ViewHelper implements ISavingObserver {
    
    private MainController mainController;
    private List<String> selectedProjectiles;
    
    public ViewHelper(MainController mainController) {
        this.mainController = mainController;
        this.selectedProjectiles = new ArrayList<>();
        this.selectedProjectiles.add("Plate");// to be removed
        this.mainController.getGameOriginator().addObserver(this);
    }
    
    public void clearProjectiles() {
        this.selectedProjectiles.clear();
    }
    
    public List<String> getSelectedProjectiles() {
        return selectedProjectiles;
    }
    
    public void registerProjectile(String projectile) {
        this.selectedProjectiles.add(projectile);
    }
    
    public void resetProjectiles() {
        this.selectedProjectiles.clear();
    }
    
    @Override
    public void updateSaving(boolean save) {
        if (save) {
            this.mainController.getGameOriginator().setSelectedProjectiles(this.selectedProjectiles);
        } else {
            this.selectedProjectiles = this.mainController.getGameOriginator().getSelectedProjectiles();
        }
    }
    
}