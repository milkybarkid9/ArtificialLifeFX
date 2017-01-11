/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.scene.control.TabPane;

/**
 *
 * @author James
 */
public class ControlPanelTabs {
    TabPane tabPane = new TabPane();        
    NewEntityTab newEntityTab = new NewEntityTab();            
    EntityInfoTab entityInfoTab = new EntityInfoTab();        
    WorldTab worldTab = new WorldTab();
    
        
    public TabPane setup(UI localUI){        
        
        tabPane.getTabs().addAll(worldTab.setup(localUI), newEntityTab.setup(localUI), entityInfoTab.setup(localUI));
        tabPane.setMinWidth(400);
        tabPane.setMaxWidth(400);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);   

        return tabPane;
    }

    void setWorldSetupLabel(String text) {
        worldTab.setWorldSetupLabel(text);
    }

}
