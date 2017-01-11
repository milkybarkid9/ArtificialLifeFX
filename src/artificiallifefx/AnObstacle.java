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
public class AnObstacle extends AnEntity{
    AnObstacle(){
        super();
    }
    
    AnObstacle(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
    }
    
    public int getEnergy() {
        return 0;
    }

    public void setEnergy(int iEnergy) {
        
    }

    public boolean smellFood(AWorld.Direction D, int range) {
        return false;
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
    
}
