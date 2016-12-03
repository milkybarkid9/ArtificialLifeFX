/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.fromText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author James
 */
public class WorldTab {
    public Tab setup(){
        Tab worldTab = new Tab("World");
            VBox worldTabVB = new VBox(ArtificialLifeFX.DEFAULT_PADDING);
                Label dimensionLabel = new Label("World dimensions:");
                HBox dimensionsHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);    
                    Label xLabel = new Label("X:");
                    TextField sizeX = new TextField ("10");
                    Label yLabel = new Label("Y:");
                    TextField sizeY = new TextField ("10");
                Label foodPercentLabel = new Label("Food percentage:");
                TextField foodPercent = new TextField ("5");
                Label obstaclePercentLabel = new Label("Obstacle percentage:");
                TextField obstaclePercent = new TextField ("5");
                Label beeQuantLabel = new Label("Number of bees:");
                TextField beeQuant = new TextField ("5");
                Label antQuantLabel = new Label("Number of ants:");
                TextField antQuant = new TextField ("5");
                Button submitWorld = new Button("Generate world");
                              
                
                dimensionsHB.getChildren().addAll(xLabel, sizeX, yLabel, sizeY);
            worldTabVB.getChildren().addAll(
                    dimensionLabel, 
                    dimensionsHB, 
                    foodPercentLabel, 
                    foodPercent, 
                    obstaclePercentLabel, 
                    obstaclePercent,
                    beeQuantLabel,
                    beeQuant,
                    antQuantLabel,
                    antQuant,
                    submitWorld
            );
        worldTab.setContent(worldTabVB);
                
        sizeX.setMaxWidth(60);
        sizeY.setMaxWidth(60);
        foodPercent.setMaxWidth(60);
        obstaclePercent.setMaxWidth(60);//setPrefWidth(60);
        beeQuant.setMaxWidth(60);
        antQuant.setMaxWidth(60);
        submitWorld.setMaxWidth(100);
        
        submitWorld.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                
            }
        });
        
        worldTabVB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING
        ));
        
        
         
        
        return worldTab;
    }
    
}
