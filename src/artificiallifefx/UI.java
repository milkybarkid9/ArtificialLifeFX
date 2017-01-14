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
 *
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
    
    public Scene setup(ArtificialLifeFX artificialLife){
        scrollPane.setContent(display);
        scrollPane.setPannable(true);
        
        pane.setTop(topMenu.setMenu());
        pane.setCenter(scrollPane);
        pane.setRight(controlPanelTabs.setup(this));
        pane.setBottom(controlBar.setup(artificialLife, this));
        
        display.setStyle("-fx-background-color: #B3FF99;");
        
        return scene;
    }
    
    public void updateWorld(){  
        display.getChildren().clear();
        
        for (int i = 0; i < ArtificialLifeFX.ySize; i++){
            for (int j = 0; j < ArtificialLifeFX.xSize; j++) {
                ImageView grassView = new ImageView(grassIMG);
                grassView.setFitHeight(30);
                grassView.setFitWidth(30);
                
                display.add(grassView, j, i);
            }
        }

        for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //replaces space with correct symbol
            int tempy = (int) ArtificialLifeFX.world.entities.get(i).getyPos();
            int tempx = (int) ArtificialLifeFX.world.entities.get(i).getxPos();
            
            if(ArtificialLifeFX.world.entities.get(i).getAlive() == false && !"food".equals(ArtificialLifeFX.world.entities.get(i).getSpecies()) && !"obstacle".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(meatIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, ArtificialLifeFX.world.entities.get(i).getxPos(), ArtificialLifeFX.world.entities.get(i).getyPos());
            } else if("food".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){  
                ImageView imageView = new ImageView(fruit1IMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
            }else if("obstacle".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(rockIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
            }else if("ant".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(antIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
            }else if("bee".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(beeIMG);
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                display.add(imageView, tempx, tempy);
            }else{
                ImageView imageView = new ImageView(errorIMG);  
                imageView.setFitHeight(30);
                imageView.setFitWidth(18);
                display.add(imageView, tempx, tempy);
            }
            
            
        }
        
        labelEntities();
    }
    
    public void labelEntities(){
        for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //replaces space with correct symbol
            int tempy = (int) ArtificialLifeFX.world.entities.get(i).getyPos();
            int tempx = (int) ArtificialLifeFX.world.entities.get(i).getxPos();
                
            if(ArtificialLifeFX.world.getShowID() == true){
                Label ID = new Label();
                ID.setText(ArtificialLifeFX.world.entities.get(i).getID()+"");
                ID.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
                display.add(ID, tempx, tempy);
            }   
        }
    }
    
    
    public void setWorldSetupLabel(String text){
        controlPanelTabs.setWorldSetupLabel(text);
    }
}
