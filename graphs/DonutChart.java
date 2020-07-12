package graphs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DonutChart extends PieChart {
  private final Border border = new Border(new BorderStroke(Color.RED,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Circle innerCircle;
  private Label header;

  public DonutChart(ObservableList<Data> pieData, String headerText) {
    super(pieData);
    header = new Label(headerText);
    innerCircle = new Circle();
    
    header.setPrefSize(500, 75);
    header.setFont(Font.font("Arail", 24));
    header.setBorder(border);
    header.setAlignment(Pos.BASELINE_CENTER);
    // just styled in code for demo purposes,
    // use a style class instead to style via css.
    innerCircle.setFill(Color.WHITESMOKE);
    innerCircle.setStroke(Color.WHITE);
    innerCircle.setStrokeWidth(3);
  }

  protected void layoutChartChildren(double top, double left, double contentWidth,
      double contentHeight) {
    super.layoutChartChildren(top, left, contentWidth, contentHeight);
    addInnerCircleIfNotPresent();
    updateInnerCircleLayout();
  }


  public void addInnerCircleIfNotPresent() {
    if (getData().size() > 0) {
      Node pie = getData().get(0).getNode();
      if (pie.getParent() instanceof Pane) {
        Pane parent = (Pane) pie.getParent();
        if (!parent.getChildren().contains(innerCircle)) {
          parent.getChildren().add(innerCircle);
        } else {
          parent.getChildren().remove(innerCircle); // TODO possible problem
          parent.getChildren().add(innerCircle);
        }
      }
    }
  }

  public void updateInnerCircleLayout() {
    double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
    double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
    for (PieChart.Data data : getData()) {
      Node node = data.getNode();

      Bounds bounds = node.getBoundsInParent();
      if (bounds.getMinX() < minX) {
        minX = bounds.getMinX();
      }
      if (bounds.getMinY() < minY) {
        minY = bounds.getMinY();
      }
      if (bounds.getMaxX() > maxX) {
        maxX = bounds.getMaxX();
      }
      if (bounds.getMaxY() > maxY) {
        maxY = bounds.getMaxY();
      }
    }
    innerCircle.setCenterX(minX + (maxX - minX) / 2);
    innerCircle.setCenterY(minY + (maxY - minY) / 2);
    innerCircle.setRadius((maxX - minX) / 4);
    innerCircle.setFill(Color.WHITESMOKE);
  }
  
  public Label getHeader() {
    return header;
  }
}
