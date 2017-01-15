/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Sets up the elements and contents of the world tab
 * @author James
 */
public class WorldTab {
    Tab worldTab = new Tab("World");
            VBox worldTabVB = new VBox(ArtificialLifeFX.DEFAULT_PADDING);
                Label newWorld = new Label("New World");
                Label dimensionLabel = new Label("World dimensions:");
                HBox dimensionsHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);    
                    Label xLabel = new Label("X:");
                    TextField sizeX = new TextField ("10");
                    Label yLabel = new Label("Y:");
                    TextField sizeY = new TextField ("10");
                Label foodPercentLabel = new Label("Food percentage:");
                TextField foodPercent = new TextField ("5");
                Label obstaclePercentLabel = new Label("Obstacle percentage:");
                TextField obstaclePercent = new TextField ("0");
                Label beeQuantLabel = new Label("Number of bees:");
                TextField beeQuant = new TextField ("0");
                Label antQuantLabel = new Label("Number of ants:");
                TextField antQuant = new TextField ("1");
                HBox submitHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);
                    Button submitWorld = new Button("Generate world");
                    Label submitError = new Label();
                Separator sep1 = new Separator();
                Label worldSetup = new Label();
    
    /**
     * sets up the world setup tab
     * @param localUI instance of UI it's in
     * @return completed tab
     */
    public Tab setup(UI localUI){ 
                    submitHB.getChildren().addAll(submitWorld, submitError);
                dimensionsHB.getChildren().addAll(xLabel, sizeX, yLabel, sizeY);
            worldTabVB.getChildren().addAll(
                    newWorld,
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
                    submitHB,                    
                    sep1,
                    worldSetup
            );
        worldTab.setContent(worldTabVB);
                
        sizeX.setMaxWidth(60);
        sizeY.setMaxWidth(60);
        foodPercent.setMaxWidth(60);
        obstaclePercent.setMaxWidth(60);//setPrefWidth(60);
        beeQuant.setMaxWidth(60);
        antQuant.setMaxWidth(60);
        submitWorld.setMaxWidth(100);
        
        newWorld.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        submitWorld.setOnAction((ActionEvent e) -> { //sumbit world button pressed
            double  foodPercentVal, obstaclePercentVal;
            int sizeXVal, sizeYVal, beeQuantVal, antQuantVal;
            try {
                //parse values as appropriate types
                sizeXVal = Integer.parseInt(sizeX.getText());
                sizeYVal = Integer.parseInt(sizeY.getText());
                foodPercentVal = Double.parseDouble(foodPercent.getText());
                obstaclePercentVal = Double.parseDouble(obstaclePercent.getText());
                beeQuantVal = Integer.parseInt(beeQuant.getText());
                antQuantVal = Integer.parseInt(antQuant.getText());
                
                ArtificialLifeFX.xSize = sizeXVal;
                ArtificialLifeFX.ySize = sizeYVal;
                
                //create new world with types
                ArtificialLifeFX.world = ArtificialLifeFX.fromText(sizeXVal, sizeYVal, foodPercentVal, obstaclePercentVal, antQuantVal, beeQuantVal);
                
                submitError.setText(""); //no error text
                worldSetup.setText(
                        "Size: "+sizeXVal+" by "+sizeYVal+"\n"
                                + "Food percentage: "+foodPercentVal+"\n"
                                + "Obstacle percentage: "+obstaclePercentVal+"\n"
                                + "No. of Ants: "+antQuantVal+"\n"
                                + "No. of Bees: "+beeQuantVal);
                
            } catch(Exception ee) { //else show error message
                submitError.setText("All values must be numbers");
                localUI.updateWorld();
            }   });    
        
        worldTab.setOnSelectionChanged((Event t) -> { //on tab selected
           ArtificialLifeFX.world.setShowID(false); //don't show IDs
        });
        
        worldTabVB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        
        return worldTab;
    }
}

