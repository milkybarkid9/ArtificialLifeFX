/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.moveTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author James
 */
public class SimControlBar {        
    public HBox setup(ArtificialLifeFX artificialLife, UI ui){
        HBox simControl = new HBox(5);
        Button playButton = new Button("Play");
        Button resetButton = new Button("Reset"); 
        
        simControl.getChildren().addAll(
                playButton, 
                resetButton
                );
        simControl.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING
        ));
                
        playButton.setPrefWidth(60);
        playButton.setOnAction((ActionEvent e) -> {
            if("Play".equals(playButton.getText())){
                playButton.setText("Pause");
                ArtificialLifeFX.running = true;
                ArtificialLifeFX.moveTimer.start();
            }else{
                playButton.setText("Play");
                ArtificialLifeFX.running = false;
                ArtificialLifeFX.moveTimer.stop();
            }
        });
        
        resetButton.setPrefWidth(60);
        resetButton.setOnAction((ActionEvent e) -> {
            playButton.setText("Play");
            ArtificialLifeFX.running = false;
            moveTimer.stop();
        });
        
        return simControl;
    }
}
