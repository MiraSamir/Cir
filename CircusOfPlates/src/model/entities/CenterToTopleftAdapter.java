package model.entities;

import javafx.geometry.Point2D;

public class CenterToTopleftAdapter implements ICenterToTopleftAdapter {
    private static final long serialVersionUID = 8L;
    private Entity entity;
    private Double width;
    private Double height;
    
    public CenterToTopleftAdapter(Entity entity) {
        this.entity = entity;
    }
    
    private Point2D getTopLeftPosition(Point2D positionCentered) {
        Point2D topLeft = new Point2D(positionCentered.getX() - (width / 2.0),
                positionCentered.getY() - (height / 2.0));
        return topLeft;
    }
    
    @Override
    public void setCurrentPosition(Point2D currentPositionCentered) {
        entity.currentPosition = this.getTopLeftPosition(currentPositionCentered);
        
    }
    
    @Override
    public void setEjaculationPoint(Projectile projectile, Point2D ejaculationPointCentered) {
        projectile.ejaculationPoint = this.getTopLeftPosition(ejaculationPointCentered);
        
    }
    
    @Override
    public void setHeight(Double charheight) {
        this.height = charheight;
        
    }
    
    @Override
    public void setInitialPosition(Point2D initialPositionCentered) {
        entity.intialPosition = this.getTopLeftPosition(initialPositionCentered);
        
    }
    
    @Override
    public void setWidth(Double charwidth) {
        this.width = charwidth;
        
    }
    
}
