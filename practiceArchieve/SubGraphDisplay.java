package practiceArchieve;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class SubGraphDisplay {
  private Pane graph1 = new Pane();
  private Pane graph2 = new Pane();
  private Pane graph3 = new Pane();
  private HBox graphLayout = new HBox();
  // Borders
  private Border redBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private Border blueBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final int WIDTH = 1525;
  
  public SubGraphDisplay() {
    graph1.setPrefSize(500, 500);
    graph1.setBorder(blackBorder);
    Region spacerG1 = new Region();
    spacerG1.setPrefSize(10, 500);
    graph2.setPrefSize(500, 500);
    graph2.setBorder(blackBorder);
    Region spacerG2 = new Region();
    spacerG2.setPrefSize(10, 500);
    graph3.setPrefSize(500, 500);
    graph3.setBorder(blackBorder);

    graphLayout.getChildren().addAll(graph1, spacerG1, graph2, spacerG2, graph3);
    graphLayout.setMinWidth(WIDTH);
    graphLayout.setMinHeight(500);
    graphLayout.setBorder(blueBorder);
  }
  
  public HBox getLayout() {
    return graphLayout;
  }
}
