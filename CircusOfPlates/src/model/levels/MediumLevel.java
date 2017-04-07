package model.levels;

public class MediumLevel extends Level {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3591233775898866159L;
    
    public MediumLevel() {
        this.lowerBoundSpeed = 30;
        this.upperBoundSpeed = 80;
        this.newProjectileTime = 1.7;
        
    }
    
}
