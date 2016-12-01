/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author James
 */
public class AWorld {
    protected double xSize, ySize;
    protected int entityStack, maxEnts, worldTick;
    protected ArrayList<AnEntity> entities = new ArrayList<AnEntity>();
    Random rng = new Random();
    
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    AWorld(){
        xSize = 15;
        ySize = 15;
        entityStack = 0;
        maxEnts = (int) (xSize*ySize)/4;
        worldTick = 0;
    }
    AWorld(double ixSize, double iySize){
        xSize = ixSize;
        ySize = iySize;
        entityStack = 0;
        maxEnts = (int) (xSize*ySize)/4;
        worldTick = 0;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public double getxSize(){
        return xSize;
    }
    public double getySize(){
        return ySize;
    }
    public int getEntityStack(){
        return entityStack;
    }
    
    public void setySize(int iySize){
        ySize = iySize;
    }
    public void setxSize(int ixSize){
        xSize = ixSize;
    }
    // </editor-fold>
    
    public String entityStats(){ 
        String output = "";
        int foodCount = 0;
        for (int i = 0; i < entityStack; i++) { //for all entities
            if("food".equals(entities.get(i).getSpecies())){ //if entity is food, increment food count
                foodCount++;
            }else if(!"obstacle".equals(entities.get(i).getSpecies())){ //if not an obstacle
                output += "ID: " + entities.get(i).getID() + ", Species: " + entities.get(i).getSpecies() + ", Energy: " + entities.get(i).getEnergy() + "\n"; //print stats
            }           
        }
        output += "\nFood left = "+foodCount; //output amount of food
        return output;
    }
    
    public boolean moveCheck(int i, Direction d, String towards){
        boolean foodFound = false; //food not yet found
        switch(d){
            case north: //north chosen
                if(canMove(entities.get(i).getxPos(),entities.get(i).getyPos()-1) == 0){ //if space to move into is free
                    entities.get(i).setyPos(entities.get(i).getyPos()-1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards); 
                    foodFound = true; //food found
                }else if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 2){ //if space to move into is food
                    i--; //go back a space in entity list
                    entities.get(i).setEnergy(entities.get(i).getEnergy()+5); //add energy to entity
                    entities.get(i).setyPos(entities.get(i).getyPos()-1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }
                break;
            case south: //south chosen
                if(canMove(entities.get(i).getxPos(),entities.get(i).getyPos()+1) == 0){ //if space to move into is free
                    entities.get(i).setyPos(entities.get(i).getyPos()+1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }else if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 2){ //if space to move into is food
                    i--; //go back a space in entity list
                    entities.get(i).setEnergy(entities.get(i).getEnergy()+5); //add energy to entity
                    entities.get(i).setyPos(entities.get(i).getyPos()+1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }
                break;
            case east: //east chosen
                if(canMove(entities.get(i).getxPos()+1,entities.get(i).getyPos()) == 0){ //if space to move into is free
                    entities.get(i).setxPos(entities.get(i).getxPos()+1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }else if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 2){ //if space to move into is food
                    i--; //go back a space in entity list
                    entities.get(i).setEnergy(entities.get(i).getEnergy()+5); //add energy to entity
                    entities.get(i).setxPos(entities.get(i).getxPos()+1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }                            
                break;
            case west: //west chosen
                if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 0){ //if space to move into is free
                    entities.get(i).setxPos(entities.get(i).getxPos()-1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }else if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 2){ //if space to move into is food
                    i--; //go back a space in entity list
                    entities.get(i).setEnergy(entities.get(i).getEnergy()+5); //add energy to entity
                    entities.get(i).setxPos(entities.get(i).getxPos()-1); //move entity
                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+d+" "+towards);
                    foodFound = true; //food found
                }
                break;
        }
        return foodFound;
    }
    
    public void move(){
        worldTick++;
        System.out.println("\nWorld tick: "+worldTick+"\n---------------");
        boolean foodFound;
        List<Direction> rngDire = new ArrayList<>();
        Collections.addAll(rngDire, Direction.values());
        for (int i = 0; i < entityStack; i++) {
            foodFound = false;
            if("obstacle".equals(entities.get(i).getSpecies()) || "food".equals(entities.get(i).getSpecies())){
                
            }else{
                Collections.shuffle(rngDire);
                for (int j = 0; j < 4; j++) {
                    if(entities.get(i).smellFood(rngDire.get(j), 10)){
                        foodFound = moveCheck(i, rngDire.get(j), "towards food");
                    }
                }
                if(foodFound == false){
                    directionLoop:
                    for (int j = 0; j < 4; j++) {
                        switch(rngDire.get(j)){
                            case north:
                                if(canMove(entities.get(i).getxPos(),entities.get(i).getyPos()-1) == 0){ //if space to move into is free
                                    entities.get(i).setyPos(entities.get(i).getyPos()-1); //move entity
                                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+rngDire.get(j));
                                    break directionLoop;
                                }
                                break;
                            case south:
                                if(canMove(entities.get(i).getxPos(),entities.get(i).getyPos()+1) == 0){ //if space to move into is free
                                    entities.get(i).setyPos(entities.get(i).getyPos()+1); //move entity
                                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+rngDire.get(j));
                                    break directionLoop;
                                }
                                break;
                            case east:
                                if(canMove(entities.get(i).getxPos()+1,entities.get(i).getyPos()) == 0){ //if space to move into is free
                                    entities.get(i).setxPos(entities.get(i).getxPos()+1); //move entity
                                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+rngDire.get(j));
                                    break directionLoop;
                                }
                                break;
                            case west:
                                if(canMove(entities.get(i).getxPos()-1,entities.get(i).getyPos()) == 0){ //if space to move into is free
                                    entities.get(i).setxPos(entities.get(i).getxPos()-1); //move entity
                                    System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+rngDire.get(j));
                                    break directionLoop;
                                }
                                break;
                        }
                    }
                }
            }
        }
    }
    
    public boolean isFood(double x, double y){ //check if food at co-ords
        for (int i = 0; i < entityStack; i++) { //for all entities
            if (entities.get(i).getxPos() == x && entities.get(i).getyPos() == y && "food".equals(entities.get(i).getSpecies())){ //if there is an entity at x,y and has the name food
                return true; 
            }
        }
        return false;
    }
    
    public int canMove(double x, double y){
        if(x < 0 || x >= xSize || y < 0 || y >= ySize){ //if co-ords are out of range of symmap
            System.out.println("Movement out of bounds");
            return 1; //false
        }else{
            for (int i = 0; i < entityStack; i++) { //for all entities              
                if (entities.get(i).getxPos() == x && entities.get(i).getyPos() == y){ //if there is an entity at x,y
                    if("food".equals(entities.get(i).getSpecies())){ //if name of entity is food
                        removeEntity(i);
                        System.out.println("Food removed at "+x+","+y); //state food removed
                        return 2; //true, on food
                    }else{ //else must be obstacle or creature
                        return 1; //false
                    }
                } 
            }
            return 0; //true, not on food
        }
    }
    
    public void removeEntity(int i){
        entities.remove(i); //remove entity from vector
        entityStack--; //one less entity in array        
    }
    
    public boolean nearTo(double xpos, double ypos, double entityxPos, double entityyPos){
        if(abs(xpos - entityxPos) < 100 && abs(ypos - entityyPos) < 100){
            return true;
        }else{
            return false;
        }  
    }
    
    public double[] getPos() {
        while(true){
            double[] positions = new double[2];
            positions[0] = rng.nextInt((int) xSize); //gen random x co-ord
            positions[1] = rng.nextInt((int) ySize); //gen random y co-ord
            if(entityStack == 0){
                return positions; //if first entity then positions are fine
            }else{
                for (int i = 0; i < entityStack; i++) { //for all entities
                    if (nearTo( positions[0], positions[1], entities.get(i).getxPos(), entities.get(i).getyPos())) { //if there isn't an entity at those co-ords
                        return positions;
                    }
                }
            }
        } 
    }
    
    public void addEntity(){
        if(entityStack < maxEnts){ //if less than max number of entities
            System.out.println("Entity " + (entityStack+1)); //printing entity no
            entities.add(new ALifeform()); //initialise entity
            entityStack++; //increment for next entity
            
            entities.get(entityStack).setSpecies(JOptionPane.showInputDialog(null, "Input the species")); //input species using JOptionPane
            String symbol = JOptionPane.showInputDialog(null, "Input the symbol"); //store string using JOptionPane
            char symbolChar[] = symbol.toCharArray(); //convert to char array
            entities.get(entityStack).setSymbol(symbolChar[0]); //take first char as symbol
            double[] positions = new double[2];
            positions = getPos();
            entities.get(entityStack).setxPos(positions[0]); //fetch xpos from generated list
            entities.get(entityStack).setyPos(positions[1]); //fetch ypos from generated list
            String energy = JOptionPane.showInputDialog(null, "Input the energy"); //store string using JOptionPane
            entities.get(entityStack).setEnergy(Integer.parseInt(energy)); //cast string as int and input to class
            entities.get(entityStack).setID(entityStack); //id set as entity no.
            System.out.println(entities.get(entityStack).entToText()); //print to console
        }else{
            System.out.println("Entity limit reached at " + maxEnts + " entities"); //give warning of entity limit
        }
    }
    
    public void addEntity(String species, char symbol, int energy){
        if(entityStack < maxEnts){ //if less than max number of entities
            double[] positions = new double[2];
            positions = getPos(); //String speciesIn, char symbolIn, int xPosIn, int yPosIn, int energyIn, int IDIn, AWorld iWorld
            entities.add(new ALifeform(species, symbol, positions[0], positions[1], energy, entityStack, this));           
            System.out.println(entities.get(entityStack).entToText()); //print to console
            entityStack++; //increment for next entity
        }else{
            System.out.println("Entity limit reached at " + maxEnts + " entities"); //give warning of entity limit
        }
    }
    
    public void listEntities(){ //loop to print all entities
        for (int i = 0; i < entityStack; i++) {
            System.out.println(entities.get(i).entToText());
        }
    }    
    
    public void editEntity(int ID, String editVal, String newVal){
        for (int i = 0; i < entityStack; i++) {
            if (entities.get(i).getID() == ID) {
                switch(editVal){
                    default:
                        break;

                    case "Species":
                    case "species":
                        entities.get(i).setSpecies(newVal);
                        break;

                    case "Symbol":
                    case "symbol":
                        char symbolChar[] = newVal.toCharArray();
                        entities.get(i).setSymbol(symbolChar[0]);
                        break;

                    case "Xpos":
                    case "xpos":
                        if(canMove(Integer.parseInt(newVal), entities.get(i).getyPos()) == 0){
                            entities.get(i).setxPos(Integer.parseInt(newVal));
                        }                        
                        break;

                    case "Ypos":
                    case "ypos":
                        if(canMove(entities.get(i).getxPos(), Integer.parseInt(newVal)) == 0){
                            entities.get(i).setyPos(Integer.parseInt(newVal));
                        } 
                        break;        
                }
            }
        }        
    }
    
    public enum Direction {
        north,
        south,
        east,
        west;

        public static Direction getRandomDirection() { //return random enum
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}
