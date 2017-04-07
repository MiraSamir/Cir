package model.levels;

import org.apache.logging.log4j.Logger;

import logs.Log4j;

public abstract class Level implements ILevel, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 24L;
    protected double upperBoundSpeed;
    protected double lowerBoundSpeed;
    protected double newProjectileTime;
    private static Logger logger;
    
    static {
        logger = Log4j.getInstance();
    }
    
    public Level() {
        logger.info("Game level is set to " + this.getClass().getSimpleName());
    }
    
    @Override
    public double getLowerBoundSpeed() {
        
        return this.lowerBoundSpeed;
    }
    
    @Override
    public double getNewProjectileTime() {
        
        return this.newProjectileTime;
    }
    
    @Override
    public double getUpperBoundSpeed() {
        
        return this.upperBoundSpeed;
    }
    
}
