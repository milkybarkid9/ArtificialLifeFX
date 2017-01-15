/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 * This class creates immovable objects that entities must avoid while searching for food
 * @author James
 */
public class AnObstacle extends AnEntity{
    
    /**
     * Default constructor 
     */
    AnObstacle(){
        super();
    }
    
    /**
     * Overloaded constructor
     * @param speciesIn species of entity
     * @param symbolIn symbol of entity
     * @param xPosIn xpos of entity
     * @param yPosIn ypos of entity
     * @param IDIn id of entity
     * @param iWorld world entity is in
     */
    AnObstacle(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
    }
    
    public int getEnergy() {
        return 0;
    }

    public void setEnergy(int iEnergy) {
        
    }

    public int smellFood(AWorld.Direction D, int range) {
        return -1;
    }

    @Override
    public void decEnergy() {
        
    }

    @Override
    public boolean getAlive() {
        return false;
    }

    @Override
    public void setAlive(boolean iAlive) {
        
    }

    @Override
    public AWorld.Direction getFoodDirection() {
        throw new UnsupportedOperationException("Not possible"); 
    }

    @Override
    public void setFoodDirection(AWorld.Direction newFoodDirection) {
        
    }
    
}
