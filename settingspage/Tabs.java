package settingspage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Tabs {
  protected EventHandler<ActionEvent> event;
  protected Pane pane;
  protected final Double MAX_WIDTH = 1540.0 * 0.47;
  protected final Double MAX_HEIGHT = 715.0;
  private final Border BLACK_BORDER = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public Tabs() {
    pane = new Pane();
    event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Tab Error");
        alert.setContentText("Something went wrong with settings Tabs");
        alert.showAndWait();
        System.exit(0);
      }
    };
    
    pane.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
    pane.setBorder(BLACK_BORDER);
  }
  
  public EventHandler<ActionEvent> getEvent() {
    return event;
  }
}
