/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smithstopwatchfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Aaron
 */
public class SmithStopwatchFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Get the FXML document for the root
        Parent root = FXMLLoader.load(getClass().getResource("SmithStopwatchFXMLDocument.fxml"));
        
        //Set the scene as the root
        Scene scene = new Scene(root);
        
        //Set up stage that will appear
        Image icon = new Image(getClass().getResourceAsStream("clockicon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Stopwatch");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
