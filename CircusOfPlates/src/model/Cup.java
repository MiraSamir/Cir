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

public class Cup extends Projectile implements Serializable {
    private static final long serialVersionUID = 19L;
    
    protected final static Double CUPHEIGHT = 45.0;
    protected final static Double CUPWIDTH = 45.0;
    
    public Cup(IEntityGeneratorVisitor visitor) {
        super("Cup", CUPWIDTH, CUPHEIGHT, visitor);
    }
    
    public Cup(String fileName) throws FileNotFoundException {
        super("Cup", CUPWIDTH, CUPHEIGHT, File.separator + fileName);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.image = new ImageView(new Image(this.imagePath, CUPWIDTH, CUPHEIGHT, false, true));
        this.image.setX(this.currentPosition.getX());
        this.image.setY(this.currentPosition.getY());
    }
    
}
