package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entities.Character;
import model.entities.IEntityGeneratorVisitor;

public class Clown extends Character {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8870240896492201820L;
    public final static Double CLOWNHEIGHT = 270.0;
    public final static Double CLOWNWIDTH = 184.0;
    private IHand leftHand;
    private IHand rightHand;
    
    public Clown(IEntityGeneratorVisitor visitor) {
        super("Clown", CLOWNWIDTH, CLOWNHEIGHT, visitor);
        
    }
    
    public Clown(String fileName) throws FileNotFoundException {
        super("Clown", CLOWNWIDTH, CLOWNHEIGHT, File.separator + fileName);
    }
    
    public IHand getLeftHand() {
        return leftHand;
    }
    
    public IHand getRightHand() {
        return rightHand;
    }
    
    private void initializeLeftHand() {
        leftHand = new Hand(
                
                new Point2D((this.getCurrentPosition().getX() + 23), (this.getCurrentPosition().getY() + 40)));
    }
    
    private void initializeRightHand() {
        rightHand = new Hand(new Point2D((this.getCurrentPosition().getX() + 160), this.getCurrentPosition().getY()));
    }
    
    @Override
    protected void moveRelativeHand(List<IHand> hands, Double deltaDistance) {
        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).move(deltaDistance);
            
        }
        
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.image = new ImageView(new Image(this.imagePath, CLOWNWIDTH, CLOWNHEIGHT, false, true));
        this.image.setX(this.currentPosition.getX());
        this.image.setY(this.currentPosition.getY());
    }
    
    @Override
    public void setIntialPosition(Point2D intialPosition) {
        super.setIntialPosition(intialPosition);
        initializeRightHand();
        initializeLeftHand();
        this.hands.add(leftHand);
        this.hands.add(rightHand);
    }
    
}
