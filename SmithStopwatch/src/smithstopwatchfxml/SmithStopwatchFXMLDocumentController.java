/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smithstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Aaron
 */
public class SmithStopwatchFXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
      
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private HBox buttonBox;
    
    @FXML
    private HBox timerBox;
            
    @FXML
    private Text timerText;
    
    @FXML
    private StackPane imageContainer;
    
    @FXML
    private ImageView dialImageView;
    
    @FXML
    private ImageView handImageView;
            
    @FXML
    public Button start;
    
    @FXML
    public Button reset;
    
    @FXML
    public Button stop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTimer();
    }    
    
    @FXML
    public void start() {
        timeline.play();
    }
    
    @FXML
    public void stop() {
        timeline.stop();
    }
    
    @FXML
    public void reset() {
        stop();
        secondsElapsed = 0;
        handImageView.setRotate(0);
        seconds = 0;
        minutes = 0;
        hours = 0;
        strSeconds = String.format("%02d",seconds);
        strMinutes = String.format("%02d",minutes);
        strHours = String.format("%02d",hours);
        timerText.setText(strHours + ":" + strMinutes + ":" + strSeconds);
    }
    
    //Initialize the important variables.
    private double tickTimeInSeconds = 1.0;    //Can be altered to change how often the hand moves.
    private final double angleDeltaPerSeconds = 6.0; //Will be used to move the hand every second.
    private double secondsElapsed = 0;         //Will be used to keep track of how many seconds pass.
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private String strSeconds = String.format("%02d",seconds); //Will be used for printing out seconds
    private String strMinutes = String.format("%02d",minutes); //Will be used for printing minutes
    private String strHours = String.format("%02d",hours);   //Will be used for printing hours
    private Timeline timeline; //Timelines can be used to schedule an action.
    private KeyFrame keyFrame; //KeyFrames are used to mark the beginning or end of transition in a timeline.
    
    //Sets up the timer functions based on run status
    public void setupTimer() {
        boolean running = false; //Initialize a boolean to keep track of whether the timer is running or not.
        
        if(timeline != null) {
            if(timeline.getStatus() == Animation.Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }
        
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        
        if (running) {
            timeline.play();
        }
    }
    
    //Updates the timers every second
    private void update(){
            secondsElapsed += tickTimeInSeconds;
            double rotation = secondsElapsed * angleDeltaPerSeconds;
            handImageView.setRotate(rotation);
           
           seconds += tickTimeInSeconds;
           
           /*Bump minutes if seconds reaches 60*/
           if(seconds == 60.0)
           {
               minutes += 1;
               seconds = 0;
           }
           
           /*Bump hours if minutes reaches 60*/
           if(minutes == 60.0){
               hours += 1;
               minutes = 0;
           }
           
            strSeconds = String.format("%02d",seconds);
            strMinutes = String.format("%02d",minutes);
            strHours = String.format("%02d",hours);
            timerText.setText(strHours + ":" + strMinutes + ":" + strSeconds);
        }
        
    //Used in main to get the root container, borderpane
    public Parent getRootContainer() {
        return borderPane;
    }
    
    //Allows main to set up a different tickTimeInSecons
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
    }
    
    //Checks if the timer is running
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        }
        return false;
    }
}