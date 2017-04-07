package model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import model.IHand;
import model.PlayerMovement;

public abstract class Character extends Entity implements PlayerMovement {
    
    private static final long serialVersionUID = 3L;
    protected List<IHand> hands;
    
    private IHand lastUpdatedHand;
    
    public Character(String characterType, Double characterWidth, Double characterHeight,
            IEntityGeneratorVisitor visitor) {
        super("Characters" + File.separator + characterType, characterWidth, characterHeight, characterType, visitor);
        this.adapter.setHeight(characterHeight);
        this.adapter.setWidth(characterWidth);
        this.hands = new ArrayList<IHand>();
        
    }
    
    public Character(String characterType, Double characterWidth, Double characterHeight, String fileName)
            throws FileNotFoundException {
        super("Characters" + File.separator + characterType, characterWidth, characterHeight, characterType, fileName);
        this.adapter.setHeight(characterHeight);
        this.adapter.setWidth(characterWidth);
        this.hands = new ArrayList<IHand>();
        
    }
    
    private int catchProjectile(Projectile projectile) {
        Point2D localPoint = new Point2D(0, 0);
        Double localWidth;
        
        for (int i = 0; i < this.hands.size(); i++) {
            localPoint = this.hands.get(i).getPointerOnStack();
            localWidth = this.hands.get(i).getStackWidth() / 2.0;
            
            if (Math.abs(localPoint.getY() - projectile.getCurrentPosition().getY()
                    - projectile.getDimension().getHeight()) < 5
                    && ((projectile.getCurrentPosition().getX()
                            + projectile.getDimension().getWidth() / 2.0 >= (localPoint.getX() - localWidth))
                            && (projectile.getCurrentPosition().getX() + projectile.getDimension().getWidth()
                                    / 2.0 <= (localPoint.getX() + localWidth)))) {
                return i;
            }
            
        }
        
        return -1;
    }
    
    public List<IHand> getHands() {
        return hands;
    }
    
    public IHand getLastUpdatedHand() {
        return lastUpdatedHand;
    }
    
    public boolean isCatchable(Projectile projectile) {
        int flag = catchProjectile(projectile);
        if (flag != -1) {
            this.hands.get(flag).addGain(projectile);
            this.lastUpdatedHand = this.hands.get(flag);
            return true;
            
        }
        return false;
    }
    
    protected abstract void moveRelativeHand(List<IHand> hands, Double deltaDistance);
    
    @Override
    public void moveTo(double x) {
        Double deltaChange = this.currentPosition.getX();
        this.setCurrentPosition(new Point2D(x, this.intialPosition.getY()), true);
        deltaChange = this.currentPosition.getX() - deltaChange;
        this.moveRelativeHand(this.hands, deltaChange);
    }
    
    @Override
    public void moveToTheLeft() {
        Double deltaChange = this.currentPosition.getX();
        this.setCurrentPosition(new Point2D(this.image.getX() - 10, this.image.getY()), true);
        deltaChange = this.currentPosition.getX() - deltaChange;
        this.moveRelativeHand(this.hands, deltaChange);
        
    }
    
    @Override
    public void moveToTheRight() {
        Double deltaChange = this.currentPosition.getX();
        this.setCurrentPosition(new Point2D(this.image.getX() + 10, this.image.getY()), true);
        deltaChange = this.currentPosition.getX() - deltaChange;
        this.moveRelativeHand(this.hands, deltaChange);
        
    }
    
    @Override
    public void setIntialPosition(Point2D intialPosition) {
        super.setIntialPosition(intialPosition);
        
    }
    
}
