package settingspage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class UserSettingsTab extends Tabs{
  private final Border GREEN_BORDER = new Border(new BorderStroke(Color.GREEN,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public UserSettingsTab() {
    super();
    event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        Settings.getMainLayout().getChildren().remove(1);
        Settings.getMainLayout().getChildren().add(pane);
      }
    };
  }
}
