package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

import org.apache.logging.log4j.Logger;

import javafx.geometry.Point2D;
import logs.Log4j;
import model.entities.Projectile;
import model.states.CaughtState;

public class Hand implements IHand, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4L;
   
    private Stack<Projectile> gains;
    
    private transient Point2D pointerOnStack;
    
    private Double stackWidth;
    
    private transient Point2D currentPosition; // this is centered
    
    public Hand(Point2D initialPosition) { // this is centered
      
        gains = new Stack<Projectile>();
        this.currentPosition = new Point2D(initialPosition.getX(), initialPosition.getY());
        this.pointerOnStack = new Point2D(initialPosition.getX(), initialPosition.getY());
        this.stackWidth = 40.0;
    }
    
    @Override
    public void addGain(Projectile projectile) {
       
        projectile.setCurrentPosition(new Point2D(this.pointerOnStack.getX(),
                this.pointerOnStack.getY() - projectile.getDimension().getHeight() / 2.0), false);
        projectile.setProjectileState(new CaughtState());
        this.gains.add(projectile);
        
        setPointerOnStack(projectile, true);
        
    }
    
    @Override
    public Stack<Projectile> getGains() {
        return gains;
    }
    
    @Override
    public HandIterator getHandIterator() {
        
        return new HandIterator(this);
    }
    
    @Override
    public Point2D getPointerOnStack() {
        return pointerOnStack;
    }
    
    @Override
    public Double getStackWidth() {
        return this.stackWidth;
    }
    
    @Override
    public void move(Double deltaChange) {

        this.setCurrentPosition(new Point2D(this.currentPosition.getX() + deltaChange, this.currentPosition.getY()));
        this.setPointerOnStack(new Point2D(this.pointerOnStack.getX() + deltaChange, this.pointerOnStack.getY()));
        for (int i = 0; i < gains.size(); i++) {
            gains.get(i).setCurrentPosition(new Point2D(gains.get(i).getCurrentPosition().getX() + deltaChange,
                    gains.get(i).getCurrentPosition().getY()), true);
            
        }
        
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { 
        s.defaultReadObject();
        double xCurrentPosition = s.readDouble();
        double yCurrentPosition = s.readDouble();
        this.currentPosition = new Point2D(xCurrentPosition, yCurrentPosition);
        double xPointerOnStack = s.readDouble();
        double yPointerOnStack = s.readDouble();
        this.pointerOnStack = new Point2D(xPointerOnStack, yPointerOnStack);
        
    }
    
    @Override
    public Projectile removeGain() {
        Projectile proj = this.gains.get(this.gains.size() - 1);
        this.gains.remove(this.gains.size() - 1);
        setPointerOnStack(proj, false);
        
        if (gains.isEmpty()) {
            
            this.stackWidth = 40.0;
        } else {
            this.stackWidth = this.gains.peek().getDimension().getWidth();
            
        }
        return proj;
    }
    
    @Override
    public void removeGain(Projectile projectile) {
        this.gains.remove(projectile);
        setPointerOnStack(projectile, false);
        this.stackWidth = this.gains.peek().getDimension().getWidth();
        if (gains.isEmpty()) {
            
            this.stackWidth = 40.0;
        }
    }
    
    private void setCurrentPosition(Point2D point) {
        this.currentPosition = point;
        
    }
    
    private void setPointerOnStack(Point2D point) {
        this.pointerOnStack = point;
    }
    
    private void setPointerOnStack(Projectile projectile, boolean flag) {
        if (gains.isEmpty()) {
            this.pointerOnStack = this.currentPosition;
        } else if (flag) {
            Double x = this.currentPosition.getX();
            Double y = this.pointerOnStack.getY() - projectile.getDimension().getHeight();
            this.pointerOnStack = new Point2D(x, y);
        } else {
            Double x = this.currentPosition.getX();
            Double y = this.pointerOnStack.getY() + projectile.getDimension().getHeight();
            this.pointerOnStack = new Point2D(x, y);
        }
    }
    
    private void writeObject(ObjectOutputStream s) throws IOException { // serialize
        s.defaultWriteObject();
        
        s.writeDouble(this.currentPosition.getX());
        s.writeDouble(this.currentPosition.getY());
        s.writeDouble(this.pointerOnStack.getX());
        s.writeDouble(this.pointerOnStack.getY());
        
    }
    
}
