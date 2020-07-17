package inpracpage;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class will have two main stages to it
 * 1. the file to be checked 
 * 2. displaying the file to be checked 
 * @author Brandon
 *
 */
public class ImportedPractices {

    private static Pane mainPane;
    private Layout1 importingPractice;
    private final Background BACKGROUND =
        new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY));
    
    public ImportedPractices() {
      mainPane = new Pane();
      mainPane.setBackground(BACKGROUND);
      importingPractice = new Layout1();
      mainPane.getChildren().add(importingPractice.getLayout());
    }
    
    public static void setPane(HBox layout) {
      mainPane.getChildren().clear();
      mainPane.getChildren().add(layout);
    }
    
    public static void setPane(VBox layout) {
      mainPane.getChildren().clear();
      mainPane.getChildren().add(layout);
    }
    
    public static Pane getPane() {
        return mainPane;
    }
}
