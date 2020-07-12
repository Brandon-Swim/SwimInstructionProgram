package background;

import javafx.application.Application;
import gui.WelcomePage;
import inpracpage.ImportedPractices;
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

public class Master extends Application {


  public static Stage StageGUI;
  public static Scene mainPage;
  private TabPane tabScene;
  private Tab tab1;
  private Tab tab2;
  private Tab tab3;
  private Tab tab4;
  private Label header;

  /*
   * Returns the mainStage
   */
  public static Stage getStage() {
    return StageGUI;
  }

  public void start(Stage mainStage) {
    SwimWorkout workout = new SwimWorkout();
    workout.addGroup(new Group(SwimWorkout.getNextID()));
    StageGUI = mainStage;
    // Declare Different Tabs
    MainPage practiceTab = new MainPage(workout); // Pane For Mainpage
    PracticeArchieve archieveTab = new PracticeArchieve();
    ImportedPractices importTab = new ImportedPractices();
    Settings settingsTab = new Settings();
    // Runs Introduction
    mainStage.setTitle("Practice Builder");
    mainStage.setScene(new WelcomePage().setScene());
    mainStage.centerOnScreen();
    mainStage.show();

    tabScene = new TabPane();
    tab1 = new Tab("Practice Builder", practiceTab.getScrollPane());
    tab2 = new Tab("Practice Archieve", archieveTab.getPane());
    tab3 = new Tab("Imported Practices", importTab.getPane());
    tab4 = new Tab("Settings", settingsTab.getPane());

    tabScene.getTabs().add(tab2);
    tabScene.getTabs().add(tab1);
    //tabScene.getTabs().add(tab2);
    tabScene.getTabs().add(tab3);
    tabScene.getTabs().add(tab4);
    VBox tabLayout = new VBox(tabScene);

    header = new Label("Practice Builder");
    header.setFont(Font.font("Arial", 48));
    header.setTextFill(Color.RED);
    header.setPrefSize(400, 100);
    header.setTextAlignment(TextAlignment.CENTER);
    header.setAlignment(Pos.CENTER);
    VBox overLayout = new VBox();
    overLayout.getChildren().addAll(header, tabLayout);
    mainPage = new Scene(overLayout);// Set minimum Size

  }

  public static void main(String[] args) {
    Application.launch(args);
  }
}
