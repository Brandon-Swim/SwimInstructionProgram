package general;
/*
 * This class displays the welcome message for the user.
 * The message will introduce the user to the program and
 * give some functionality. This will be run through 
 * the foreground class. 
 * 
 */

import java.io.File;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class WelcomePage{
    
        Scene introduction;
    public WelcomePage() {
        //Border for all components
        Border welcomeBorder = new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        //Vertical Layout for all components
        VBox layout = new VBox();
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);
        //Button to move to main program
        Button but1 = new Button("Click to Continue");
        but1.setPrefSize(200, 100);
        but1.setBorder(welcomeBorder);
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                GUI.getStage().setScene(GUI.mainPage);
                GUI.getStage().setMaximized(true);
            }
        };
        but1.setOnAction(buttonHandler);
        //Introduction text for the user.
        Label welcomeText = new Label( fileReader("WelcomeText.txt"));
        welcomeText.setWrapText(true);
        welcomeText.setFont(Font.font("Arial", 16));
        welcomeText.setTextFill(Color.BLACK);
        welcomeText.setTextAlignment(TextAlignment.CENTER);
        welcomeText.setAlignment(Pos.CENTER);
        welcomeText.setPrefSize(300, 200);
        welcomeText.setBorder(welcomeBorder);
        //Header Text
        Label headerText = new Label("Welcome");
        headerText.setFont(Font.font("Arial", 48));
        headerText.setTextFill(Color.RED);
        headerText.setPrefSize(400, 100);
        headerText.setBorder(welcomeBorder);
        headerText.setTextAlignment(TextAlignment.CENTER);
        headerText.setAlignment(Pos.CENTER);
        
        layout.getChildren().addAll(headerText, welcomeText, but1);
        introduction = new Scene(layout, 800, 600);
    }
    public Scene setScene() {
        return introduction;
    }
    public static void main(String[] args) {
        GUI.main(args);  
    }
    public static String fileReader(String fileName) {
        String fileContents = "";
        File inputFile = new File(fileName);
        try {
            Scanner fileInput = new Scanner(inputFile);
            while (fileInput.hasNextLine()) {
                fileContents = fileContents + fileInput.nextLine();
                }
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fileContents;
    }
}
