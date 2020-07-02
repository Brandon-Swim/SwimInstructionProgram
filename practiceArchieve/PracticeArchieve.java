package practiceArchieve;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.ScrollEvent;
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

public class PracticeArchieve {  //TODO Check extending
  
  private final int MULTIPLIER = 4;
  private final Border BLUE_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  private Pane mainPane;
  private ScrollPane mainScene;
  private VBox overalLayout;
  private HBox upperLayout;


  public PracticeArchieve() {
    mainPane = new Pane();
    mainScene = new ScrollPane(mainPane);
    overalLayout = new VBox();
    upperLayout = new HBox();
    int height = 1200;
    int width = 1525;

    mainPane.setPrefSize(1800, height);
    mainPane.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = mainScene.getContent().getBoundsInLocal().getWidth();
        double vvalue = mainScene.getVvalue();
        mainScene.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally
                                                       // fast regardless of the actual width of the
                                                       // component
      }
    });
    mainScene.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScene.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScene.setFitToWidth(true);

    SelectionList selector = new SelectionList();
    MainDisplay display =  new MainDisplay();
    SubGraphDisplay graph = new SubGraphDisplay();

    Region spacer1 = new Region();
    spacer1.setPrefSize(2, 650);
    Region spacer2 = new Region();
    spacer2.setPrefSize(10, 650);

    upperLayout.getChildren().addAll(spacer1, selector.getLayout(), spacer2, display.getLayout());
    upperLayout.setMinWidth(width);
    upperLayout.setMinHeight(650);
    upperLayout.setBorder(BLUE_BORDER);

    overalLayout.getChildren().addAll(upperLayout, graph.getLayout());
    mainPane.getChildren().add(overalLayout);
  }
  

  public ScrollPane getPane() {
    return mainScene;
  }
  
}
