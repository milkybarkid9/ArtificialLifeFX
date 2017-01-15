/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author James
 */
public class NewEntityTab {
    Tab newEntityTab = new Tab();
        VBox newEntTabVB = new VBox(ArtificialLifeFX.DEFAULT_PADDING);
            Label addEnt = new Label("Add Entities");
            Label typeLabel = new Label("Type:");
            ComboBox type = new ComboBox();
            Label energyLabel = new Label("Energy:");
            TextField energy = new TextField("7");
            HBox dimensionsHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);    
                Label xLabel = new Label("X:");
                TextField xpos = new TextField ("1");
                Label yLabel = new Label("Y:");
                TextField ypos = new TextField ("1");
            HBox addHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);
            Button add = new Button("Add Entity");
            Label errorLabel = new Label();
            
            Separator sep1 = new Separator();
            
            Label editEnt = new Label("Edit Entities");
            Label entityLabel = new Label("Entities:");
            ComboBox entities = new ComboBox();
            Label editEnergyLabel = new Label("Energy:");
            TextField editEnergy = new TextField();
            HBox editDimensionsHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);    
                Label editxLabel = new Label("X:");
                TextField editxpos = new TextField ();
                Label edityLabel = new Label("Y:");
                TextField editypos = new TextField ();
            HBox editHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);
                Button edit = new Button("Edit Entity");
                Label editError = new Label();
            
            Separator sep2 = new Separator();
            
            Label delEnt = new Label("Remove Entities");
            Label delLabel = new Label("Entities:");
            ComboBox entities2 = new ComboBox();
            HBox delHB = new HBox(ArtificialLifeFX.DEFAULT_PADDING);
                Button del = new Button("Remove Entity");
                Label delError = new Label();
            
            Separator sep3 = new Separator();
            
    
    public Tab setup(UI localUI){
        newEntityTab.setText("Entity Management");
        
        type.getItems().addAll(
                "fruit",
                "fruit tree",
                "obstacle",
                "ant",
                "bee");
        
        newEntTabVB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        dimensionsHB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        addHB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        editDimensionsHB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        editHB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        delHB.setPadding(new Insets(
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING,
                ArtificialLifeFX.DEFAULT_PADDING));
        
        energy.setMaxWidth(60);
        xpos.setMaxWidth(60);
        ypos.setMaxWidth(60);
        
        editEnergy.setMaxWidth(60);
        editxpos.setMaxWidth(60);
        editypos.setMaxWidth(60);
        
        addEnt.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        editEnt.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        delEnt.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        
        dimensionsHB.getChildren().addAll(xLabel, xpos, yLabel, ypos);
        addHB.getChildren().addAll(add, errorLabel);
        editHB.getChildren().addAll(edit, editError);
        delHB.getChildren().addAll(del, delError);
        editDimensionsHB.getChildren().addAll(editxLabel,editxpos,edityLabel,editypos);
        newEntTabVB.getChildren().addAll(
                addEnt,
                typeLabel, 
                type, 
                energyLabel, 
                energy, 
                dimensionsHB, 
                addHB, 
                sep1,
                editEnt,
                entityLabel,
                entities,
                editEnergyLabel,
                editEnergy,
                editDimensionsHB,
                editHB,
                sep2,
                delEnt,
                delLabel,
                entities2,
                delHB,
                sep3);
        newEntityTab.setContent(newEntTabVB);
        
        newEntityTab.setOnSelectionChanged((Event t) -> {
            ArtificialLifeFX.world.setShowID(true);
        });
        
        add.setOnAction((ActionEvent e) -> {
            int xposVal, yposVal, energyVal;
            String species = "";
            char symbol = 'd';
            try {
                xposVal = Integer.parseInt(xpos.getText());
                yposVal = Integer.parseInt(ypos.getText());
                energyVal = Integer.parseInt(energy.getText());
                
                switch(type.getValue().toString()){
                    case "fruit":
                    case "fruit tree":
                        species = "food";
                        symbol = 'f';
                        break;
                    case "obstacle":
                        species = "obstacle";
                        symbol = 'o';
                        break;
                    case "ant":
                        species = "ant";
                        symbol = 'a';
                        break;
                    case "bee":
                        species = "bee";
                        symbol = 'b';
                        break;
                }
                
                if(ArtificialLifeFX.world.isEntityAt(xposVal,yposVal)){
                    errorLabel.setText("Space already taken");
                }else if(!ArtificialLifeFX.world.borderCheck(xposVal,yposVal)){
                    errorLabel.setText("Coordinates are too large or too small");
                }else {
                    ArtificialLifeFX.world.addEntity(species, symbol, energyVal, xposVal, yposVal);
                }
                
            } catch(Exception ee) {
                errorLabel.setText("Values must be numbers");
            }
        });         
        
        entities.setOnMouseClicked((e) -> {
            entities.getItems().clear();
            for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) {
                entities.getItems().addAll("ID: "+ArtificialLifeFX.world.entities.get(i).getID()+" Species: "+ArtificialLifeFX.world.entities.get(i).getSpecies());
            }
        });        
        
        edit.setOnAction((e) -> {
            try{
                String t = entities.getValue().toString();
                String[] values = t.split(" ");
                int ID = Integer.parseInt(values[1]);
                
                if(editEnergy.getText() != null){
                    int energy = Integer.parseInt(editEnergy.getText());
                    ArtificialLifeFX.world.editEntity(ID, "energy", energy+"");
                }
                if(editxpos.getText() != null){
                    int xpos = Integer.parseInt(editxpos.getText());
                    ArtificialLifeFX.world.editEntity(ID, "xpos", xpos+"");
                }
                if(editypos.getText() != null){
                    int ypos = Integer.parseInt(editypos.getText());
                    ArtificialLifeFX.world.editEntity(ID, "ypos", ypos+"");
                }
                editError.setText("Successfully changed");
            }catch(Exception ee){
                editError.setText("Failed changing");
            }
        });
                
        entities.setOnAction((e) -> {
            try{
                String t = entities.getValue().toString();
                String[] values = t.split(" ");
                int ID = Integer.parseInt(values[1]);

                for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) {
                    if(ID == ArtificialLifeFX.world.entities.get(i).getID()){
                        editEnergy.setText(ArtificialLifeFX.world.entities.get(i).getEnergy()+"");
                        editxpos.setText(ArtificialLifeFX.world.entities.get(i).getxPos()+"");
                        editypos.setText(ArtificialLifeFX.world.entities.get(i).getyPos()+"");
                    }
                }
            }catch(Exception ee){
                
            }            
        });
        
        entities2.setOnMouseClicked((e) -> {
            entities2.getItems().clear();
            for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) {
                entities2.getItems().addAll("ID: "+ArtificialLifeFX.world.entities.get(i).getID()+" Species: "+ArtificialLifeFX.world.entities.get(i).getSpecies());
            }
        });   
        
        del.setOnAction((e) -> {
            try{
                String t = entities2.getValue().toString();
                String[] values = t.split(" ");
                int ID = Integer.parseInt(values[1]);

                for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) {
                    if(ID == ArtificialLifeFX.world.entities.get(i).getID()){
                        ArtificialLifeFX.world.removeEntity(i);
                    }
                }
                delError.setText("Successfully removed");
            }catch(Exception ee){
                delError.setText("Failed removing");
            }           
        });        
        
        
        return newEntityTab;
    }
}
