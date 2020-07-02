package graphs;

import java.util.ArrayList;
import general.Storage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
<<<<<<< HEAD
  private ArrayList<DonutGraph> distance = new ArrayList<DonutGraph>(); //TODO
=======
  private ArrayList<DistanceGraph> distance = new ArrayList<DistanceGraph>(); //TODO
>>>>>>> beta
  private ArrayList<DonutGraph> intensity = new ArrayList<DonutGraph>(); //TODO
  private ArrayList<DonutGraph> type = new ArrayList<DonutGraph>(); //TODO
  private ArrayList<GraphData> intensityData = new ArrayList<GraphData>();
  private ArrayList<GraphData> typeData = new ArrayList<GraphData>();
  private final int WIDTH = 500;
  private final int HEIGHT = 400;
  private HBox layout = new HBox();
  private VBox[] vLayout = new VBox[3];
  
  public Graphs() {
    
  }
  
  
  public Graphs(Pane pane) {
    ArrayList<GraphData> distanceData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      distanceData.add(new GraphData());
      this.distanceData.add(distanceData.get(i));
    }
<<<<<<< HEAD
    ArrayList<DonutGraph> distance = new ArrayList<DonutGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      distance.add(new DonutGraph(distanceData.get(i).getData()));
=======
    ArrayList<DistanceGraph> distance = new ArrayList<DistanceGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      distance.add(new DistanceGraph(distanceData.get(i).getData()));
>>>>>>> beta
      distance.get(i).setPrefSize(WIDTH, 500);
      distance.get(i).setBorder(testBorder);
      distance.get(i).setLegendVisible(false);
      this.distance.add(distance.get(i));
    }
    
    ArrayList<GraphData> intensityData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      intensityData.add(new GraphData());
    } 
    this.intensityData = intensityData;
    ArrayList<DonutGraph> intensity = new ArrayList<DonutGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      intensity.add(new DonutGraph(intensityData.get(i).getData()));
      intensity.get(i).setPrefSize(WIDTH, 500);
      intensity.get(i).setBorder(testBorder);
      intensity.get(i).setLegendVisible(false);
      this.intensity.add(intensity.get(i));
    }
    
    ArrayList<GraphData> typeData = new ArrayList<GraphData>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      typeData.add(new GraphData());
    } 
    this.typeData = typeData;
    ArrayList<DonutGraph> type = new ArrayList<DonutGraph>();
    for (int i = 0; i < Storage.AMT_GROUPS; i++) {
      type.add(new DonutGraph(typeData.get(i).getData()));
      type.get(i).setPrefSize(WIDTH, 500);
      type.get(i).setBorder(testBorder);
      type.get(i).setLegendVisible(false);
      this.type.add(type.get(i));
    }
    
    Region spacer1 = new Region();
    spacer1.setPrefSize(10, 100);
    Region spacer2 = new Region();
    spacer2.setPrefSize(10, 100);
    
    VBox GLayout1 = new VBox();
    Label distanceLabel = new Label("Distance per Set");
    layoutSetUp(distanceLabel, GLayout1, 1);

    VBox GLayout2 = new VBox();
    Label intensityLabel = new Label("Intensity per Set");
    layoutSetUp(intensityLabel, GLayout2, 2);
    
    VBox GLayout3 = new VBox();
    Label typeLabel = new Label("Distance per Type");
    layoutSetUp(typeLabel, GLayout3, 3);
    
    HBox layout = new HBox();
    layout.setPrefSize(2000, 500); // Might not need
    layout.setBorder(testBorder);
    layout.getChildren().addAll(GLayout1, spacer1, GLayout2, spacer2, GLayout3);
    this.layout = layout;
    pane.getChildren().add(layout);
  }
  
  private void layoutSetUp(Label label, VBox layout, int ID) {
    label.setPrefSize(WIDTH, 50);
    label.setFont(Font.font("Arial", 32));
    label.setBorder(testBorder);
    label.setAlignment(Pos.BASELINE_CENTER);
    
    layout.setPrefSize(WIDTH, 500);
    layout.setBorder(testBorder);
    switch (ID) {
      case 1:
        layout.getChildren().addAll(label, distance.get(0));
        break;
      case 2:
        layout.getChildren().addAll(label, intensity.get(0));
        break;
      case 3:
        layout.getChildren().addAll(label, type.get(0));
        break;
    }
    vLayout[ID - 1] = layout;
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
  
  public HBox getLayout() {
    return this.layout;
  }
   
  public void remGraphs() {
    for (int i = 0; i < vLayout.length; i++) {
      vLayout[i].getChildren().remove(1);
    }
  }
  
  public void addGraphs(int index) {
      vLayout[0].getChildren().add(distance.get(index));
      vLayout[1].getChildren().add(intensity.get(index));
      vLayout[2].getChildren().add(type.get(index));
  }
  
<<<<<<< HEAD
  public DonutGraph getDistanceGraph(int ID) {
=======
  public DistanceGraph getDistanceGraph(int ID) {
>>>>>>> beta
     return distance.get(ID);
  }
  
  public DonutGraph getIntensityGraph(int ID) {
    return intensity.get(ID);
 }
  
  public DonutGraph getTypeGraph(int ID) {
    return type.get(ID);
 }
  

}
