/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;

/**
 *
 * @author James
 */
public class NewEntityTab {
    Tab newEntityTab = new Tab();
    
    public Tab setup(UI localUI){
        newEntityTab.setText("Entity Management");
        
        newEntityTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ArtificialLifeFX.world.setShowID(true);
            }
        });
        
        return newEntityTab;
    }
}
