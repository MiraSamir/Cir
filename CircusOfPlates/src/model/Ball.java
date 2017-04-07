package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.IEntityGeneratorVisitor;
import model.entities.Projectile;

public class Ball extends Projectile implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 18L;
    
    protected final static Double BALLHEIGHT = 45.0;
    protected final static Double BALLWIDTH = 45.0;
    
    public Ball(IEntityGeneratorVisitor visitor) {
        super("Ball", BALLWIDTH, BALLHEIGHT, visitor);
        
    }
    
    public Ball(String fileName) throws FileNotFoundException {
        super("Ball", BALLWIDTH, BALLHEIGHT, File.separator + fileName);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.image = new ImageView(new Image(this.imagePath, BALLWIDTH, BALLHEIGHT, false, true));
        this.image.setX(this.currentPosition.getX());
        this.image.setY(this.currentPosition.getY());
    }
    
}
