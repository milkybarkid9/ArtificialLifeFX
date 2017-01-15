/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 * Inherited from AnEntity, this abstract class sets up the functions shared by AFruit and APlant
 * @author James
 */
public abstract class AFood extends AnEntity{
    /**
     * Default constructor
     */
    AFood(){
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
    AFood(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
    }
    
    public int getEnergy() {
        return 7;
    }

    public void setEnergy(int iEnergy) {
        
    }

    public int smellFood(AWorld.Direction D, int range) {
        return 99;
    }

    public void decEnergy() {
        
    }

    public boolean getAlive() {
        return false;
    }

    public void setAlive(boolean iAlive) {
        
    }
    
    public AWorld.Direction getFoodDirection() {
        throw new UnsupportedOperationException("Not possible"); 
    }

    public void setFoodDirection(AWorld.Direction newFoodDirection) {
        
    }
}
