/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

/**
 *
 * @author James
 */
public class APlant extends AFood{

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
