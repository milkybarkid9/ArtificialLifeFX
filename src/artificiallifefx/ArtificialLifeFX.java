/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;



public class ArtificialLifeFX extends Application {
    public static Integer DEFAULT_PADDING = 5;
    
    public static boolean running = false;
    public static int spriteScale = 40;
    
    public static AnimationTimer timer;
    public static AWorld world = new AWorld();
    
    
    UI ui = new UI();
    
    
    public static AWorld fromText(String input){
        if ("d".equals(input) || "default".equals(input)){ 
            input = "50 36 20 1 ant 4 bee 6"; //d creates a default world
        }
        String[] splitInput = input.split(" "); //split into array of string with spaces
        //strings to setup values
        double xSize = Integer.parseInt(splitInput[0]); 
        double ySize = Integer.parseInt(splitInput[1]); 
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
            world.addEntity("food", 'f', 5);
        }
        for (int i = 0; i < obstacles; i++) { //add obstacles to world
            world.addEntity("obstacle", 'O', 0);
        }
        for (int i = 0; i < species1quant; i++) { //add entities
            char symbolChar[] = species1.toCharArray();          
            world.addEntity(species1, symbolChar[0], 5);
        }
        for (int i = 0; i < species2quant; i++) { //add entities
            char symbolChar[] = species2.toCharArray();
            world.addEntity(species2, symbolChar[0], 5);
        }
        return world; 
    }
    
    public static AWorld fromText(double xSize, double ySize, double foodPercent, double obststaclePercent, int ants, int bees){
        AWorld world = new AWorld(xSize, ySize); //create new instance of the world
        
        double food = xSize*ySize*(foodPercent/100);
        double obstacles = xSize*ySize*(obststaclePercent/100);
        
        for(int i = 0; i < food; i++){ //add food to world
            world.addEntity("food", 'f', 5);
        }
        for (int i = 0; i < obstacles; i++) { //add obstacles to world
            world.addEntity("obstacle", 'O', 0);
        }
        for (int i = 0; i < ants; i++) { //add entities         
            world.addEntity("Ant", 'a', 5);
        }
        for (int i = 0; i < bees; i++) { //add entities           
            world.addEntity("Bee", 'b', 5);
        }
        //ui.setWorldSetupLabel("oi");
        return world;
    }
    
   
    
    @Override
    public void start(Stage primaryStage) throws Exception {    
        
        timer = new AnimationTimer(){
            public void handle(long currentNanoTime)
            {
                ui.updateWorld();
            }
        };                          
                        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Artificial Life Simulator");
        primaryStage.setScene(ui.setup(this));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
