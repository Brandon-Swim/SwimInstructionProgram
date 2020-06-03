import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Foreground extends Application{
    
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
        Welcome introduction = new Welcome();   
        MainPage practiceTab = new MainPage();  //Pane For Mainpage
        //Runs Introduction
        mainStage.setTitle("Practice Builder");
        mainStage.setScene(introduction.setScene());
        mainStage.centerOnScreen();
        mainStage.show();
        
        TabPane tabs = new TabPane();
        Tab tab1 = new Tab("Practice Builder", practiceTab.getPane());
        Tab tab2 = new Tab("Practice Archieve"  , new Pane());
        Tab tab3 = new Tab("Imported Practices" , new Pane());
        Tab tab4 = new Tab("Settings", new Pane());

        tabs.getTabs().add(tab1);
        tabs.getTabs().add(tab2);
        tabs.getTabs().add(tab3);
        tabs.getTabs().add(tab4);
        
        Label header = new Label("Header");
        header.setFont(Font.font("Arial", 48));
        header.setTextFill(Color.RED);
        header.setPrefSize(400, 100);
        header.setTextAlignment(TextAlignment.CENTER);
        header.setAlignment(Pos.CENTER);
        VBox tabLayout = new VBox(tabs);
        HBox overLayout = new HBox();
        mainPage = new Scene(tabLayout);//Set minimum Size
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
