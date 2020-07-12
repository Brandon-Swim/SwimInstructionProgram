package graphs;

import java.util.ArrayList;
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

public class GraphPane {
  private final Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private Pane pane;
  private HBox layout;
  private ArrayList<VBox> graphLayout = new ArrayList<VBox>();
  private Region sp1 = new Region();
  private Region sp2 = new Region();

  public GraphPane() {
    pane = new Pane();
    layout = new HBox();
  }

  public GraphPane(ArrayList<Graphs> graphs) {
    this();
    pane.setPrefSize(1525, 600);
    pane.setBorder(border);

    sp1.setPrefSize(10, 600);
    sp2.setPrefSize(10, 600);
  }

  public Pane getPane() {
    return pane;
  }

  public void setLayout(Graphs graph) {
    graphLayout.clear();
    for (int j = 0; j < graph.size(); j++) {
      graphLayout.add(new VBox());
      graphLayout.get(j).getChildren().addAll(graph.getHeader(j), graph.getLayout(j));
      switch (j) {
        case 0:
          layout.getChildren().addAll(graphLayout.get(j), sp1);
          break;
        case 1:
          layout.getChildren().addAll(graphLayout.get(j), sp2);
          break;
        case 2:
          layout.getChildren().add(graphLayout.get(j));
          break;
        default:
          layout.getChildren().add(graphLayout.get(j));
      }
    }
    pane.getChildren().add(layout);
  }

  public HBox getLayout() {
    return layout;
  }
}
