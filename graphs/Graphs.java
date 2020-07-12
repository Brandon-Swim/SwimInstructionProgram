package graphs;

import java.util.ArrayList;
import background.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
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
  private final Border testBorder = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final int WIDTH = 500;
  private final int HEIGHT = 525;
  private static final int AMT_GRAPHS = 3;
  private ArrayList<Label> headers = new ArrayList<Label>();
  private ArrayList<DonutChart> charts = new ArrayList<DonutChart>();
  private DonutChart distanceGraph;
  private DonutChart typeGraph;
  private DonutChart intensityGraph;
  private ObservableList<Data> distanceData;
  private ObservableList<Data> typeData;
  private ObservableList<Data> intensityData;

  public Graphs() {
    distanceData = FXCollections.observableArrayList();
    typeData = FXCollections.observableArrayList();
    intensityData = FXCollections.observableArrayList();
    distanceGraph = new DonutChart(distanceData, "Distance Per Set");
    typeGraph = new DonutChart(typeData, "Distance Per Type");
    intensityGraph = new DonutChart(intensityData, "Intensity Per Set");
  }

  public Graphs(Group group) {
    this();
    headers.add(distanceGraph.getHeader());
    headers.add(typeGraph.getHeader());
    headers.add(intensityGraph.getHeader());
    populateGraphs(group);
    charts.add(distanceGraph);
    charts.add(typeGraph);
    charts.add(intensityGraph);
    for (int i = 0; i < charts.size(); i++) {
      charts.get(i).setBorder(testBorder);
      charts.get(i).setPrefSize(WIDTH, HEIGHT);
      charts.get(i).setLegendVisible(false);
    }
  }

  public void populateGraphs(Group group) {
    distanceData.clear();
    typeData.clear();
    intensityData.clear();
    for (int i = 0; i < group.size(); i++) {
      if (group.setDistance(i + 1) != 0) {
        Integer j = i + 1;
        distanceData.add(new PieChart.Data("Set " + (j).toString(), group.setDistance(i + 1)));
      }
      if (group.typeDistance(group.getType(i)) != 0) {
        boolean value = false;
        int index = 0;
        for (int j = 0; j < typeData.size(); j++) {
          if (!typeData.get(j).getName().contentEquals("")
              && typeData.get(j).getName() == group.getType(i)) {
            value = true;
            index = j;
            break;
          }
        }
        if (value) {
          typeData.set(index, new PieChart.Data(group.getType(i),
              typeData.get(index).getPieValue() + group.typeDistance(group.getType(i))));
        } else {
          typeData.add(new PieChart.Data(group.getType(i), group.typeDistance(group.getType(i))));
        }
      }
      if (group.setIntensity(i + 1) != 0) {
        Integer j = i + 1;
        intensityData.add(new PieChart.Data("Set " + (j).toString(), group.setIntensity(i + 1)));
      }
    }
  }

  public Label getHeader(int index) {
    return headers.get(index);
  }

  public DonutChart getLayout(int index) {
    return charts.get(index);
  }

  public int size() {
    return charts.size();
  }
}
