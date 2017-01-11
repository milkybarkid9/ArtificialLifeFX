/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artificiallifefx;

import static artificiallifefx.ArtificialLifeFX.moveTimer;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author James
 */
public class TopMenu {
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
            moveTimer.stop();
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
            moveTimer.start();
        });

        MenuItem mPlayPause = new MenuItem("Play/Pause");
        mPlayPause.setOnAction((ActionEvent actionEvent) -> {
            moveTimer.stop();
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
}
