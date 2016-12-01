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
public class ALifeform extends AnEntity {
    protected int energy;
    
    ALifeform(){
        super();
        energy = 0;
    }
    
    ALifeform(String speciesIn, char symbolIn, double xPosIn, double yPosIn, int energyIn, int IDIn, AWorld iWorld){
        super(speciesIn, symbolIn, xPosIn, yPosIn, IDIn, iWorld);
        energy = energyIn;
    } 
    
    public int getEnergy(){
        return energy;
    }
    public void setEnergy(int iEnergy){
        energy = iEnergy;
    }
    
    public boolean smellFood(AWorld.Direction D, int range){
        switch (D){
            case north:
                for (int i = 1; i <= range; i++) { //for a set smell range
                    if (world.isFood(xPos, yPos-i)){ //smell north in increments 
                        return true;
                    }
                }
                return false;
            case south:
                for (int i = 1; i <= range; i++) { //for a set smell range
                    if (world.isFood(xPos, yPos+i)){ //smell south in increments 
                        return true;
                    }
                }
                return false;
            case east:
                for (int i = 1; i <= range; i++) { //for a set smell range
                    if (world.isFood(xPos+i, yPos)){ //smell east in increments 
                        return true;
                    }
                }
                return false;
            case west:
                for (int i = 1; i <= range; i++) { //for a set smell range
                    if (world.isFood(xPos-i, yPos)){ //smell west in increments 
                        return true;
                    }
                }
                return false;
        }
        return false;
    }
    
    public String entToText(){ //return all the attributes in one string
        String attributes = "\nSpecies: " + species + "\nSymbol : " + symbol + "\nxPosition: " + xPos + "\nyPosition: " + yPos + "\nEnergy: " + energy + "\nID: " + ID;
        return attributes;
    }
}
