/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArtificialLifeFX extends Application {
    AnimationTimer timer;
    AWorld world;
    BorderPane pane = new BorderPane();
    BorderPane menupane = new BorderPane();
    Pane display = new Pane();
    
    Button playButton = new Button("Play");
    Button resetButton = new Button("Reset");
    HBox simControl = new HBox(5);
    
    TabPane tabPane = new TabPane();
    Tab worldTab = new Tab();
    
    int spriteScale = 40;
    boolean running = false;
    
    Image beeIMG = new Image("file:bee.PNG", spriteScale, spriteScale, false, false);
    Image rockIMG = new Image("file:rock.PNG", spriteScale, spriteScale, false, false);
    Image fruit1IMG = new Image("file:fruit1.PNG", spriteScale, spriteScale, false, false);
    Image fruit2IMG = new Image("file:fruit2.PNG", spriteScale, spriteScale, false, false);
    Image fruit3IMG = new Image("file:fruit3.PNG", spriteScale, spriteScale, false, false);
    Image fruit4IMG = new Image("file:fruit4.PNG", spriteScale, spriteScale, false, false);
    Image fruit5IMG = new Image("file:fruit5.PNG", spriteScale, spriteScale, false, false);
    
    private double SCENE_WIDTH = 1420;
    private double SCENE_HEIGHT = 800;
    Scene scene = new Scene(pane, SCENE_WIDTH,SCENE_HEIGHT);
    
    

    public void alertBox(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
    
    public MenuBar setMenu() {
        /**
         * Function to set up the menu
         */
        MenuBar menuBar = new MenuBar();

        Menu mFile = new Menu("File");
        MenuItem mNewConfig = new MenuItem("New Configuration");
        mNewConfig.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mLoadConfig = new MenuItem("Load Configuration");
        mLoadConfig.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mSave = new MenuItem("Save");
        mSave.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mSaveAs = new MenuItem("Save As");
        mSaveAs.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mExit = new MenuItem("Exit");
        mExit.setOnAction((ActionEvent actionEvent) -> {
            timer.stop();
            System.exit(0);
        });
        mFile.getItems().addAll(mNewConfig, mLoadConfig, mSave, mSaveAs, mExit);

        Menu mView = new Menu("View");
        MenuItem mDisplayConfig = new MenuItem("Display Configuration");
        mDisplayConfig.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mEditConfig = new MenuItem("Edit Configuration");
        mDisplayConfig.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mLifeformInfo = new MenuItem("Lifeform Info");
        mLifeformInfo.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mMapInfo = new MenuItem("Map Info");
        mMapInfo.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });
        mView.getItems().addAll(mDisplayConfig, mEditConfig, mLifeformInfo, mMapInfo);

        Menu mEdit = new Menu("Edit");
        MenuItem mNewLifeform = new MenuItem("Add Lifeform");
        mMapInfo.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mModifyLifeform = new MenuItem("Modify Lifeform");
        mDisplayConfig.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });

        MenuItem mRemoveLifeform = new MenuItem("Remove Lifeform");
        mLifeformInfo.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });
        mEdit.getItems().addAll(mNewLifeform, mModifyLifeform, mRemoveLifeform);

        Menu mSimulation = new Menu("Simulation");
        MenuItem mRun = new MenuItem("Run");
        mRun.setOnAction((ActionEvent actionEvent) -> {
            timer.start();
        });

        MenuItem mPlayPause = new MenuItem("Play/Pause");
        mPlayPause.setOnAction((ActionEvent actionEvent) -> {
            timer.stop();
        });

        MenuItem mReset = new MenuItem("Reset");
        mReset.setOnAction((ActionEvent actionEvent) -> {
            //code here
        });
        mSimulation.getItems().addAll(mRun, mPlayPause, mReset);

        Menu mHelp = new Menu("Help");
        MenuItem mAboutApp = new MenuItem("About (Application)");
        mAboutApp.setOnAction((ActionEvent actionEvent) -> {
            alertBox("About (Application)", "Artificial Life Simulator");
        });

        MenuItem mAboutAuth = new MenuItem("About (Author)");
        mAboutAuth.setOnAction((ActionEvent actionEvent) -> {
            alertBox("About (Application)", "Author: James Rhymes\nStudent Number: 23003907\nEmail: jamesrhym@gmail.com");
        });
        mHelp.getItems().addAll(mAboutApp, mAboutAuth);

        menuBar.getMenus().addAll(mFile, mView, mEdit, mSimulation, mHelp);
        return menuBar;
    }
    
    public static AWorld fromText(String input){
        if ("d".equals(input) || "default".equals(input)){ 
            input = "50 36 20 1 ant 4 bee 6"; //d creates a default world
        }
        String[] splitInput = input.split(" "); //split into array of string with spaces
        //strings to setup values
        double xSize = Integer.parseInt(splitInput[0]); 
        double ySize = Integer.parseInt(splitInput[1]); 
        float foodPercent = Integer.parseInt(splitInput[2]); 
        float obststaclePercent = Integer.parseInt(splitInput[3]); 
        String species1 = splitInput[4];
        int species1quant = Integer.parseInt(splitInput[5]);
        String species2 = splitInput[6];
        int species2quant = Integer.parseInt(splitInput[7]);
        
        AWorld world = new AWorld(xSize, ySize); //create new instance of the world
        
        //calculate food and obstacle amounts from percentages
        double food = xSize*ySize*(foodPercent/100);
        double obstacles = xSize*ySize*(obststaclePercent/100);
        
        for(int i = 0; i < food; i++){ //add food to world
            world.addEntity("food", 'f', 5);
        }
        for (int i = 0; i < obstacles; i++) { //add obstacles to world
            world.addEntity("obstacle", 'O', 0);
        }
        for (int i = 0; i < species1quant; i++) { //add entities
            char symbolChar[] = species1.toCharArray();          
            world.addEntity(species1, symbolChar[0], 5);
        }
        for (int i = 0; i < species2quant; i++) { //add entities
            char symbolChar[] = species2.toCharArray();
            world.addEntity(species2, symbolChar[0], 5);
        }
        return world; 
    }
    
    public void updateWorld(){  
        display.getChildren().clear();
        
        for (int i = 0; i < world.getEntityStack(); i++) { //replaces space with correct symbol
            double tempy = world.entities.get(i).getyPos();
            double tempx = world.entities.get(i).getxPos();           
                        
            if("food".equals(world.entities.get(i).getSpecies())){                     
                ImageView imageView = new ImageView(fruit1IMG);              
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView);   
            }else if("obstacle".equals(world.entities.get(i).getSpecies())){
                ImageView imageView = new ImageView(rockIMG);              
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView); 
            }else{
                ImageView imageView = new ImageView(beeIMG);              
                imageView.setTranslateX(tempx*20);
                imageView.setTranslateY(tempy*20);
                display.getChildren().add(imageView); 
            }
        }       
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        simControl.getChildren().addAll(playButton, resetButton);
        simControl.setPadding(new Insets(5, 5, 5, 5));
        
        worldTab.setText("World");
        tabPane.getTabs().add(worldTab);
        tabPane.setMinWidth(400);
        tabPane.setMaxWidth(400);
        
        playButton.setPrefWidth(60);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if("Play".equals(playButton.getText())){
                    playButton.setText("Pause");
                    running = true;
                    timer.start();
                }else{
                    playButton.setText("Play");
                    running = false;
                    timer.stop();
                }
                //code
            }
        });
        
        resetButton.setPrefWidth(60);
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                playButton.setText("Play");
                running = false;
                timer.stop();                
                world = fromText("d"); 
                updateWorld();
            }
        });
        
        timer = new AnimationTimer(){
            public void handle(long currentNanoTime)
            {
                //code
            }
        };
        
        pane.setTop(setMenu());
        pane.setCenter(display);
        pane.setRight(tabPane);
        pane.setBottom(simControl);
        
        display.setStyle("-fx-background-color: #B3FF99;");
        display.setPadding(new Insets(50, 5, 5, 5));
                
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Artificial Life Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
