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
    AWorld(){
        xSize = 15;
        ySize = 15;
        maxEnts = (int) (xSize*ySize)/4;
        worldTick = 0;
        showID = false;
    }
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
    
//    public void eat(int i, AnEntity eater, Direction d){
//        
//        int xpos = entities.get(i).getxPos();
//        int ypos = entities.get(i).getyPos();
//        
//        for (int j = 0; j < entityStack; i++) {            
//            if (isEntityAt(xpos,ypos) && ("food".equals(entities.get(j).getSpecies()) || entities.get(j).getAlive() == false)){
//                eater.setxPos(xpos);
//                eater.setyPos(ypos);
//                removeEntity(i);                
//            }
//        }        
//    }
    
    public void checkDead(){
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getEnergy() <= 0){
                entities.get(i).setAlive(false);
                entities.get(i).setEnergy(7);
            }            
        }
    }
    
    public String entityStats(){ 
        String output = "";
        int foodCount = 0;
        for (int i = 0; i < entities.size(); i++) { //for all entities
            if("food".equals(entities.get(i).getSpecies()) || (entities.get(i).getAlive() == false && !"obstacle".equals(entities.get(i).getSpecies()))){ //if entity is food, increment food count
                foodCount++;
            }else if(!"obstacle".equals(entities.get(i).getSpecies())){ //if not an obstacle
                output += "ID: " + entities.get(i).getID() + ", Species: " + entities.get(i).getSpecies() + ", Energy: " + entities.get(i).getEnergy() + ", Alive: " + entities.get(i).getAlive() + "\n"; //print stats
            }           
        }
        output += "\nFood left = "+foodCount; //output amount of food
        return output;
    }
    
//    public boolean moveCheck(int i, Direction d, String towards){
//        int xpos = entities.get(i).getxPos();
//        int ypos = entities.get(i).getyPos();
//        int ID = entities.get(i).getID();
//        int energy = entities.get(i).getEnergy();
//        String species = entities.get(i).getSpecies();
//        
//        boolean foodFound = false; //food not yet found
//        switch(d){
//            case north: //north chosen
//                if(canMove(xpos,ypos-1) == 0){ //if space to move into is free
//                    moveNorth(i,xpos,ypos);
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards); 
//                    foodFound = true; //food found
//                }else if(canMove(xpos-1,ypos) == 2){ //if space to move into is food
//                    i--; //go back a space in entity list
//                    entities.get(i).setEnergy(energy+5); //add energy to entity
//                    moveNorth(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }
//                break;
//            case south: //south chosen
//                if(canMove(xpos,ypos+1) == 0){ //if space to move into is free
//                    moveSouth(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }else if(canMove(xpos-1,ypos) == 2){ //if space to move into is food
//                    i--; //go back a space in entity list
//                    entities.get(i).setEnergy(energy+5); //add energy to entity
//                    moveSouth(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }
//                break;
//            case east: //east chosen
//                if(canMove(xpos+1,ypos) == 0){ //if space to move into is free
//                    moveEast(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }else if(canMove(xpos-1,ypos) == 2){ //if space to move into is food
//                    i--; //go back a space in entity list
//                    entities.get(i).setEnergy(energy+5); //add energy to entity
//                    moveEast(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }                            
//                break;
//            case west: //west chosen
//                if(canMove(xpos-1,ypos) == 0){ //if space to move into is free
//                    moveWest(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }else if(canMove(xpos-1,ypos) == 2){ //if space to move into is food
//                    i--; //go back a space in entity list
//                    entities.get(i).setEnergy(energy+5); //add energy to entity
//                    moveWest(i,xpos,ypos); //move entity
//                    System.out.println(species+" "+ID+" moved "+d+" "+towards);
//                    foodFound = true; //food found
//                }
//                break;
//        }
//        return foodFound;
//    }
    
