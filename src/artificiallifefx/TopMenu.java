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
    /**
     * Generates an alert pop-up box
     * @param title Title of box
     * @param content Content of box
     */
    public void alertBox(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }
    
    /**
     * Sets up the top menu bar
     * @return Completed menu
     */
    public MenuBar setMenu() {

        MenuBar menuBar = new MenuBar();

        Menu mFile = new Menu("File");

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
        mFile.getItems().addAll(mLoadConfig, mSave, mSaveAs, mExit);

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

        menuBar.getMenus().addAll(mFile, mHelp);
        return menuBar;
    }
}
