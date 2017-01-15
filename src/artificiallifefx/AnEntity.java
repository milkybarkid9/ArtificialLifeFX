/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;
/**
 * This class is the basis of all entities in the program. Sets up the basic variables and functions needed by all entities
 * @author James
 */



public abstract class AnEntity {
    protected String species;
    protected char symbol;
    protected int xPos, yPos, ID;
    protected AWorld world;
    protected boolean canMove;
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor (never used)
     */
    AnEntity(){
        species = "Default";
        symbol = 'D';
        xPos = 0;
        yPos = 0;
        ID = 0;
        world = new AWorld();
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
    AnEntity(String speciesIn, char symbolIn, int xPosIn, int yPosIn, int IDIn, AWorld iWorld){
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
    public int getxPos(){
        return xPos;
    }
    public int getyPos(){
        return yPos;
    }
    public int getID(){
        return ID;
    }
    public boolean getCanMove(){
        return canMove;
    }
    public abstract int getEnergy();
    
    public abstract void decEnergy();

    public void setSpecies(String iSpecies){
        species = iSpecies;
    }
    public void setSymbol(char iSymbol){
        symbol = iSymbol;
    }
    public void setxPos(int ixPos){
        xPos = ixPos;
    }
    public void setyPos(int iyPos){
        yPos = iyPos;
    }
    public void setID(int iID){
        ID = iID;
    }
    public void setCanMove(boolean newCanMove){
        canMove = newCanMove;
    }
    public abstract void setEnergy(int iEnergy);
    
    public abstract boolean getAlive();
    
    public abstract void setAlive(boolean iAlive);
    
    public abstract AWorld.Direction getFoodDirection();
    
    public abstract void setFoodDirection(AWorld.Direction newFoodDirection);
    //</editor-fold>
    
    public abstract int smellFood(AWorld.Direction D, int range);
    
    /**
     * Gets species and position
     * @return species and position as a string
     */
    public String entToString(){ //return the species and the position
        String speciesPos = "\nSpecies: " + species + "\nxPosition: " + xPos + "\nyPosition: " + yPos;
        return speciesPos;
    }
    
    /**
     * Gets entity statistics
     * @return species, symbol, position and id as a string
     */
    public String entToText(){ //return all the attributes in one string
        String attributes = "\nSpecies: " + species + "\nSymbol : " + symbol + "\nxPosition: " + xPos + "\nyPosition: " + yPos + "\nID: " + ID;
        return attributes;
    }
}
