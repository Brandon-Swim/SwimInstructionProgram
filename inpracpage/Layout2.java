package inpracpage;

import java.util.ArrayList;
import background.SwimWorkout;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class Layout2 {
  
  private HBox layout;
  private final Background BACKGROUND =
      new Background(new BackgroundFill(Color.rgb(0, 255, 0), CornerRadii.EMPTY, Insets.EMPTY));
  
  private Region spacer1;
  private Region spacer2;
  private Label thing;
  
  public Layout2(ArrayList<SwimWorkout> workouts) {
    layout = new HBox();
    spacer1 = new Region();
    spacer1.setPrefWidth(500);
    spacer2 = new Region();
    spacer2.setPrefWidth(500);
    thing =  new Label("Switched");
    thing.setPrefWidth(500);
    layout.setBackground(BACKGROUND);
    layout.getChildren().addAll(spacer1, thing, spacer2);
  }
  
  public HBox getLayout() {
    return layout;
  }
}
