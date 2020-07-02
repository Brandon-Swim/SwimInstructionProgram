package settingspage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ImportedPracticeTab extends Tabs{
  private final Border PURPLE_BORDER = new Border(new BorderStroke(Color.PURPLE,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public ImportedPracticeTab() {
    super();
    pane.setBorder(PURPLE_BORDER);
    event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        Settings.getMainLayout().getChildren().remove(1);
        Settings.getMainLayout().getChildren().add(pane);
      }
    };
  }
}
