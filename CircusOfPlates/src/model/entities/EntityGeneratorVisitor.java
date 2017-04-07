package model.entities;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityGeneratorVisitor implements IEntityGeneratorVisitor {
    
    /**
     * 
     */
    private static final long serialVersionUID = 6L;
    
    @Override
    public File getRandomEntity(String path) {
        File[] files = null;
        try {
            files = new File(this.getClass().getClassLoader().getResource("res" + File.separator + path).toURI())
                    .listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return files[new Random().nextInt(files.length)];
    }
    
    @Override
    public void setRandomEntity(Entity entity, String pathTail, Double entityWidth, Double entityHeight,
            String projectileType) {
        
        File imgPath = this.getRandomEntity(pathTail);
        
        Image selectedImg = new Image(imgPath.toURI().toString(), entityWidth, entityHeight, false, true);
        
        entity.imagePath = imgPath.toURI().toString();
        
        entity.image = new ImageView(selectedImg);
        entity.image.setCache(true);
        
        entity.setColor(imgPath.getName(), projectileType);
        
        entity.dimension = new Dimension2D(entityWidth, entityHeight);
        entity.intialPosition = new Point2D(0, 0);
        entity.currentPosition = new Point2D(0, 0);
        
    }
    
}
