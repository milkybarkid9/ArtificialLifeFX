/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 * Inherited from AFood, this class creates plants which will create food items
 * @author James
 */
public class APlant extends AFood{
    /**
     * Overloaded constructor 
     * @param speciesIn species of entity
     * @param symbolIn symbol of entity
     * @param xPosIn xpos of entity
     * @param yPosIn ypos of entity
     * @param IDIn id of entity
     * @param iWorld world entity is in
     */
    APlant(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld) {
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
        canMove = false;
    }

    @Override
    public AWorld.Direction getFoodDirection() {
        throw new UnsupportedOperationException("Not possible");
    }

    @Override
    public void setFoodDirection(AWorld.Direction newFoodDirection) {
        
    }
    
}
