/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author James
 */
public class AWorld {
    protected int xSize, ySize;
    protected int maxEnts, worldTick;
    protected boolean showID;
    protected ArrayList<AnEntity> entities = new ArrayList<>();
    Random rng = new Random();
    
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor (never used)
     */
    AWorld(){
        xSize = 15;
        ySize = 15;
        maxEnts = (int) (xSize*ySize)/4;
        worldTick = 0;
        showID = false;
    }
    /**
     * Overloaded constructor
     * @param ixSize Desired xSize
     * @param iySize Desired ySize
     */
    AWorld(int ixSize, int iySize){
        xSize = ixSize;
        ySize = iySize;
        maxEnts = (int) (xSize*ySize)/4;
        worldTick = 0;
        showID = false;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getxSize(){
        return xSize;
    }
    public int getySize(){
        return ySize;
    }
    public boolean getShowID(){
        return showID;
    }
    
    public void setySize(int iySize){
        ySize = iySize;
    }
    public void setxSize(int ixSize){
        xSize = ixSize;
    }
    public void setShowID(boolean show){
        showID = show;
    }
    // </editor-fold>
    
    /**
     * Checks all entities to see if they are dead. If so, set their state to dead
     */    
    public void checkDead(){
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getEnergy() <= 0){
                entities.get(i).setAlive(false);
                entities.get(i).setEnergy(7);
            }            
        }
    }
    
    /**
     * Returns the statistics for the world. Live entities are listed in detail, food is just added in a counter
     * @return the completed output string for all entities
     */
    public String entityStats(){ 
        String output = "";
        int foodCount = 0;
        for (int i = 0; i < entities.size(); i++) { //for all entities
            //if food or (dead & not obstacle)
            if("food".equals(entities.get(i).getSpecies()) || (entities.get(i).getAlive() == false && !"obstacle".equals(entities.get(i).getSpecies()))){ //if entity is food, increment food count
                foodCount++; 
            }else if(!"obstacle".equals(entities.get(i).getSpecies())){ //if not an obstacle
                //output ID: X, Species: X, Energy: X, Alixe: X \n
                output += "ID: " + entities.get(i).getID() + ", Species: " + entities.get(i).getSpecies() + ", Energy: " + entities.get(i).getEnergy() + ", Alive: " + entities.get(i).getAlive() + "\n"; //print stats
            }           
        }
        output += "\nFood left = "+foodCount; //output amount of food
        return output;
    }
    
    /**
     * Moves all entities in the direction of the closest food entity or if no food within range, move a random direction.
     * Also handles eating food if within range. This is run every 0.5 seconds.
     */
    public void move(){        
        List<Direction> rngDire = new ArrayList<>(); //arraylist for all four directions
        Collections.addAll(rngDire, Direction.values()); //add all directions
        
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getCanMove() && entities.get(i).getAlive()){
                
                Collections.shuffle(rngDire);//shuffle directions
                int newDistance = 99;
                int oldDistance = 99;
                int finalDistance = 99;
                Direction newDirection = rngDire.get(0); //get first direction
                Direction finalDirection = rngDire.get(0); //get first direction
                String species = entities.get(i).getSpecies(); //get species
                int ID = entities.get(i).getID(); //get ID
                
                directionLoop: //find the smallest distance to food an its direction
                for (int j = 0; j < 4; j++) {                      
                    newDirection = rngDire.get(j);
                    newDistance = entities.get(i).smellFood(newDirection, 9);
                    
                    if(newDistance < oldDistance){
                        finalDistance = newDistance;
                        finalDirection = newDirection;                        
                    }
                    oldDistance = newDistance;
                }  
                
                switch(finalDistance){
                    case 99: //no food
                        moveDirection(i, finalDirection); 
                        System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+finalDirection+" (no food)");
                        entities.get(i).decEnergy(); //decrement energy
                        break;

                    case 1: //next to food 
                        int j;
                        if(i == 0){
                            j = 0;
                        }else{
                            j = i-1;
                        }
                        moveDirection(i, finalDirection);
                        eat(j, finalDirection);                        
                        System.out.println(entities.get(j).getSpecies()+" "+entities.get(j).getID()+" moved "+finalDirection+" (next to food)");
                        entities.get(j).decEnergy(); //decrement energy
                        break;

                    default:
                        moveDirection(i, entities.get(i).getFoodDirection());
                        System.out.println(entities.get(i).getSpecies()+" "+entities.get(i).getID()+" moved "+finalDirection+" (towards food)");
                        entities.get(i).decEnergy(); //decrement energy
                        break;
                }
                                
            }            
        }               
    }
    
    /**
     * Removes entity close to another entity
     * @param i index of entity doing the eating
     * @param d direction to eat
     */
    public void eat(int i, Direction d){
        int xpos = entities.get(i).getxPos();
        int ypos = entities.get(i).getyPos();
        int foodIndex = -1;
        
        switch(d){ //fetches index of entity to be eaten
            case north:
                foodIndex = getEntityAt(xpos, ypos-1);
                break;
            case south:
                foodIndex = getEntityAt(xpos, ypos+1);
                break;
            case east:
                foodIndex = getEntityAt(xpos+1, ypos);
                break;
            case west:
                foodIndex = getEntityAt(xpos-1, ypos);
                break;
        }        
        removeEntity(foodIndex); //removes chosen entity
    }
    
    public void moveDirection(int i, Direction d){        
        int xpos = entities.get(i).getxPos();
        int ypos = entities.get(i).getyPos();
        
        switch(d){
            case north:
                moveNorth(i, xpos, ypos);
                break;
            case south:
                moveSouth(i, xpos, ypos);
                break;
            case east:
                moveEast(i, xpos, ypos);
                break;
            case west:
                moveWest(i, xpos, ypos);
                break;
        }
    }
    
    public boolean moveNorth(int i, int xpos, int ypos){
        if(canMove(xpos, ypos-1) == 0 && borderCheck(xpos,ypos)){ //if space to move into is free
            entities.get(i).setyPos(ypos-1); //move entity    
            return true;
        }
        return false;
    }
    public boolean moveSouth(int i, int xpos, int ypos){
        if(canMove(xpos, ypos+1) == 0 && borderCheck(xpos,ypos)){ //if space to move into is free
            entities.get(i).setyPos(ypos+1); //move entity
            return true;
        }
        return false;
    }
    public boolean moveEast(int i, int xpos, int ypos){
        if(canMove(xpos+1, ypos) == 0 && borderCheck(xpos,ypos)){ //if space to move into is free
            entities.get(i).setxPos(xpos+1); //move entity
            return true;
        }
        return false;
    }
    public boolean moveWest(int i, int xpos, int ypos){
        if(canMove(xpos-1, ypos) == 0 && borderCheck(xpos,ypos)){ //if space to move into is free
            entities.get(i).setxPos(xpos-1); //move entity
            return true;
        }
        return false;
    }
    
    public boolean borderCheck(int xpos, int ypos){
        if (xpos < 0 || xpos > xSize){
            if (ypos < 0 || ypos > ySize){
                return false;
            }
        }
        return true;
    }
    
    
    public boolean isFood(int x, int y){ //check if food at co-ords
        for (int i = 0; i < entities.size(); i++) { //for all entities
            if (isEntityAt(x, y) && ("food".equals(entities.get(i).getSpecies()) || entities.get(i).getAlive() == false)){ //if there is an entity at x,y and has the name food or is dead
                return true; 
            }
        }
        return false;
    }
    
    public int canMove(int x, int y){
        if(x < 0 || x >= xSize || y < 0 || y >= ySize){ //if co-ords are out of range of symmap
            System.out.println("Movement out of bounds");
            return 1; //false
        }else{
            for (int i = 0; i < entities.size(); i++) { //for all entities              
                if (entities.get(i).getxPos() == x && entities.get(i).getyPos() == y){ //if there is an entity at x,y
                    if("food".equals(entities.get(i).getSpecies())){ //if name of entity is food
                        removeEntity(i);
                        entities.get(i).setEnergy(entities.get(i).getEnergy()+7);
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
    
    public boolean isEntityAt(int xpos, int ypos){
        for (int i = 0; i < entities.size(); i++){  
            if(xpos == entities.get(i).getxPos() && ypos == entities.get(i).getyPos()){
                return true;
            }
        }
        return false;
    }
   
    /** 
    *returns the entity stack position of an entity at set coordinates
    */
    public int getEntityAt(int xpos, int ypos){
        for (int i = 0; i < entities.size(); i++){
            if (isEntityAt(xpos, ypos)){
                return i;
            }
        }        
        return -1;
    }
    
    public void removeEntity(int i){
        if(i >= 0 && i < entities.size()){
            entities.remove(i); //remove entity from vector
            System.out.println(entities.get(i).getSpecies()+" removed");
        }
    }
    
    
    public int[] getStartingPos() {
        while(true){
            int[] positions = new int[2];
            positions[0] = rng.nextInt(xSize); //gen random x co-ord
            positions[1] = rng.nextInt(ySize); //gen random y co-ord
            if(entities.size() == 0){
                return positions; //if first entity then positions are fine
            }else{
                for (int i = 0; i < entities.size(); i++) { //for all entities
                    if (positions[0] != entities.get(i).getxPos() &&  positions[1] != entities.get(i).getyPos()) { //if there isn't an entity at those co-ords
                        return positions;
                    }
                }
            }
        } 
    }
    
    public void addEntity(String species, char symbol, int energy){
        if(entities.size() < maxEnts){ //if less than max number of entities
            int[] positions = new int[2];
            positions = getStartingPos(); //String speciesIn, char symbolIn, int xPosIn, int yPosIn, int energyIn, int IDIn, AWorld iWorld

            switch(species){
                case "bee":
                    entities.add(new ACarnivore(species, symbol, positions[0], positions[1], energy, entities.size(), this));
                    break;
                case "ant":
                    entities.add(new AHerbivore(species, symbol, positions[0], positions[1], energy, entities.size(), this));
                    break;
                case "food":
                    entities.add(new AFruit(species, symbol, positions[0], positions[1], entities.size(), this));
                    break;
                case "obstacle":
                    entities.add(new AnObstacle(species, symbol, positions[0], positions[1], entities.size(), this));
                    break;
                case "plant":
                    entities.add(new APlant(species, symbol, positions[0], positions[1], entities.size(), this));
                    break;
            }
                     
            System.out.println(entities.get(entities.size()-1).entToText()); //print to console
            
        }else{
            System.out.println("Entity limit reached at " + maxEnts + " entities"); //give warning of entity limit
        }
    }
    
    public void addEntity(String species, char symbol, int energy, int xpos, int ypos){
        if(entities.size() < maxEnts){ //if less than max number of entities
            switch(species){
                case "bee":
                    entities.add(new ACarnivore(species, symbol, xpos, ypos, energy, entities.size(), this));
                    break;
                case "ant":
                    entities.add(new AHerbivore(species, symbol, xpos, ypos, energy, entities.size(), this));
                    break;
                case "food":
                    entities.add(new AFruit(species, symbol, xpos, ypos, entities.size(), this));
                    break;
                case "obstacle":
                    entities.add(new AnObstacle(species, symbol, xpos, ypos, entities.size(), this));
                    break;
                case "plant":
                    entities.add(new APlant(species, symbol, xpos, ypos, entities.size(), this));
                    break;
            }
                     
            System.out.println(entities.get(entities.size()-1).entToText()); //print to console
            
        }else{
            System.out.println("Entity limit reached at " + maxEnts + " entities"); //give warning of entity limit
        }
    }
    
    public void listEntities(){ //loop to print all entities
        for (int i = 0; i < entities.size(); i++) {
            System.out.println(entities.get(i).entToText());
        }
    }    
    
    public void editEntity(int ID, String editVal, String newVal){
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getID() == ID) {
                switch(editVal.toLowerCase()){
                    default:
                        break;

                    case "species":
                        entities.get(i).setSpecies(newVal);
                        break;

                    case "symbol":
                        char symbolChar[] = newVal.toCharArray();
                        entities.get(i).setSymbol(symbolChar[0]);
                        break;

                    case "energy":
                        entities.get(i).setEnergy(Integer.parseInt(newVal));
                        break;
                        
                    case "xpos":
                        if(!isEntityAt(Integer.parseInt(newVal), entities.get(i).getyPos())){
                            entities.get(i).setxPos(Integer.parseInt(newVal));
                        }                        
                        break;

                    case "ypos":
                        if(!isEntityAt(entities.get(i).getxPos(), Integer.parseInt(newVal))){
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
