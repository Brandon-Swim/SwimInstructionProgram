package mainpage;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SidePane {
  public static final String[] DEFAULT_NAMES = new String[] {"TTL Distance:", "TTL Time:",
      "Avg Intensity:", "Working Distance:", "Working Time:", "Working Intensity:", "Workout Type:",
      "T Season Distance", "T Practices left in the week", "T Practices til Comp",
      "T Game Day Counter", "T Practices until Taper", "Set distance"};
  public static final String[] UNTS = new String[] {"yds", "mins", "%", "yds", "mins", "%", "N/A",
      "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"};
  private static ArrayList<SideNode> nodes = new ArrayList<SideNode>();
  private Pane pane = new Pane();
  private ScrollPane scrollPane = new ScrollPane(pane);
  private final Border upperLayoutBorder = new Border(new BorderStroke(Color.BLUE,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private VBox sideLayout = new VBox();

  public SidePane() {
    for (int i = 0; i < DEFAULT_NAMES.length; i++) {
      nodes.add(new SideNode(i));
      sideLayout.getChildren().add(nodes.get(i).getLabel());
    }
    sideLayout.setAlignment(Pos.TOP_CENTER);
    pane.getChildren().add(sideLayout);
    scrollPane.setPrefSize(250, 690);
    scrollPane.setBorder(upperLayoutBorder);
    scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    scrollPane.setCache(false);
  }

  public ScrollPane getPane() {
    return scrollPane;
  }
  
  public static SideNode getNode(int index) {
    return nodes.get(index);
  }
  
  public static int getNodeSize() {
    return nodes.size();
  }
  
  public static void updateNodes() {
    for (int i = 0; i < nodes.size(); i++) {
      getNode(i).updateLabel();
    }
  }
}
