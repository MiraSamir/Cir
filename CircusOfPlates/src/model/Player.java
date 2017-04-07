package model;

import java.io.FileNotFoundException;
import java.io.Serializable;

import org.apache.logging.log4j.Logger;

import logs.Log4j;
import model.entities.Character;
import model.entities.IEntityGeneratorVisitor;

public class Player implements IPlayer, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 17L;
    private Score playerScore;
    private Character playerCharacter;
    

    public Player() throws FileNotFoundException {
       
        this.playerScore = new Score();
        this.playerCharacter = new Clown("BlueClown");
    }
    
    public Player(IEntityGeneratorVisitor visitor) {
   
        this.playerScore = new Score();
        this.playerCharacter = new Clown(visitor);
    }
    
    @Override
    public Character getPlayerCharacter() {
        return playerCharacter;
    }
    
    @Override
    public Score getPlayerScore() {
        return playerScore;
    }
    
    @Override
    public void incrementScore() {
        this.playerScore.incrementScore();
        
    }
}
