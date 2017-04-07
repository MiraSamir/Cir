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

public class Plate extends Projectile implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 20L;
    protected final static Double PLATEHEIGHT = 30.0;
    protected final static Double PLATEWIDTH = 60.0;
    
    public Plate(IEntityGeneratorVisitor visitor) {
        super("Plate", PLATEWIDTH, PLATEHEIGHT, visitor);
    }
    
    public Plate(String fileName) throws FileNotFoundException {
        super("Plate", PLATEWIDTH, PLATEHEIGHT, File.separator + fileName);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.image = new ImageView(new Image(this.imagePath, PLATEWIDTH, PLATEHEIGHT, false, true));
        this.image.setX(this.currentPosition.getX());
        this.image.setY(this.currentPosition.getY());
    }
}
