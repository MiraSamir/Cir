package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import controller.IFileHandler;

public class FileHandling implements IFileHandler {
    
    private String path;
    
    public FileHandling() {
        
        this.path = this.getClass().getClassLoader().getResource("res/Savings/").getPath();
        
    }
    
    @Override
    public void save(SavingMemento memento) {
        
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            String currentTime = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME).replaceAll(":", "_");
            File file = new File(this.path + "/" + currentTime + ".ser");
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(memento);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public SavingMemento load() {
        SavingMemento memento = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        
        try {
            File[] files = new File(this.path).listFiles();
            fileInputStream = new FileInputStream(files[files.length - 1]);
            objectInputStream = new ObjectInputStream(fileInputStream);
            memento = (SavingMemento) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return memento;
        
    }
    
}