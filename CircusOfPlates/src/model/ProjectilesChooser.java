package model;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;

import controller.DynamicProjectileLoader;
import controller.MainController;
import logs.Log4j;
import model.entities.Projectile;

public class ProjectilesChooser {
  private MainController mainController;
  private Random random;
  private DynamicProjectileLoader dynamicProjectileLoader;


  public ProjectilesChooser(MainController mainController) {
	
    this.mainController = mainController;
    this.random = new Random();
    this.dynamicProjectileLoader = new DynamicProjectileLoader();
  }

  public Projectile createProjectile() {
	 
    List<String> selectedProjectiles =
        mainController.getViewController().getViewHelper().getSelectedProjectiles();
    int index = random.nextInt(selectedProjectiles.size());
    return this.dynamicProjectileLoader.locateProjectile(selectedProjectiles.get(index));
  }

}
