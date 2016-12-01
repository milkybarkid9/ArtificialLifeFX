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
public class AFood extends AnEntity{
    AFood(){
        super();
    }
    
    AFood(String speciesIn, char symbolIn, double xPosIn, double yPosIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
    }
    
    public int getEnergy() {
        return 5;
    }

    public void setEnergy(int iEnergy) {
        
    }

    public boolean smellFood(AWorld.Direction D, int range) {
        return false;
    }
}
