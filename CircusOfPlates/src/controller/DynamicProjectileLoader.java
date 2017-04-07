package controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import logs.Log4j;
import model.entities.EntityGeneratorVisitor;
import model.entities.IEntityGeneratorVisitor;
import model.entities.Projectile;

public class DynamicProjectileLoader {
    
    private static Map<String, Class<?>> classes;

    
    static {
        classes = new HashMap<>();
       
    }
    
    public Projectile locateProjectile(String projectileIdentifier) {
        Class<?> cls;
        Projectile projectile;

        try {
            if (classes.containsKey(projectileIdentifier))
                return (Projectile) classes.get(projectileIdentifier).getConstructor(IEntityGeneratorVisitor.class)
                        .newInstance(new EntityGeneratorVisitor());
            cls = Class.forName(Messages.getString("DynamicProjectileLoader.0") + projectileIdentifier); //$NON-NLS-1$
            projectile = (Projectile) cls.getConstructor(IEntityGeneratorVisitor.class)
                    .newInstance(new EntityGeneratorVisitor());
            classes.put(projectileIdentifier, projectile.getClass());
        } catch (Exception e) {
            
            return null;
        }
        
        return projectile;
    }
}
