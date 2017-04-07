package model;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.logging.log4j.Logger;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import logs.Log4j;

public class Score implements java.io.Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 99L;
  private int Score;
  private transient IntegerProperty scoreProperty;
  


  public Score() {
	
    this.Score = 0;
    this.scoreProperty = new SimpleIntegerProperty(Score);
  }

  public int getScore() {
    return Score;
  }

  public IntegerProperty getScoreProperty() {
    return scoreProperty;
  }

  public void incrementScore() {
	
    this.scoreProperty.set(++this.Score);
  }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException { // deserialize
    s.defaultReadObject();
    this.scoreProperty = new SimpleIntegerProperty(this.Score);
  }

}
