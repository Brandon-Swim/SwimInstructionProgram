package settingspage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class SidePaneTab extends Tabs{
  private final Border RED_BORDER = new Border(new BorderStroke(Color.RED,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public SidePaneTab() {
    super();
    pane.setBorder(RED_BORDER);
    event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        Settings.getMainLayout().getChildren().remove(1);
        Settings.getMainLayout().getChildren().add(pane);
      }
    };
  }
}
