/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
 * Sets up the elements and contents of the new entity 
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
            
    /**
     * sets up the edit entity tab
     * @param localUI instance of UI it's in
     * @return completed tab
     */
    public Tab setup(UI localUI){
        newEntityTab.setText("Entity Management");
        
        type.getItems().addAll(
                "fruit",
                "fruit tree",
                "obstacle",
                "ant",
                "bee");
        
        newEntTabVB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        dimensionsHB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        addHB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        editDimensionsHB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        editHB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        delHB.setPadding(ArtificialLifeFX.DEFAULT_INSET);
        
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
        
        newEntityTab.setOnSelectionChanged((Event t) -> { //on tab clicked
            ArtificialLifeFX.world.setShowID(true); //show IDs
        });
        
        add.setOnAction((ActionEvent e) -> { //on add button pressed
            int xposVal, yposVal, energyVal;
            String species = "";
            char symbol = 'd';
            try {
                xposVal = Integer.parseInt(xpos.getText());
                yposVal = Integer.parseInt(ypos.getText());
                energyVal = Integer.parseInt(energy.getText());
                
                switch(type.getValue().toString()){ //get type of entity to add
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
                
                if(ArtificialLifeFX.world.isEntityAt(xposVal,yposVal)){ //if entity at
                    errorLabel.setText("Space already taken"); //state space taken
                }else if(!ArtificialLifeFX.world.borderCheck(xposVal,yposVal)){ //if out of bounds
                    errorLabel.setText("Coordinates are too large or too small"); //state out of bounds
                }else {
                    ArtificialLifeFX.world.addEntity(species, symbol, energyVal, xposVal, yposVal); //add entity
                }
                
            } catch(Exception ee) { //failed
                errorLabel.setText("All values must be numbers"); //likely parsing failed
            }
        });         
        
        entities.setOnMouseClicked((e) -> { //combobox clicked on
            entities.getItems().clear(); //clear items
            for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //for all entities
                //add the ID and species of all entities to the combobox
                entities.getItems().addAll("ID: "+ArtificialLifeFX.world.entities.get(i).getID()+" Species: "+ArtificialLifeFX.world.entities.get(i).getSpecies());
            }
        });        
        
        edit.setOnAction((e) -> { //edit button clicked
            try{
                String t = entities.getValue().toString(); //get combobox choice
                String[] values = t.split(" "); //split using spaces
                int ID = Integer.parseInt(values[1]); //get second string
                
                if(editEnergy.getText() != null){ //if not empty
                    int energy = Integer.parseInt(editEnergy.getText()); //parse edit value as int
                    ArtificialLifeFX.world.editEntity(ID, "energy", energy+""); //edit entity
                }
                if(editxpos.getText() != null){ //if not empty
                    int xpos = Integer.parseInt(editxpos.getText()); //parse edit value as int
                    ArtificialLifeFX.world.editEntity(ID, "xpos", xpos+""); //edit entity
                }
                if(editypos.getText() != null){ //if not empty
                    int ypos = Integer.parseInt(editypos.getText()); //parse edit value as int
                    ArtificialLifeFX.world.editEntity(ID, "ypos", ypos+""); //edit entity
                }
                editError.setText("Successfully changed"); //state success
            }catch(Exception ee){
                editError.setText("All values must be numbers"); //state fail
            }
        });
                
        entities.setOnAction((e) -> { //on combobox selection made
            try{
                String t = entities.getValue().toString(); //get combobox choice
                String[] values = t.split(" "); //split using spaces
                int ID = Integer.parseInt(values[1]); //get second string
                
                for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //for all entities
                    if(ID == ArtificialLifeFX.world.entities.get(i).getID()){ //if ID matches
                        //populate boxes with original values
                        editEnergy.setText(ArtificialLifeFX.world.entities.get(i).getEnergy()+"");
                        editxpos.setText(ArtificialLifeFX.world.entities.get(i).getxPos()+"");
                        editypos.setText(ArtificialLifeFX.world.entities.get(i).getyPos()+"");
                    }
                }
            }catch(Exception ee){
                //don't populate boxes
            }            
        });
        
        entities2.setOnMouseClicked((e) -> { //combobox clicked on
            entities2.getItems().clear(); //clear items
            for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //for all entities
                //add the ID and species of all entities to the combobox
                entities2.getItems().addAll("ID: "+ArtificialLifeFX.world.entities.get(i).getID()+" Species: "+ArtificialLifeFX.world.entities.get(i).getSpecies());
            }
        });   
        
        del.setOnAction((e) -> { //on delete button pressed
            try{
                String t = entities2.getValue().toString(); //get combobox choice
                String[] values = t.split(" "); //split using spaces
                int ID = Integer.parseInt(values[1]); //get second string

                for (int i = 0; i < ArtificialLifeFX.world.entities.size(); i++) { //for all entities
                    if(ID == ArtificialLifeFX.world.entities.get(i).getID()){ //check ID matches
                        ArtificialLifeFX.world.removeEntity(i); //remove entity
                    }
                }
                delError.setText("Successfully removed"); //state success
            }catch(Exception ee){
                delError.setText("Failed removing"); //state failure
            }           
        });                
        
        return newEntityTab;
    }
}
