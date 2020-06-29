package settingspage;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings {
  private final Border BLACK_BORDER = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border RED_BORDER = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Double MAX_WIDTH = 1540.0;
  private final Double MAX_HEIGHT = 715.0;
  private final int AMT_BUTTONS = 5;
  private final String[] butNames = new String[] {"General", "User Settings",
      "Side Panel Information", "Practice Archieve", "Imported Practices"};

  private Pane mainPane;
  private HBox layout;
  private Pane selectorPane;
  private static HBox spLayout;
  private Pane buttonPane;
  private VBox buttonLayout;
  private ScrollPane buttonScrollPane;
  private Button[] buttons;
  private ArrayList<EventHandler<ActionEvent>> events;

  public Settings() {
    mainPane = new Pane();
    layout = new HBox();
    selectorPane = new Pane();
    spLayout = new HBox();
    buttonPane = new Pane();
    buttonLayout = new VBox();
    buttonScrollPane = new ScrollPane(buttonPane);
    buttons = new Button[AMT_BUTTONS];
    events = new ArrayList<EventHandler<ActionEvent>>();
    eventSetUp();

    final Region leftSpacer = new Region();
    leftSpacer.setPrefSize(MAX_WIDTH * 0.2, MAX_HEIGHT);
    final Region rightSpacer = new Region();
    rightSpacer.setPrefSize(MAX_WIDTH * 0.2, MAX_HEIGHT);

    selectorPane.setPrefSize(MAX_WIDTH * 0.6, MAX_HEIGHT);
    selectorPane.setBorder(RED_BORDER);

    buttonPane.setPrefSize(MAX_WIDTH * 0.12, MAX_HEIGHT);
    for (int i = 0; i < buttons.length; i++) {
      buttons[i] = new Button(butNames[i]);
      buttons[i].setPrefSize(MAX_WIDTH * 0.12, 60);
      buttons[i].setFont(Font.font("Arail", 16));
      buttons[i].setWrapText(true);
      buttons[i].setOnAction(events.get(i));
      buttonLayout.getChildren().add(buttons[i]);
    }
    buttonPane.getChildren().add(buttonLayout);

    buttonScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    buttonScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    buttonScrollPane.setBorder(BLACK_BORDER);
    
    Pane placeHolder = new Pane();
    placeHolder.setPrefSize(MAX_WIDTH * 0.47, MAX_HEIGHT);

    spLayout.getChildren().addAll(buttonScrollPane, placeHolder);
    selectorPane.getChildren().add(spLayout);
    layout.getChildren().addAll(leftSpacer, selectorPane, rightSpacer);
    mainPane.getChildren().add(layout);
  }

  public static HBox getMainLayout() {
    return spLayout;
  }
  
  
  private void eventSetUp() {
    for (int i = 0; i < AMT_BUTTONS; i++) {
      switch (i) {
        case 0:
          events.add(new GeneralTab().getEvent());
          break;
        case 1:
          events.add(new UserSettingsTab().getEvent());
          break;
        case 2:
          events.add(new SidePaneTab().getEvent());
          break;
        case 3:
          events.add(new PracticeArchieveTab().getEvent());
          break;
        case 4:
          events.add(new ImportedPracticeTab().getEvent());
          break;
      }
    }
  }

  public Pane getPane() {
    return mainPane;
  }
}
