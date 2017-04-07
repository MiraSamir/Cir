package model.entities;

import static view.Util.SCREEN_WIDTH;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.Logger;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import logs.Log4j;
import model.Shelf;
import model.states.FlyingState;
import model.states.OnShelfState;
import model.states.ProjectileState;

public abstract class Projectile extends Entity implements ProjectileMovement {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected static Shelf shelf;
    private static Logger logger;
    static {
        logger = Log4j.getInstance();
    }
    
    protected transient Point2D ejaculationPoint;
    
    protected ProjectileState projectileState;
    
    public Projectile(String projectileType, Double projectileWidth, Double projectileHeight,
            IEntityGeneratorVisitor visitor) {
        super("Throwables" + File.separator + projectileType, projectileWidth, projectileHeight, projectileType,
                visitor);
        // this.deltaTime = 0.017f;
        this.ejaculationPoint = new Point2D(0.0, 0.0);
        this.adapter.setWidth(projectileWidth);
        this.adapter.setHeight(projectileHeight);
        this.projectileState = new OnShelfState();
        this.dimension = new Dimension2D(projectileWidth, projectileHeight);
    }
    
    public Projectile(String projectileType, Double projectileWidth, Double projectileHeight, String fileName)
            throws FileNotFoundException {
        super("Throwables" + File.separator + projectileType, projectileWidth, projectileHeight, projectileType,
                fileName);
        // this.deltaTime = 0.017f;
        this.ejaculationPoint = new Point2D(0.0, 0.0);
        this.adapter.setWidth(projectileWidth);
        this.adapter.setHeight(projectileHeight);
        this.projectileState = new OnShelfState();
    }
    
    @Override
    public void ejaculateProjectile() {
        if (this.projectileState.getClass() == OnShelfState.class && (((this.intialPosition.getX() > SCREEN_WIDTH / 2)
                && (this.currentPosition.getX() <= (this.getEjaculationPoint().getX())))
                || ((this.intialPosition.getX() < SCREEN_WIDTH / 2)
                        && (this.currentPosition.getX() >= this.getEjaculationPoint().getX())))) {
            this.setProjectileState(new FlyingState());
            
        }
        this.getProjectileState().move(this.image, this.intialPosition.getX());
        this.setCurrentPosition(new Point2D(this.image.getX(), this.image.getY()), true);
    }
    
    public Point2D getEjaculationPoint() {
        return ejaculationPoint;
    }
    
    public ProjectileState getProjectileState() {
        return projectileState;
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { // deserialize
        s.defaultReadObject();
        double xEjaculationPoint = s.readDouble();
        double yEjaculationPoint = s.readDouble();
        this.ejaculationPoint = new Point2D(xEjaculationPoint, yEjaculationPoint);
    }
    
    private void setEjaculationPoint(Point2D ejaculationPoint) {
        logger.info("Setting " + this.getClass().getName() + " Ejaculation Point");
        this.adapter.setEjaculationPoint(this, ejaculationPoint);
    }
    
    public void setPositions(Shelf shelfPosition) {
        setIntialPosition(shelfPosition.getInitialPoint());
        setCurrentPosition(shelfPosition.getInitialPoint(), false);
        setEjaculationPoint(shelfPosition.getEjaculationPoint());
        
    }
    
    public void setProjectileState(ProjectileState projectileState) {
        this.projectileState = projectileState;
    }
    
    private void writeObject(ObjectOutputStream s) throws IOException { // serialize
        s.defaultWriteObject();
        s.writeDouble(this.ejaculationPoint.getX());
        s.writeDouble(this.ejaculationPoint.getY());
        
    }
    
}