//    public void move(){
//        worldTick++; //increment world tick
//        System.out.println("\nWorld tick: "+worldTick+"\n---------------"); //print tick no
//        boolean foodFound; 
//        List<Direction> rngDire = new ArrayList<>(); //arraylist for all four directions
//        Collections.addAll(rngDire, Direction.values()); //add all directions
//        
//        for (int i = 0; i < entityStack; i++) { //for all entities
//            foodFound = false; 
//            
//            int xpos = entities.get(i).getxPos();
//            int ypos = entities.get(i).getyPos();
//            int ID = entities.get(i).getID();
//            String species = entities.get(i).getSpecies();
//                         
//            if(entities.get(i).getCanMove()){ //if entity can move
//                
//                entities.get(i).decEnergy(); //take away one energy
//                Collections.shuffle(rngDire); //shuffle directions
//                int j = 0;
//                boolean moved = false;
//                
//                directionLoop:                
//                while(j < 4 || moved == false) { //for each direction in turn
//                    int distanceToFood = entities.get(i).smellFood(rngDire.get(j), 9); //get distance to food
//                    
//                    switch (distanceToFood) {
//                        case -1: //if no food
//                            foodFound = false; //food not found
//                            Direction direction = rngDire.get(j);
//                            switch(direction){
//                                case north:
//                                    //moved = moveNorth(i, xpos, ypos);
//                                    if(moveNorth(i, xpos, ypos)){
//                                        break directionLoop;
//                                    }
//                                    break;
//                                case south:
//                                    //moved = moveSouth(i, xpos, ypos);
//                                    if(moveSouth(i, xpos, ypos)){
//                                        break directionLoop;
//                                    }
//                                    break;
//                                case east:
//                                    //moved = moveEast(i, xpos, ypos);
//                                    if(moveEast(i, xpos, ypos)){
//                                        break directionLoop;
//                                    }
//                                    break;
//                                case west:
//                                    //moved = moveWest(i, xpos, ypos);
//                                    if(moveWest(i, xpos, ypos)){
//                                        break directionLoop;
//                                    }
//                                    break;
//                            }   
//                            System.out.println(species+" "+ID+" moved "+direction);
//                            break;
//                        case 1:
//                            //on top of food
//                            eat(entities.get(i), xpos, ypos);
//                            //foodFound = moveCheck(i, rngDire.get(j), "towards food");
//                            foodFound = true;
//                            break;
//                        default:
//                            Direction foodDirection = entities.get(i).getFoodDirection();
//                            switch(foodDirection){
//                                case north:
//                                    eat(entities.get(i), xpos, ypos);
//                                    moved = moveNorth(i, xpos, ypos);
//                                    break;
//                                case south:
//                                    eat(entities.get(i), xpos, ypos);
//                                    moved = moveSouth(i, xpos, ypos);
//                                    break;
//                                case east:
//                                    eat(entities.get(i), xpos, ypos);
//                                    moved = moveEast(i, xpos, ypos);
//                                    break;
//                                case west:
//                                    eat(entities.get(i), xpos, ypos);
//                                    moved = moveWest(i, xpos, ypos);
//                                    break;
//                            }
//                            foodFound = true;
//                            break;
//                    }
//                    j++;
//                }                               
//            }
//        }
//    }
    
    
    
    
    
    public void move(){
        
        List<Direction> rngDire = new ArrayList<>(); //arraylist for all four directions
        Collections.addAll(rngDire, Direction.values()); //add all directions
        
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getCanMove() && entities.get(i).getAlive()){
                
                Collections.shuffle(rngDire);//shuffle directions
                int newDistance = 99;
                int oldDistance = 99;
                int finalDistance = 99;
                Direction newDirection = rngDire.get(0);
                Direction finalDirection = rngDire.get(0);
                
                directionLoop:
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
                        System.out.println(entities.get(i).getSpecies()+" "+i+" moved "+finalDirection+" (no food)");
                        break;

                    case 1: //next to food 
                        moveDirection(i, finalDirection);
                        eat(i, finalDirection);                        
                        System.out.println(entities.get(i).getSpecies()+" "+i+" moved "+finalDirection+" (next to food)");
                        break;

                    default:
                        moveDirection(i, entities.get(i).getFoodDirection());
                        System.out.println(entities.get(i).getSpecies()+" "+i+" moved "+finalDirection+" (towards food)");
                        break;
                }
                
                entities.get(i).decEnergy(); //decrement energy
                                
            }            
        }               
    }
    
    
    
    
    
    
    
    
    
    public void eat(int i, Direction d){
        int xpos = entities.get(i).getxPos();
        int ypos = entities.get(i).getyPos();
        int foodIterator = -1;
        
        switch(d){
            case north:
                foodIterator = getEntityAt(xpos, ypos-1);
                break;
            case south:
                foodIterator = getEntityAt(xpos, ypos+1);
                break;
            case east:
                foodIterator = getEntityAt(xpos+1, ypos);
                break;
            case west:
                foodIterator = getEntityAt(xpos-1, ypos);
                break;
        }
        
        removeEntity(foodIterator);
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

                    case "xpos":
                        if(canMove(Integer.parseInt(newVal), entities.get(i).getyPos()) == 0){
                            entities.get(i).setxPos(Integer.parseInt(newVal));
                        }                        
                        break;

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
