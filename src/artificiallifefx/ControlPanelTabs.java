/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author James
 */
public class ControlPanelTabs {
    TabPane tabPane = new TabPane();        
    Tab newEntityTab = new Tab();            
    Tab entityInfoTab = new Tab();        
    WorldTab worldTab = new WorldTab();
        
    public TabPane setup(UI localUI){        
        
        newEntityTab.setText("Entity Management");
        entityInfoTab.setText("Entity Info");
        
        tabPane.getTabs().addAll(worldTab.setup(localUI), newEntityTab, entityInfoTab);
        tabPane.setMinWidth(400);
        tabPane.setMaxWidth(400);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);   

        return tabPane;
    }

    void setWorldSetupLabel(String text) {
        worldTab.setWorldSetupLabel(text);
    }

}
