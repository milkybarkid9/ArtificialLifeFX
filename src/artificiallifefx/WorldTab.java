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
        Tab worldTab = new Tab();
            VBox worldTabVB = new VBox(ArtificialLifeFX.DEFAULT_PADDING);
                Label dimensionLabel = new Label();
                HBox dimensionsHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);    
                    Label xLabel = new Label();
                    TextField sizeX = new TextField ();
                    Label yLabel = new Label();
                    TextField sizeY = new TextField ();
                Label foodPercentLabel = new Label();
                TextField foodPercent = new TextField ();
                Label obstaclePercentLabel = new Label();
                TextField obstaclePercent = new TextField ();
                Label beeQuantLabel = new Label();
                TextField beeQuant = new TextField ();
                Label antQuantLabel = new Label();
                TextField antQuant = new TextField ();
                Button submitWorld = new Button();
                
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
        
        
        worldTab.setText("World");
            
        dimensionLabel.setText("World dimensions:");
        xLabel.setText("X:");
        yLabel.setText("Y:");
        foodPercentLabel.setText("Food percentage:"); 
        obstaclePercentLabel.setText("Obstacle percentage:");
        beeQuantLabel.setText("Number of bees:");
        antQuantLabel.setText("Number of ants:");        
        submitWorld.setText("Generate world");    
        
        sizeX.setPrefWidth(60);
        sizeY.setPrefWidth(60);
        foodPercent.setPrefWidth(60);
        obstaclePercent.setPrefWidth(60);
        beeQuant.setPrefWidth(60);
        antQuant.setPrefWidth(60);
        submitWorld.setPrefWidth(100);
        
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
