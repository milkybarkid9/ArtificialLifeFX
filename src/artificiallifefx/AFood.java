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
public abstract class AFood extends AnEntity{
    AFood(){
        super();
    }
    
    AFood(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
    }
    
    public int getEnergy() {
        return 7;
    }

    public void setEnergy(int iEnergy) {
        
    }

    public boolean smellFood(AWorld.Direction D, int range) {
        return false;
    }

    public void decEnergy() {
        
    }

    public boolean getAlive() {
        return false;
    }

    public void setAlive(boolean iAlive) {
        
    }
}
