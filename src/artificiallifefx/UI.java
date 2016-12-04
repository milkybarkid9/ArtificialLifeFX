/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.world;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author James
 */
public class UI {
    BorderPane pane = new BorderPane();
    BorderPane menupane = new BorderPane();
    Pane display = new Pane();    
    
    ControlPanelTabs controlPanelTabs = new ControlPanelTabs();
    TopMenu topMenu = new TopMenu();
    SimControlBar controlBar = new SimControlBar();
    
    private final double SCENE_WIDTH = 1420;
    private final double SCENE_HEIGHT = 800;
    Scene scene = new Scene(pane, SCENE_WIDTH,SCENE_HEIGHT);
    
    public int localScale = ArtificialLifeFX.spriteScale;    
    Image beeIMG = new Image("file:bee.PNG", localScale, localScale, false, false);
    Image rockIMG = new Image("file:rock.PNG", localScale, localScale, false, false);
    Image fruit1IMG = new Image("file:fruit1.PNG", localScale, localScale, false, false);
    Image fruit2IMG = new Image("file:fruit2.PNG", localScale, localScale, false, false);
    Image fruit3IMG = new Image("file:fruit3.PNG", localScale, localScale, false, false);
    Image fruit4IMG = new Image("file:fruit4.PNG", localScale, localScale, false, false);
    Image fruit5IMG = new Image("file:fruit5.PNG", localScale, localScale, false, false);
    
    public Scene setup(ArtificialLifeFX artificialLife){
        pane.setTop(topMenu.setMenu());
        pane.setCenter(display);
        pane.setRight(controlPanelTabs.setup(this));
        pane.setBottom(controlBar.setup(artificialLife, this));
        
        display.setStyle("-fx-background-color: #B3FF99;");
        
        return scene;
    }
    
    public void updateWorld(){  
        display.getChildren().clear();
        
        for (int i = 0; i < ArtificialLifeFX.world.getEntityStack(); i++) { //replaces space with correct symbol
            double tempy = ArtificialLifeFX.world.entities.get(i).getyPos();
            double tempx = ArtificialLifeFX.world.entities.get(i).getxPos();
                        
            if("food".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(fruit1IMG);
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView);
            }else if("obstacle".equals(ArtificialLifeFX.world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(rockIMG);
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView); 
            }else{
                ImageView imageView = new ImageView(beeIMG);              
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView); 
            }
        }       
    }
    
    void setWorldSetupLabel(String text){
        controlPanelTabs.setWorldSetupLabel(text);
    }
}
