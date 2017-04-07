package model.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.Logger;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import logs.Log4j;
import model.ColorFactory;
import model.Drawable;

public abstract class Entity implements IEntity, Drawable, java.io.Serializable {
    
    private static final long serialVersionUID = 7L;
    
    private static Logger logger;
    static {
        logger = Log4j.getInstance();
    }
    protected transient Dimension2D dimension;
    protected transient Point2D intialPosition;
    protected transient Point2D currentPosition;
    protected transient Color color;
    protected ICenterToTopleftAdapter adapter;
    
    protected transient ImageView image;
    
    protected String imagePath;
    
    public Entity(String pathTail, Double entityWidth, Double entityHeight, String projectileType,
            IEntityGeneratorVisitor visitor) {
        logger.info("Initializing " + this.getClass().getName());
        this.adapter = new CenterToTopleftAdapter(this);
        visitor.setRandomEntity(this, pathTail, entityWidth, entityHeight, projectileType);
    }
    
    // with default path
    public Entity(String pathTail, Double entityWidth, Double entityHeight, String projectileType, String fileName)
            throws FileNotFoundException {
        logger.info("Initializing " + this.getClass().getName());
        
        this.adapter = new CenterToTopleftAdapter(this);
        if (!Files.exists(Paths.get(pathTail + fileName))) {
            throw new FileNotFoundException();
            
        }
        File imgPath = new File(pathTail + fileName);
        this.imagePath = imgPath.toURI().toString();
        Image selectedImg = new Image(imgPath.toURI().toString(), entityWidth, entityHeight, false, true);
        this.image = new ImageView(selectedImg);
        this.image.setCache(true);
        this.setColor(imgPath.getName(), projectileType);
        this.dimension = new Dimension2D(entityWidth, entityHeight);
        this.intialPosition = new Point2D(0, 0);
        this.currentPosition = new Point2D(0, 0);
    }
    
    @Override
    public void draw(Pane root) {
        root.getChildren().add(this.image);
    }
    
    public Color getColor() {
        return color;
    }
    
    public Point2D getCurrentPosition() {
        return this.currentPosition;
    }
    
    public Dimension2D getDimension() {
        return this.dimension;
    }
    
    public ImageView getImage() {
        return image;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public Point2D getIntialPosition() {
        return this.intialPosition;
    }
    
    private String pathColor(String path, String projectileType) {
        String color = path.substring(0, path.indexOf(projectileType));
        return color;
    }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { // deserialize
        s.defaultReadObject();
        
        double xInitial = s.readDouble();
        double yInitial = s.readDouble();
        this.intialPosition = new Point2D(xInitial, yInitial);
        double xCurrent = s.readDouble();
        double yCurrent = s.readDouble();
        this.currentPosition = new Point2D(xCurrent, yCurrent);
        double red = s.readDouble();
        double green = s.readDouble();
        double blue = s.readDouble();
        double opacity = s.readDouble();
        this.color = Color.color(red, green, blue, opacity);
        double height = s.readDouble();
        double width = s.readDouble();
        this.dimension = new Dimension2D(width, height);
    }
    
    @Override
    public void remove(Pane root) {
        root.getChildren().remove(this.image);
    }
    
    protected void setColor(String path, String projectileType) {
        logger.info("Setting " + this.getClass().getName() + " Color.");
        this.color = ColorFactory.getColor(pathColor(path, projectileType));
        
    }
    
    public void setCurrentPosition(Point2D currentPosition, boolean flag) { // if true then it's
                                                                                // topleft
        if (flag) {
            this.currentPosition = currentPosition;
            this.image.setX(currentPosition.getX());
            this.image.setY(currentPosition.getY());
        } else {
            this.adapter.setCurrentPosition(currentPosition);
            this.image.setX(this.currentPosition.getX());
            this.image.setY(this.currentPosition.getY());
        }
        
    }
    
    public void setIntialPosition(Point2D intialPosition) {
        logger.info("Setting " + this.getClass().getName() + " Initial Position.");
        this.adapter.setInitialPosition(intialPosition);
        this.image.setX(this.intialPosition.getX());
        this.image.setY(this.intialPosition.getY());
        this.adapter.setCurrentPosition(intialPosition);
    }
    
    private void writeObject(ObjectOutputStream s) throws IOException { // serialize
        s.defaultWriteObject();
        s.writeDouble(this.intialPosition.getX());
        s.writeDouble(this.intialPosition.getY());
        s.writeDouble(this.currentPosition.getX());
        s.writeDouble(this.currentPosition.getY());
        s.writeDouble(this.color.getRed());
        s.writeDouble(this.color.getGreen());
        s.writeDouble(this.color.getBlue());
        s.writeDouble(this.color.getOpacity());
        s.writeDouble(this.dimension.getHeight());
        s.writeDouble(this.dimension.getWidth());
    }
    
}