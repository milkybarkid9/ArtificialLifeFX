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



public abstract class AnEntity {
    protected String species;
    protected char symbol;
    protected double xPos, yPos;
    protected int ID;
    protected AWorld world;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    AnEntity(){
        species = "Default";
        symbol = 'D';
        xPos = 0;
        yPos = 0;
        ID = 0;
        world = new AWorld();
    }
    
    AnEntity(String speciesIn, char symbolIn, double xPosIn, double yPosIn, int IDIn, AWorld iWorld){
        species = speciesIn;
        symbol = symbolIn;
        xPos = xPosIn;
        yPos = yPosIn;
        ID = IDIn;
        world = iWorld;
    } 
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getSpecies(){
        return species;
    }
    public char getSymbol(){
        return symbol;
    }
    public double getxPos(){
        return xPos;
    }
    public double getyPos(){
        return yPos;
    }
    public int getID(){
        return ID;
    }
    public abstract int getEnergy();

    public void setSpecies(String iSpecies){
        species = iSpecies;
    }
    public void setSymbol(char iSymbol){
        symbol = iSymbol;
    }
    public void setxPos(double ixPos){
        xPos = ixPos;
    }
    public void setyPos(double iyPos){
        yPos = iyPos;
    }
    public void setID(int iID){
        ID = iID;
    }
    public abstract void setEnergy(int iEnergy);
    //</editor-fold>
    
    public abstract boolean smellFood(AWorld.Direction D, int range);
    
    public String entToString(){ //return the species and the position
        String speciesPos = "\nSpecies: " + species + "\nxPosition: " + xPos + "\nyPosition: " + yPos;
        return speciesPos;
    }
    public String entToText(){ //return all the attributes in one string
        String attributes = "\nSpecies: " + species + "\nSymbol : " + symbol + "\nxPosition: " + xPos + "\nyPosition: " + yPos + "\nEnergy: " + "\nID: " + ID;
        return attributes;
    }
}
