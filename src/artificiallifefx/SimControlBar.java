/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.moveTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Sets up the bottom control bar for the UI
 * @author James
 */
public class SimControlBar {       
    /**
     * sets up the bottom control bar
     * @param ui
     * @return completed HBox
     */
    public HBox setup(UI ui){
        HBox simControl = new HBox(5);
        Button playButton = new Button("Play");
        Button resetButton = new Button("Reset"); 
        
        simControl.getChildren().addAll(playButton,resetButton);
        simControl.setPadding(ArtificialLifeFX.DEFAULT_INSET);
                
        playButton.setPrefWidth(60);
        playButton.setOnAction((ActionEvent e) -> { //play button pressed
            if("Play".equals(playButton.getText())){ //if paused
                playButton.setText("Pause");
                ArtificialLifeFX.running = true;
                ArtificialLifeFX.moveTimer.start();
            }else{ //if playing
                playButton.setText("Play");
                ArtificialLifeFX.running = false;
                ArtificialLifeFX.moveTimer.stop();
            }
        });
        
        resetButton.setPrefWidth(60);
        resetButton.setOnAction((ActionEvent e) -> { //not currently implemented
            playButton.setText("Play");
            ArtificialLifeFX.running = false;
            moveTimer.stop();
        });
        
        return simControl;
    }
}
