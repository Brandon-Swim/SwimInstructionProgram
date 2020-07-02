package general;

import inpracpage.ImportedPractices;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mainpage.MainPage;
import practiceArchieve.PracticeArchieve;
import settingspage.Settings;

public class GUI extends Application{
    
    public static Stage StageGUI;
    public static Scene mainPage;
    /*
     * Returns the mainStage
     */
    public static Stage getStage() {
        return StageGUI;
    }
    
    public void start(Stage mainStage) {
        StageGUI = mainStage;
        //Declare Different Scenes
        WelcomePage introduction = new WelcomePage();   
        MainPage practiceTab = new MainPage();  //Pane For Mainpage
        PracticeArchieve archieveTab = new PracticeArchieve();
        ImportedPractices importTab = new ImportedPractices();
        Settings settingsTab = new Settings();
        //Runs Introduction
        mainStage.setTitle("Practice Builder");
        mainStage.setScene(introduction.setScene());
        mainStage.centerOnScreen();
        mainStage.show();
        
        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Practice Builder", practiceTab.getPane());
        Tab tab2 = new Tab("Practice Archieve"  , archieveTab.getPane());
        Tab tab3 = new Tab("Imported Practices" , importTab.getPane());
        Tab tab4 = new Tab("Settings", settingsTab.getPane());

        tabs.getTabs().add(tab4);
        tabs.getTabs().add(tab1);
        tabs.getTabs().add(tab2);
        tabs.getTabs().add(tab3);
        //tabs.getTabs().add(tab4);
        
        Label header = new Label("Practice Builder");
        header.setFont(Font.font("Arial", 48));
        header.setTextFill(Color.RED);
        header.setPrefSize(400, 100);
        header.setTextAlignment(TextAlignment.CENTER);
        header.setAlignment(Pos.CENTER);
        VBox tabLayout = new VBox(tabs);
        VBox overLayout = new VBox();
        overLayout.getChildren().addAll(header, tabLayout);
        mainPage = new Scene(overLayout);//Set minimum Size
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
