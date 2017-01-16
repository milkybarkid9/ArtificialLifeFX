/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;


/**
 * This is the static main class of the function that stores the static instance of AWorld and the instance of UI.
 * It also has the animation timers to update the world and move the entities as well as triggers the chain of 
 * functions that returns the completed UI. Also stores all the global instances of DEFAULT_PADDING, DEFAULT_INSET 
 * and the boolean "running".
 * @author James
 */
public class ArtificialLifeFX extends Application {
    public static final Integer DEFAULT_PADDING = 5;
    
    public static final Insets DEFAULT_INSET = new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING);
    
    public static boolean running = false;
    
    public static AnimationTimer moveTimer;
    public static AnimationTimer worldTimer;
    public static AWorld world = new AWorld();
    
    UI ui = new UI();
    
    public static int xSize = 0;
    public static int ySize = 0;
    
    /**
     * Turns an input string into a generated world
     * @param input The input string from the user
     * @return The completed world with entities
     */
    public static AWorld fromText(String input){
        if ("d".equals(input) || "default".equals(input)){ 
            input = "50 36 20 1 ant 4 bee 6"; //d creates a default world
        }
                
        String[] splitInput = input.split(" "); //split into array of string with spaces
        //strings to setup values
        xSize = Integer.parseInt(splitInput[0]); 
        ySize = Integer.parseInt(splitInput[1]); 
        float foodPercent = Integer.parseInt(splitInput[2]); 
        float obststaclePercent = Integer.parseInt(splitInput[3]); 
        String species1 = splitInput[4];
        int species1quant = Integer.parseInt(splitInput[5]);
        String species2 = splitInput[6];
        int species2quant = Integer.parseInt(splitInput[7]);
        
        AWorld world = new AWorld(xSize, ySize); //create new instance of the world
        
        //calculate food and obstacle amounts from percentages
        double food = xSize*ySize*(foodPercent/100);
        double obstacles = xSize*ySize*(obststaclePercent/100);
        
        for(int i = 0; i < food; i++){ //add food to world
            world.addEntity("food", 'f', 7);
        }
        for (int i = 0; i < obstacles; i++) { //add obstacles to world
            world.addEntity("obstacle", 'O', 0);
        }
        for (int i = 0; i < species1quant; i++) { //add entities
            char symbolChar[] = species1.toCharArray();          
            world.addEntity(species1, symbolChar[0], 30);
        }
        for (int i = 0; i < species2quant; i++) { //add entities
            char symbolChar[] = species2.toCharArray();
            world.addEntity(species2, symbolChar[0], 20);
        }
        
        return world; 
    }
    /**
     * Turns an input string into a generated world. Input string is broken into integers and doubles already 
     * @param xSize xSize of world
     * @param ySize ySize of world
     * @param foodPercent Percentage of the world that should be food
     * @param obststaclePercent Percentage of the world that should be obstacles
     * @param ants Number of ants
     * @param bees Number of bees
     * @return The completed world with entities
     */
    public static AWorld fromText(int xSize, int ySize, double foodPercent, double obststaclePercent, int ants, int bees){
        AWorld world = new AWorld(xSize, ySize); //create new instance of the world
        
        double food = xSize*ySize*(foodPercent/100);
        double obstacles = xSize*ySize*(obststaclePercent/100);
        
        for(int i = 0; i < food; i++){ //add food to world
            world.addEntity("food", 'f', 7);
        }
        for (int i = 0; i < obstacles; i++) { //add obstacles to world
            world.addEntity("obstacle", 'O', 0);
        }
        for (int i = 0; i < ants; i++) { //add entities         
            world.addEntity("ant", 'a', 30);
        }
        for (int i = 0; i < bees; i++) { //add entities           
            world.addEntity("bee", 'b', 20);
        }

        return world;
    }
    
   
    /**
     * Starts the UI and handles updating it as well as calling functions to move entities
     * @param primaryStage UI primary stage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {    
        moveTimer = new AnimationTimer(){
            long lastUpdate = 0; //move timer
            public void handle(long now){
                if (now - lastUpdate >= 500000000) { //0.5 seconds
                    world.checkDead(); //check dead entities
                    world.move(); //move alive entities
                    
                    lastUpdate = now; //reset move timer
                }
            }
        };       
        
        worldTimer = new AnimationTimer(){
            public void handle(long now){ //run every frame
                ui.updateWorld(); //update the world
            }
        };                     
        worldTimer.start();
        
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Artificial Life Simulator");
        primaryStage.setScene(ui.setup());
        primaryStage.show();
    }

    /**
     * Launches program
     * @param args 
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}
