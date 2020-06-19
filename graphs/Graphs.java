package graphs;

import java.util.ArrayList;
import general.Storage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import table.Set;

/**
 * Overall class that declares all other graphs
 * 
 * @author Brandon
 *
 */

public class Graphs {
  private final Border testBorder = new Border(new BorderStroke(Color.RED,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private ArrayList<GraphData> distanceData = new ArrayList<GraphData>();
  private ArrayList<DistanceGraph> distance = new ArrayList<DistanceGraph>(); //TODO
  private ArrayList<GraphData> intensityData = new ArrayList<GraphData>();
  private ArrayList<GraphData> typeData = new ArrayList<GraphData>();
  public Graphs() {
    
  }
  
  
  public Graphs(Pane pane) {
    ArrayList<GraphData> distanceData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      distanceData.add(new GraphData());
      this.distanceData.add(distanceData.get(i));
    }
    ArrayList<DistanceGraph> distance = new ArrayList<DistanceGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      distance.add(new DistanceGraph(distanceData.get(i).getData()));
      distance.get(i).setPrefSize(500, 500);
      distance.get(i).setBorder(testBorder);
      distance.get(i).setLegendVisible(false);
      this.distance.add(distance.get(i));
    }
    
    ArrayList<GraphData> intensityData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      intensityData.add(new GraphData());
    } 
    this.intensityData = intensityData;
    ArrayList<DistanceGraph> intensity = new ArrayList<DistanceGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      intensity.add(new DistanceGraph(intensityData.get(i).getData()));
      intensity.get(i).setPrefSize(500, 500);
      intensity.get(i).setBorder(testBorder);
      intensity.get(i).setLegendVisible(false);
    }
    
    ArrayList<GraphData> typeData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      typeData.add(new GraphData());
    } 
    this.typeData = typeData;
    ArrayList<DistanceGraph> type = new ArrayList<DistanceGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      type.add(new DistanceGraph(typeData.get(i).getData()));
      type.get(i).setPrefSize(500, 500);
      type.get(i).setBorder(testBorder);
      type.get(i).setLegendVisible(false);
    }
    
    Region spacer1 = new Region();
    spacer1.setPrefSize(10, 100);
    Region spacer2 = new Region();
    spacer2.setPrefSize(10, 100);
    
    HBox layout = new HBox();
    layout.setPrefSize(2000, 500); // Might not need
    layout.setBorder(testBorder);
    layout.getChildren().addAll(distance.get(0), spacer1, intensity.get(0), spacer2, type.get(0));
    pane.getChildren().add(layout);
  }
  
  public GraphData getData(String name, int index) {
    if (index < 0 || index > Storage.AMT_GROUPS - 1) {
      return null;
    }
    if (name.equals("distance")) {
      return distanceData.get(index);
    } else if (name.equals("intensity")) {
      return intensityData.get(index);
    } else if (name.equals("type")) {
      return typeData.get(index);
    } else {
      return null;
    }
  }
  
  public DistanceGraph getDistanceGraph(int ID) {
     return distance.get(ID);
  }
  
  public void refreshPane() {
  }

}
