/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Sets up the completed UI and calls all the functions to set up the subsections of the UI
 * @author James
 */
public class UI {
    BorderPane pane = new BorderPane();
    BorderPane menupane = new BorderPane();
    ScrollPane scrollPane = new ScrollPane();
    GridPane display = new GridPane();    
    
    ControlPanelTabs controlPanelTabs = new ControlPanelTabs();
    TopMenu topMenu = new TopMenu();
    SimControlBar controlBar = new SimControlBar();
    
    private final double SCENE_WIDTH = 1420;
    private final double SCENE_HEIGHT = 800;
    Scene scene = new Scene(pane, SCENE_WIDTH,SCENE_HEIGHT);
    
    //entity images
    Image antIMG = new Image("file:src\\images\\ant.PNG");
    Image beeIMG = new Image("file:src\\images\\bee.PNG");
    Image rockIMG = new Image("file:src\\images\\rock.PNG");
    Image fruit1IMG = new Image("file:src\\images\\fruit1.PNG");
    Image fruit2IMG = new Image("file:src\\images\\fruit2.PNG");
    Image fruit3IMG = new Image("file:src\\images\\fruit3.PNG");
    Image fruit4IMG = new Image("file:src\\images\\fruit4.PNG");
    Image fruit5IMG = new Image("file:src\\images\\fruit5.PNG");
    Image grassIMG = new Image("file:src\\images\\grass.PNG");
    Image meatIMG = new Image("file:src\\images\\meat.PNG");
    Image errorIMG = new Image("file:src\\images\\question-mark.PNG");
    
    /**
     * Sets up the scene and calls menu setups
     * @return completed scene
     */
    public Scene setup(){
        scrollPane.setContent(display);
        scrollPane.setPannable(true);
        
        pane.setTop(topMenu.setMenu());
        pane.setCenter(scrollPane);
        pane.setRight(controlPanelTabs.setup(this));
        pane.setBottom(controlBar.setup(this));
        
        display.setStyle("-fx-background-color: #B3FF99;");
        
        return scene;
    }
    
    /**
     * Updates the world by clearing the gridpane, then populating it with grass in every tile
     * then for each entity, put the correct image in the correct tile.
     */
    public void updateWorld(){  
        display.getChildren().clear(); //clear gridpane
        
        //for each tile in the grid
        for (int i = 0; i < ArtificialLifeFX.ySize; i++){
            for (int j = 0; j < ArtificialLifeFX.xSize; j++) {
                ImageView grassView = new ImageView(grassIMG);
                grassView.setFitHeight(30);
                grassView.setFitWidth(30);
                
                display.add(grassView, j, i); //add a 30x30 grass img
            }
        }

        for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //replaces space with correct symbol
            int tempy = ArtificialLifeFX.world.entities.get(i).getyPos();
            int tempx = ArtificialLifeFX.world.entities.get(i).getxPos();
            String species = ArtificialLifeFX.world.entities.get(i).getSpecies();
            
            //if dead and not food not obstacle
            if(ArtificialLifeFX.world.entities.get(i).getAlive() == false && !"food".equals(species) && !"obstacle".equals(species)){
                //set image as meat
                ImageView imageView = new ImageView(meatIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
                
            //if food
            } else if("food".equals(species)){  
                //set image as fruit
                ImageView imageView = new ImageView(fruit1IMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
                
            //if obstacle
            }else if("obstacle".equals(species)){
                //set image as rock
                ImageView imageView = new ImageView(rockIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
                
            //if ant
            }else if("ant".equals(species)){
                //set image as ant
                ImageView imageView = new ImageView(antIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
                
            //if bee
            }else if("bee".equals(species)){
                //set image as bee
                ImageView imageView = new ImageView(beeIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
                
            //if anything else
            }else{
                //set unknown placeholder img
                ImageView imageView = new ImageView(errorIMG);  
                imageView.setFitHeight(30);
                imageView.setFitWidth(18);
                display.add(imageView, tempx, tempy);
            }
            
            
        }
        
        labelEntities();
    }
    
    /**
     * Overlays the entity ID as a label over the tile it's current in
     */
    public void labelEntities(){
        for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //replaces space with correct symbol
            int tempy = ArtificialLifeFX.world.entities.get(i).getyPos();
            int tempx = ArtificialLifeFX.world.entities.get(i).getxPos();
                
            if(ArtificialLifeFX.world.getShowID() == true){
                Label ID = new Label();
                ID.setText(ArtificialLifeFX.world.entities.get(i).getID()+"");
                ID.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
                display.add(ID, tempx, tempy);
            }   
        }
    }
}
