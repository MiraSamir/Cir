package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;

import controller.MainController;
import logs.Log4j;
import model.entities.Projectile;

public class ProjectilesPool implements Container {
    
    private class ProjectileIterator implements Iterator<Projectile> {
        private int pointer;
        
        public ProjectileIterator() {
            this.pointer = 0;
        }
        
        @Override
        public boolean hasNext() {
            if (this.pointer < inUse.size())
                return true;
            return false;
        }
        
        @Override
        public Object next() {
            if (this.hasNext())
                return inUse.get(this.pointer++);
            return null;
        }
        
        @Override
        public int size() {
            return inUse.size();
        }
        
    }
    
    private final static int MAXSIZE = 500;
    private static ProjectilesPool instance;

    
    public static ProjectilesPool getInstance(MainController mainController) {
        if (instance == null)
            instance = new ProjectilesPool(mainController);

        return instance;
    }
    
    private List<Projectile> available;
    private List<Projectile> inUse;
    private Random random;
    private ProjectilesChooser projectilesChooser;
    
    private ProjectilesPool(MainController mainController) {
        this.available = new ArrayList<Projectile>();
        this.inUse = new ArrayList<Projectile>();
        this.random = new Random();
        this.projectilesChooser = new ProjectilesChooser(mainController);
    }
    
    public List<Projectile> getInUse() {
        return inUse;
    }
    
    @Override
    public Iterator<Projectile> getIterator() {
        return new ProjectileIterator();
    }
    
    public Projectile getObject() throws FileNotFoundException {
        if (this.available.size() + inUse.size() > MAXSIZE) {
            try {
                wait();
            
            } catch (InterruptedException e) {
               
            }
        } else if (!this.available.isEmpty()) {
            this.inUse.add(this.available.remove(this.random.nextInt(this.available.size())));
        } else {
            this.inUse.add(this.projectilesChooser.createProjectile());
            
        }
        return this.inUse.get(this.inUse.size() - 1);
    }
    
    public void releaseAll() {
        int size = this.inUse.size();
        for (int i = 0; i < size; i++)
            this.releaseObject(inUse.get(0));
    }
    
    public void releaseObject(Projectile projectile) {
        
        if (this.inUse.contains(projectile)) {
            this.available.add(this.inUse.remove(this.inUse.indexOf(projectile)));
        }
    }
    
    public void setInUse(List<Projectile> inUse) {
        this.inUse = inUse;
    }
}
