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
  private final static int height = 1200;
  private final static int width = 1525;
  private final int MULTIPLIER = 4;
  private final Border BLUE_BORDER = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  private static Pane mainPane;
  private static ScrollPane mainScrollPane;
  private VBox overalLayout;
  private HBox upperLayout;


  public PracticeArchieve() {
    mainPane = new Pane();
    mainScrollPane = new ScrollPane(mainPane);
    overalLayout = new VBox();
    upperLayout = new HBox();

    mainPane.setPrefSize(1800, height);
    mainPane.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = mainScrollPane.getContent().getBoundsInLocal().getWidth();
        double vvalue = mainScrollPane.getVvalue();
        mainScrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally
                                                       // fast regardless of the actual width of the
                                                       // component
      }
    });
    mainScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScrollPane.setFitToWidth(true);

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

  public static ScrollPane getPane() {
    return mainScrollPane;
  }
  
}
