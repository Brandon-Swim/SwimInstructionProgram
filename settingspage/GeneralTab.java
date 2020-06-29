package settingspage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class GeneralTab extends Tabs {
  private final Border BLUE_BORDER = new Border(new BorderStroke(Color.BLUE,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public GeneralTab() {
    super();
    pane.setBorder(BLUE_BORDER);
    event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        System.out.println("General Tab");
        System.out.println(Settings.getMainLayout().getChildren().size());
        Settings.getMainLayout().getChildren().remove(1);
        Settings.getMainLayout().getChildren().add(pane);
      }
    };
  }
}
