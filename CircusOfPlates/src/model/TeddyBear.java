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

public class TeddyBear extends Projectile implements Serializable {
    
    private static final long serialVersionUID = 21L;
    
    protected final static Double TEDDYBEARHEIGHT = 50.0;
    protected final static Double TEDDYBEARWIDTH = 50.0;
    
    public TeddyBear(IEntityGeneratorVisitor visitor) {
        super("TeddyBear", TEDDYBEARWIDTH, TEDDYBEARHEIGHT, visitor);
    }
    
    public TeddyBear(String fileName) throws FileNotFoundException {
        super("TeddyBear", TEDDYBEARWIDTH, TEDDYBEARHEIGHT, File.separator + fileName);
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.image = new ImageView(new Image(this.imagePath, TEDDYBEARWIDTH, TEDDYBEARHEIGHT, false, true));
        this.image.setX(this.currentPosition.getX());
        this.image.setY(this.currentPosition.getY());
    }
}
