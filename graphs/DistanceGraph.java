package graphs;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// TODO find a new way to display this caus this doesnt work
public class DistanceGraph extends PieChart {
    private final Circle innerCircle;
    private int count = 0;
    
    public DistanceGraph(ObservableList<Data> pieData) {
        super(pieData);
<<<<<<< HEAD
        
=======
>>>>>>> beta
        innerCircle = new Circle();
        
        // just styled in code for demo purposes,
        // use a style class instead to style via css.
        innerCircle.setFill(Color.WHITESMOKE);
        innerCircle.setStroke(Color.BLACK);
        innerCircle.setStrokeWidth(3);
    }
    
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);
        //System.out.println("Count: " + count);
        //count++;
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
                    System.out.println("Added");
                } else {
                  parent.getChildren().remove(innerCircle); //TODO possible problem
                  parent.getChildren().add(innerCircle);
                }
            }
        }
    }

    public void updateInnerCircleLayout() {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (PieChart.Data data: getData()) {
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
        innerCircle.setFill(Color.AQUA);
    }
}