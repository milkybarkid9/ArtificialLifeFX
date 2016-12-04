/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.fromText;
import static artificiallifefx.ArtificialLifeFX.timer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
        Separator sep1 = new Separator(Orientation.VERTICAL);
        Label isRunning = new Label("Probably");
        
        simControl.getChildren().addAll(
                playButton, 
                resetButton, 
                sep1, 
                isRunning
                );
        simControl.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING
        ));
                
        playButton.setPrefWidth(60);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if("Play".equals(playButton.getText())){
                    playButton.setText("Pause");
                    ArtificialLifeFX.running = true;
                    ArtificialLifeFX.timer.start();
                }else{
                    playButton.setText("Play");
                    ArtificialLifeFX.running = false;
                    ArtificialLifeFX.timer.stop();
                }
                //code
            }
        });
        
        resetButton.setPrefWidth(60);
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                playButton.setText("Play");
                ArtificialLifeFX.running = false;
                timer.stop();                
                ArtificialLifeFX.world = fromText("d"); 
                ui.updateWorld();
            }
        });
        
        return simControl;
    }
}
