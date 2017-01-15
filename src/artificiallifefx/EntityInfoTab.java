/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Sets up the elements and contents of the entity info tab
 * @author James
 */
public class EntityInfoTab {
    Label entityInfoTitle = new Label("All Entities");
    Label entityInfo = new Label();
    ScrollPane scrollPane = new ScrollPane();
    VBox vbox = new VBox();
    Tab entityInfoTab = new Tab("World");
    
    /**
     * sets up the entity info tab
     * @param localUI instance of UI it's in
     * @return completed tab
     */
    public Tab setup(UI localUI){
        entityInfoTab.setText("Entity Info");
        
        vbox.getChildren().addAll(entityInfoTitle, entityInfo);
        vbox.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        
        entityInfoTitle.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        scrollPane.setContent(vbox);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        
        entityInfoTab.setContent(scrollPane);
        
        entityInfoTab.setOnSelectionChanged(new EventHandler<Event>() { //tab clicked oin
            @Override
            public void handle(Event t) {
                ArtificialLifeFX.world.setShowID(false); //don't show ID
            }
        });
        
        return entityInfoTab;
    }
    
}
