/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 *
 * @author James
 */
public class EntityInfoTab {
    Label entityInfoTitle = new Label("All Entities:");
    Label entityInfo = new Label();
    ScrollPane scrollPane = new ScrollPane();
    VBox vbox = new VBox();
    Tab entityInfoTab = new Tab("World");
    
    public Tab setup(UI localUI){
        entityInfoTab.setText("Entity Info");
        
        
        vbox.getChildren().add(entityInfo);
        vbox.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING, 
                ArtificialLifeFX.DEFAULT_PADDING
        ));
        
        scrollPane.setContent(vbox);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        
        entityInfoTab.setContent(scrollPane);
        
        entityInfoTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ArtificialLifeFX.world.setShowID(false);
            }
        });
        
        return entityInfoTab;
    }
    
    public void updateEntityInfo(AWorld world){
        entityInfo.setText(world.entityStats());
    }
}
